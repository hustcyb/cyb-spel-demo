package com.cyb.spel.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.spel.test.object.ArrayTestObject;
import com.cyb.spel.test.object.Student;

@RunWith(SpringRunner.class)
public class SpelArrayTests {

	@Test
	public void testStringArray() {
		ArrayTestObject object = new ArrayTestObject();
		object.setNames(new String[] { "Top", "Best" });
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("names[1]");
		String name = expression.getValue(object, String.class);
		assertEquals("Best", name);
	}
	
	@Test
	public void testStudentArray() {
		Student student = new Student(10, "Jim");
		
		ArrayTestObject object = new ArrayTestObject();
		object.setStudents(new Student[] { student });
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("students[0].name");
		String studentName = expression.getValue(object, String.class);
		assertEquals("Jim", studentName);
	}
}
