package com.rodrigovalim07.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigovalim07.services.MathService;

@RestController
public class MathController {
	
	@Autowired
	private MathService mathService;
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
						@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		return mathService.sum(numberOne, numberTwo);
	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
							@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		return mathService.subtraction(numberOne, numberTwo);
	}
	
	@GetMapping("/mult/{numberOne}/{numberTwo}")
	public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
							@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		return mathService.multiplication(numberOne, numberTwo);
	}
	
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double division(@PathVariable(value = "numberOne") String numberOne,
						@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		return mathService.division(numberOne, numberTwo);
	}
	
	@GetMapping("/average/{numberOne}/{numberTwo}")
	public Double average(@PathVariable(value = "numberOne") String numberOne,
						@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		return mathService.avarege(numberOne, numberTwo);
	}
	
	@GetMapping("/sqrt/{number}")
	public Double squareRoot(@PathVariable(value = "number") String number) {
		return mathService.squareRoot(number);
	}
}
