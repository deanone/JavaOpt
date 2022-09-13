package com.asal.javaopt;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * AgentTest class: The class that tests the Agent class.
 */
@DisplayName("The class that tests the Agent class.")
class AgentTest {
	Agent agent;
	
	@BeforeEach
	void setUp() {
		int dimension = 2;
		double lowerBound = -1.0;
		double upperBound = -1.0; 
		Random randomNumberGenerator = new Random();
		agent = new Agent(dimension, lowerBound, upperBound, randomNumberGenerator);
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the dimension field.")
	void testDimensionSetterGetter() {
		agent.setDimension(2);
		assertEquals(2, agent.getDimension());
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the position field.")
	void testPositionSetterGetter() {
		double[] testPosition = new double[agent.getDimension()];
		for (int i = 0; i < testPosition.length; i++) {
			testPosition[i] = 5.0;
		}
		agent.setPosition(testPosition);
		double[] currentPosition = agent.getPosition();
		
		boolean equal = Arrays.equals(currentPosition, testPosition);
		assertTrue(equal);
	}
}
