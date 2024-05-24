package com.rodrigovalim07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigovalim07.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}