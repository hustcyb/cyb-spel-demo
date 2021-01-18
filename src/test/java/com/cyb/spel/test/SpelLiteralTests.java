package com.cyb.spel.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SpelLiteralTests {

	@Test
	public void testStringLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello World'");
		String message = expression.getValue(String.class);
		assertEquals("Hello World", message);
	}
	
	@Test
	public void testDecIntegerLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("23");
		int number = expression.getValue(int.class);
		assertEquals(23, number);
	}
	
	@Test
	public void testHexIntegerLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("0x17");
		int number = expression.getValue(int.class);
		assertEquals(23, number);
	}
	
	@Test
	public void testScientificIntegerLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("2E3");
		int number = expression.getValue(int.class);
		assertEquals(2000, number);
	}
	
	@Test
	public void testFloatLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("1.23");
		float number = expression.getValue(float.class);
		assertEquals(1.23f, number, 0.001f);
	}
	
	@Test
	public void testDoubleLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("1.23");
		double number = expression.getValue(double.class);
		assertEquals(1.23, number, 0.001);
	}
	
	@Test
	public void testScientificDoubleLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("1.2345E2");
		double number = expression.getValue(double.class);
		assertEquals(123.45, number, 0.01);
	}
}
