package com.rodrigovalim07.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigovalim07.exceptions.UnsupportedMathOperationException;

@Service
public class MathService {
	
	@Autowired
	private NumberConverterService nc;
	
	public Double sum(String numberOne, String numberTwo) {
		if (!nc.isNumeric(numberOne) || !nc.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return nc.convertToDouble(numberOne) + nc.convertToDouble(numberTwo);
	}
	
	public Double subtraction(String numberOne, String numberTwo) {
		if (!nc.isNumeric(numberOne) || !nc.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return nc.convertToDouble(numberOne) - nc.convertToDouble(numberTwo);
	}
	
	public Double multiplication(String numberOne, String numberTwo) {
		if (!nc.isNumeric(numberOne) || !nc.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return nc.convertToDouble(numberOne) * nc.convertToDouble(numberTwo);
	}
	
	public Double division(String numberOne, String numberTwo) {
		if (!nc.isNumeric(numberOne) || !nc.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return nc.convertToDouble(numberOne) / nc.convertToDouble(numberTwo);
	}
	
	public Double avarege(String numberOne, String numberTwo) {
		if (!nc.isNumeric(numberOne) || !nc.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return (nc.convertToDouble(numberOne) + nc.convertToDouble(numberTwo)) / 2;
	}
	
	public Double squareRoot(String number) {
		if (!nc.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return Math.sqrt(nc.convertToDouble(number));
	}
}
