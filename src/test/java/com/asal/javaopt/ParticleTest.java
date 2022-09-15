package com.asal.javaopt;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
@DisplayName("The class that tests the Particle class.")
public class ParticleTest {
	private Particle particle;
	
	@BeforeEach
	void setUp() {
		int dimension = 2;
		double lowerBound = -1.0;
		double upperBound = 1.0; 
		Random randomNumberGenerator = new Random();
		particle = new Particle(dimension);
		particle.initialize(lowerBound, upperBound, randomNumberGenerator);
	}
	
	@Test
	@DisplayName("Testing that particle's position intialized with values from the provided interval.")
	public void testPositionInitialize() {
		double lowerBound = -1.0;
		double upperBound = 1.0; 
		double[] position = particle.getPosition();
		boolean positionCorrectlyInitialized = true;
		for (int positionIndex = 0; positionIndex < position.length; positionIndex++) {
			if ((position[positionIndex] < lowerBound) || (position[positionIndex] > upperBound)) {
				positionCorrectlyInitialized = false;
				break;
			}
		}
		assertTrue(positionCorrectlyInitialized);
	}
	
	@Test
	@DisplayName("Testing that particle's best position intialized with values from the provided interval.")
	public void testBestPositionInitialize() {
		double lowerBound = -1.0;
		double upperBound = 1.0; 
		double[] bestPosition = particle.getBestPosition();
		boolean bestPositionCorrectlyInitialized = true;
		for (int bestPositionIndex = 0; bestPositionIndex < bestPosition.length; bestPositionIndex++) {
			if ((bestPosition[bestPositionIndex] < lowerBound) || (bestPosition[bestPositionIndex] > upperBound)) {
				bestPositionCorrectlyInitialized = false;
				break;
			}
		}
		assertTrue(bestPositionCorrectlyInitialized);
	}
	
	@Test
	@DisplayName("Testing that particle's velocity intialized with values from the provided interval.")
	public void testVelocityInitialize() {
		double lowerBound = -1.0;
		double upperBound = 1.0; 
		double[] velocity = particle.getVelocity();
		boolean velocityCorrectlyInitialized = true;
		for (int velocityIndex = 0; velocityIndex < velocity.length; velocityIndex++) {
			if ((velocity[velocityIndex] < lowerBound) || (velocity[velocityIndex] > upperBound)) {
				velocityCorrectlyInitialized = false;
				break;
			}
		}
		assertTrue(velocityCorrectlyInitialized);		
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the dimension field.")
	public void testDimensionSetterGetter() {
		particle.setDimension(3);
		assertEquals(3, particle.getDimension());
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the position field.")
	public void testPositionSetterGetter() {
		double[] testPosition = {1, 2};
		particle.setPosition(testPosition);
		double[] actualPosition = particle.getPosition();
		assertArrayEquals(testPosition, actualPosition);
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the bestPosition field.")
	public void testBestPositionSetterGetter() {
		double[] testBestPosition = {1, 2};
		particle.setBestPosition(testBestPosition);
		double[] actualBestPosition = particle.getBestPosition();
		assertArrayEquals(testBestPosition, actualBestPosition);
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the velocity field.")
	public void testVelocitySetterGetter() {
		double[] testVelocity = {1, 2};
		particle.setVelocity(testVelocity);
		double[] actualVelocity = particle.getVelocity();
		assertArrayEquals(testVelocity, actualVelocity);
	}
}
