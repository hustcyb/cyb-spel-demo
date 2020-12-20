package com.cyb.spel.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.spel.test.object.Demo;
import com.cyb.spel.test.object.Simple;
import com.cyb.spel.test.object.Student;

@RunWith(SpringRunner.class)
public class SpelEvaluationTests {

	@Test
	public void testLiteralString() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello, World'");
		String message = (String) expression.getValue();
		assertEquals("Hello, World", message);
	}

	@Test
	public void testMethodInvocation() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello, World'.concat('!')");
		String message = (String) expression.getValue();
		assertEquals("Hello, World!", message);
	}

	@Test
	public void testCallProperty() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello, World'.bytes");
		byte[] bytes = (byte[]) expression.getValue();
		assertArrayEquals(new byte[] { 72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100 }, bytes);
	}

	@Test
	public void testConstructor() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("new String('Hello, World').toUpperCase()");
		String message = (String) expression.getValue();
		assertEquals("HELLO, WORLD", message);
	}

	@Test
	public void testGenericResult() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("'Hello, World'");
		String message = expression.getValue(String.class);
		assertEquals("Hello, World", message);
	}

	@Test
	public void testInstanceProperty() {
		Student student = new Student(1, "Jim");

		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("id");
		int id = expression.getValue(student, int.class);
		assertEquals(1, id);

		expression = parser.parseExpression("name");
		String name = expression.getValue(student, String.class);
		assertEquals("Jim", name);
	}

	@Test
	public void testSimpleEvaluationContext() {
		Simple simple = new Simple();
		simple.booleanList.add(true);

		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("booleanList[0]");
		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
		expression.setValue(context, simple, "false");

		boolean b = simple.booleanList.get(0);
		assertEquals(false, b);
	}

	@Test
	public void testSpelParserConfiguration() {
		Demo demo = new Demo();

		SpelParserConfiguration config = new SpelParserConfiguration(true, true);
		ExpressionParser parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("list[3]");
		String value = expression.getValue(demo, String.class);
		assertEquals("", value);
	}

	@Test
	public void testImmediateCompilerMode() {
		Student student = new Student(2, "Tom");

		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
				this.getClass().getClassLoader());
		ExpressionParser parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("name");
		String name = expression.getValue(student, String.class);
		assertEquals("Tom", name);
	}
	
	@Test
	public void testMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("className", "Computer");
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("{'Math','English','Computer'}.contains(['className'])");
		boolean contains = expression.getValue(data, boolean.class);
		assertEquals(true, contains);
	}

}
