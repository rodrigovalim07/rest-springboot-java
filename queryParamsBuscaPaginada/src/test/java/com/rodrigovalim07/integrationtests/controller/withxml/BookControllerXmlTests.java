package com.rodrigovalim07.integrationtests.controller.withxml;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rodrigovalim07.configs.TestConfigs;
import com.rodrigovalim07.integrationtests.testcontainers.AbstractIntegrationTest;
import com.rodrigovalim07.integrationtests.vo.AccountCredentialsVO;
import com.rodrigovalim07.integrationtests.vo.BookVO;
import com.rodrigovalim07.integrationtests.vo.TokenVO;
import com.rodrigovalim07.integrationtests.vo.pagedmodels.PagedModelBook;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class BookControllerXmlTests extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static XmlMapper objectMapper;
	private static BookVO book;
	
	@BeforeAll
	public static void setup() {
	    objectMapper = new XmlMapper();
	    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	    book = new BookVO();
	}
	
	@Test
	@Order(0)
	public void authorization() throws JsonMappingException, JsonProcessingException {
		AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin123");
		
		var accessToken = given()
				.basePath("/auth/signin")
					.port(TestConfigs.SERVER_PORT)
					.contentType(TestConfigs.CONTENT_TYPE_XML)
					.accept(TestConfigs.CONTENT_TYPE_XML)
				.body(user)
					.when()
				.post()
					.then()
						.statusCode(200)
							.extract()
							.body()
								.as(TokenVO.class)
							.getAccessToken();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
				.setBasePath("/api/book/v1")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
	}
	
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockBook();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
				.accept(TestConfigs.CONTENT_TYPE_XML)
					.body(book)
					.when()
					.post()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
        book = objectMapper.readValue(content, BookVO.class);
		
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getTitle());
		assertNotNull(book.getAuthor());
		assertNotNull(book.getPrice());
		assertNotNull(book.getLaunchDate());
		assertTrue(book.getId() > 0);
		
		assertEquals("Java Concurrency in Practice", book.getTitle());
		assertEquals("Brian Goetz e Tim Peierls", book.getAuthor());
		assertEquals(250D, book.getPrice());
	}
	
	@Test
	@Order(2)
	public void testUpdate() throws JsonMappingException, JsonProcessingException {
		book.setTitle("Java para Vencedores");
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
				.accept(TestConfigs.CONTENT_TYPE_XML)
				.body(book)
				.when()
				.put()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		book = objectMapper.readValue(content, BookVO.class);
		
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getTitle());
		assertNotNull(book.getAuthor());
		assertNotNull(book.getPrice());
		assertNotNull(book.getLaunchDate());
		
		assertEquals(book.getId(), book.getId());	
		assertEquals("Java para Vencedores", book.getTitle());
		assertEquals("Brian Goetz e Tim Peierls", book.getAuthor());
		assertEquals(250D, book.getPrice());
	}

	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockBook();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
				.accept(TestConfigs.CONTENT_TYPE_XML)
	            .pathParam("id", book.getId())
				.basePath("/api/book/v1/{id}")
				.when()
				.get()
				.then()
					.statusCode(200)
					.extract()
					.body()
						.asString();
		
		book = objectMapper.readValue(content, BookVO.class);
		
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getTitle());
		assertNotNull(book.getAuthor());
		assertNotNull(book.getPrice());
		assertNotNull(book.getLaunchDate());
		
		assertEquals(book.getId(), book.getId());
		assertEquals("Java para Vencedores", book.getTitle());
		assertEquals("Brian Goetz e Tim Peierls", book.getAuthor());
		assertEquals(250D, book.getPrice());
	}

	@Test
	@Order(4)
	public void testDelete() throws JsonMappingException, JsonProcessingException {
		
		given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
				.pathParam("id", book.getId())
				.basePath("/api/book/v1/{id}")
				.when()
				.delete()
				.then()
				.statusCode(204);
	}
	
	@Test
	@Order(5)
	public void testFindAll() throws JsonMappingException, JsonProcessingException {
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
				.accept(TestConfigs.CONTENT_TYPE_XML)
				.queryParams("page", 0, "size", 10, "direction", "asc")
					.when()
					.get()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		PagedModelBook wrapper = objectMapper.readValue(content, PagedModelBook.class);
		var book = wrapper.getContent();
		
		BookVO foundBookOne = book.get(0);
		
		assertNotNull(foundBookOne.getId());
		assertNotNull(foundBookOne.getTitle());
		assertNotNull(foundBookOne.getAuthor());
		assertNotNull(foundBookOne.getLaunchDate());
		assertNotNull(foundBookOne.getPrice());
		
		assertEquals((Long) 12L, foundBookOne.getId());
		
		assertEquals("Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana", foundBookOne.getTitle());
		assertEquals("Viktor Mayer-Schonberger e Kenneth Kukier", foundBookOne.getAuthor());
		assertEquals(54.00, foundBookOne.getPrice());
		
		BookVO foundBookSix = book.get(5);
		
		assertNotNull(foundBookSix.getId());
		assertNotNull(foundBookSix.getTitle());
		assertNotNull(foundBookSix.getAuthor());
		assertNotNull(foundBookSix.getLaunchDate());
		assertNotNull(foundBookSix.getPrice());
		
		assertEquals((Long) 11L, foundBookSix.getId());
		
		assertEquals("Engenharia de Software: uma abordagem profissional", foundBookSix.getTitle());
		assertEquals("Roger S. Pressman", foundBookSix.getAuthor());
		assertEquals(56.00, foundBookSix.getPrice());
	}
	
	@Test
	@Order(6)
	public void testFindAllWithoutToken() throws JsonMappingException, JsonProcessingException {
		
		RequestSpecification specificationWithoutToken = new RequestSpecBuilder()
			.setBasePath("/api/book/v1")
			.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();
		
		given().spec(specificationWithoutToken)
				.contentType(TestConfigs.CONTENT_TYPE_XML)
					.when()
					.get()
				.then()
					.statusCode(403);
	}
		
	private void mockBook() {
		book.setTitle("Java Concurrency in Practice");
		book.setAuthor("Brian Goetz e Tim Peierls");
		book.setLaunchDate(new Date());
		book.setPrice(250D);		
	}
}
