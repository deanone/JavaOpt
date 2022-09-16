package com.asal.javaopt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticleSwarmOptimizationTest {
	ParticleSwarmOptimization pso;
	
	@BeforeEach
	void setUp() {
		int fType = 1;
		int dimension = 2;
		int numAgents = 100;
		double tol = 1e-10;
		int maxNumIterations = 100;
		double lowerBound = -1.0;
		double upperBound = 1.0;
		double inertiaWeight = 0.5;
		double cognitiveCoefficient = 2.0;
		double socialCoefficient = 2.0;
		
		pso = new ParticleSwarmOptimization(fType, dimension, numAgents, tol, maxNumIterations, lowerBound, upperBound, 
				inertiaWeight, cognitiveCoefficient, socialCoefficient);
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the dimension field.")
	void testDimensionSetterGetter() {
		pso.setDimension(3);
		assertEquals(3, pso.getDimension());
	}
	
	@Test
	@DisplayName("Testing the getter of the numAgents field.")
	void testNumAgentsSetterGetter() {
		assertEquals(100, pso.getNumAgents());
	}
	
	@Test
	@DisplayName("Testing putting and retrieving an agent.")
	void testAgentSetterGetter() {
		int dimension = 2;
		double lowerBound = -1.0;
		double upperBound = 1.0;
        
		Random randomNumberGenerator = new Random();
        randomNumberGenerator.setSeed(42);
        
        Agent agent = new Agent(dimension);
        agent.initialize(lowerBound, upperBound, randomNumberGenerator);
        
		pso.addAgent(agent);
		
		Agent retrievedAgent = pso.getAgent(0);
		
		assertAll("The retrieved Agent object should have the same fields as the initial Agent object",
				() -> assertEquals(agent.getDimension(), retrievedAgent.getDimension()),
				() -> assertArrayEquals(agent.getPosition(), retrievedAgent.getPosition(), 1e-10));
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the tol field.")
	void testTolSetterGetter() {
		pso.setTolerance(1e-5);
		assertEquals(1e-5, pso.getTolerance());
	}
	
	@Test
	@DisplayName("Testing the setter and getter of the maxNumIterations field.")
	void testMaxNumIterationsSetterGetter() {
		pso.setMaxNumIterations(200);
		assertEquals(200, pso.getMaxNumIterations());
	}
	
}
