package com.asal.javaopt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class DifferentialEvolution extends Optimizer {
	protected double crossoverProbability;
	protected double differentialWeight;
		
	/**
	 * Constructor.
	 * @param propertiesFilename the name of the properties file that contains the properties of the method
	 */
	protected DifferentialEvolution(String propertiesFilename) {
		super(propertiesFilename);
		try {
			// read properties of the DE algorithm from a .properties file
            this.crossoverProbability = psoPropertiesParser.getPropertyAsDouble("crossoverProbability");
            this.differentialWeight = psoPropertiesParser.getPropertyAsDouble("differentialWeight");
            double lowerBound = psoPropertiesParser.getPropertyAsDouble("lowerBound");
            double upperBound = psoPropertiesParser.getPropertyAsDouble("upperBound");
            Random randomNumberGenerator = new Random();
            randomNumberGenerator.setSeed(42); // for reproducibility of the results
            
            // construct agents
            this.swarm = new ArrayList<Agent>();
            for (int agentIndex = 0; agentIndex < numAgents; ++agentIndex) {
            	Agent agent = new Agent(this.dimension);
            	agent.initialize(lowerBound, upperBound, randomNumberGenerator);
            	addAgent(agent);
            }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
    /**
     * Runs the iterative Differential Evolution (DE) procedure.
     */
	protected void run() {
        Random randomNumberGenerator = new Random();
        randomNumberGenerator.setSeed(42); // for reproducibility of the results
		
		double[] oldSolution = Arrays.copyOf(solution, solution.length);
		double funcValueAtOldSolution = funcToOptimize.evaluate(oldSolution);
		
		System.out.println("Iteration --> f(x)");
		int iterationsIndex = 0;
		while (iterationsIndex < maxNumIterations) {
			
			System.out.println(iterationsIndex + " --> " + String.format("%.4f", funcValueAtOldSolution));
			
			// estimate the new positions of all agents (main part of the algorithm)
	        for (int agentIndex = 0; agentIndex < numAgents; ++agentIndex) {
	        	Agent agent = swarm.get(agentIndex);
	        	double[] agentPosition = agent.getPosition();
	        	
	        	// step 1: pick three agents a, b, c
	        	ArrayList<Integer> agentsPickedFromPopulation = new ArrayList<Integer>();
	        	while (agentsPickedFromPopulation.size() != 3) {
	        		int randomAgentFromPopulation = (randomNumberGenerator.nextInt(numAgents));
	        		if ((agentsPickedFromPopulation.contains(randomAgentFromPopulation)) || (randomAgentFromPopulation == agentIndex)) {
	        			continue;
	        		} else {
	        			agentsPickedFromPopulation.add(randomAgentFromPopulation);	
	        		}
	        	}	        	
	        	Agent agentA = swarm.get(agentsPickedFromPopulation.get(0));
	        	double[] agentAPosition = agentA.getPosition();
	        	Agent agentB = swarm.get(agentsPickedFromPopulation.get(1));
	        	double[] agentBPosition = agentB.getPosition();
	        	Agent agentC = swarm.get(agentsPickedFromPopulation.get(2));
	        	double[] agentCPosition = agentC.getPosition();
	        	
	        	// step 2: pick a random index R
	        	int R = randomNumberGenerator.nextInt(dimension);
	        	
	        	// step 3: compute the agent's potentially new position
	        	double[] newPosition = new double[dimension];
	        	for (int i = 0; i < dimension; ++i) {
	        		int ri = randomNumberGenerator.nextInt();
	        		if ((ri < crossoverProbability) || (i == R)) {
	        			newPosition[i] = agentAPosition[i] + differentialWeight * (agentBPosition[i] - agentCPosition[i]);
	        		} else {
	        			newPosition[i] = agentPosition[i];
	        		}
	        	}
	        	
	        	// step 4: if the value of the function to be optimized at the new position is less than or equal to the value
	        	// at the previous position, then replace the previous with the new position of the agent
	        	double funcValueAtOldPosition = funcToOptimize.evaluate(agentPosition);
	        	double funcValueAtNewPosition = funcToOptimize.evaluate(newPosition);
        		if (funcValueAtNewPosition <= funcValueAtOldPosition) {
        			agent.setPosition(newPosition);
        		}
	        }
	        
	        // termination evaluation
	        for (int agentIndex = 0; agentIndex < numAgents; ++agentIndex) {
	        	Agent agent = swarm.get(agentIndex);
	        	double[] agentPosition = agent.getPosition();
	        	double funcValueAtPosition = funcToOptimize.evaluate(agentPosition);
	        	if (funcValueAtPosition <= funcValueAtOldSolution) {
	        		solution = agentPosition;
	        		funcValueAtOldSolution = funcValueAtPosition;
	        		
	        	}
	        }
            DistanceCalculator distanceCalculator = new DistanceCalculator(oldSolution, solution);
            double dist = distanceCalculator.euclideanDistance();
            if (dist < tol) {
                break;
            } else {
            	oldSolution = Arrays.copyOf(solution, solution.length);
            	funcValueAtOldSolution = funcToOptimize.evaluate(oldSolution);
                iterationsIndex++;
            }
		}
	}
}
