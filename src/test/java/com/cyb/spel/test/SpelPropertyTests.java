package com.cyb.spel.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.spel.test.object.PropertyTestObject;

@RunWith(SpringRunner.class)
public class SpelPropertyTests {

	@Test
	public void testPropertyLowerCase() {
		PropertyTestObject object = new PropertyTestObject();
		object.setBirth(LocalDate.of(2000, 1, 1));
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("birth.year");
		int year = expression.getValue(object, int.class);
		assertEquals(2000, year);
	}
	
	@Test
	public void testPropertyUpperCase() {

		PropertyTestObject object = new PropertyTestObject();
		object.setBirth(LocalDate.of(2000, 1, 1));
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("T(java.time.LocalDate).now().Year - birth.Year");
		int year = expression.getValue(object, int.class);
		assertEquals(21, year);
	}
}
