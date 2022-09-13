package com.asal.javaopt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DifferentialEvolution {
	PropertiesParser psoPropertiesParser;
	int dimension;
	int populationSize;
	double tol;
	int maxNumIterations;
	double lowerBound;
	double upperBound;
	double crossoverProbability;
	double differentialWeight;

	ArrayList<Agent> swarm;
	FunctionToOptimize funcToOptimize;
	
	double[] solution;
	Random randomNumberGenerator;
		
	/**
	 * Constructor.
	 * @param propertiesFilename the name of the properties file that contains the properties of the method.
	 */
	public DifferentialEvolution(String propertiesFilename) {
		try {
        	// read properties of the algorithm from a properties file
            psoPropertiesParser = new PropertiesParser(propertiesFilename);
            psoPropertiesParser.readPropertiesValues();
            
            this.funcToOptimize = new FunctionToOptimize(psoPropertiesParser.getPropertyAsInteger("fType"));
            this.dimension = psoPropertiesParser.getPropertyAsInteger("dimension");
            this.populationSize = psoPropertiesParser.getPropertyAsInteger("populationSize");
            this.tol = psoPropertiesParser.getPropertyAsDouble("tol");
            this.maxNumIterations = psoPropertiesParser.getPropertyAsInteger("maxNumIterations");
            this.lowerBound = psoPropertiesParser.getPropertyAsDouble("lowerBound");
            this.upperBound = psoPropertiesParser.getPropertyAsDouble("upperBound");
            this.crossoverProbability = psoPropertiesParser.getPropertyAsDouble("crossoverProbability");
            this.differentialWeight = psoPropertiesParser.getPropertyAsDouble("differentialWeight");
            randomNumberGenerator = new Random();
            randomNumberGenerator.setSeed(42);	// for reproducibility of the results
            
            // construct set of candidate solutions
            this.swarm = new ArrayList<Agent>();
            for (int agentIndex = 0; agentIndex < this.populationSize; ++agentIndex) {
                addAgent(new Agent(this.dimension, this.lowerBound, this.upperBound, randomNumberGenerator));
            }
            
            // initial random solution
            solution = new double[dimension];
            for (int i = 0; i < solution.length; ++i) {
            	solution[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
            }

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	/**
	 * Adds a new agent to the swarm.
	 * @param agent the new agent to be added.
	 */
	public void addAgent(Agent agent) {
		swarm.add(agent);
	}
	
    /**
     * Runs the iterative Differential Evolution (DE) procedure.
     */
	public void run() {
		double[] oldSolution = Arrays.copyOf(solution, solution.length);
		double funcValueAtOldSolution = funcToOptimize.evaluate(oldSolution);
		
		System.out.println("Iteration --> f(x)");
		int iterationsIndex = 0;
		while (iterationsIndex < maxNumIterations) {
			
			System.out.println(iterationsIndex + " --> " + String.format("%.4f", funcValueAtOldSolution));
			
			// estimate the new positions of all agents (main part of the algorithm)
	        for (int agentIndex = 0; agentIndex < populationSize; ++agentIndex) {
	        	Agent agent = swarm.get(agentIndex);
	        	double[] agentPosition = agent.getPosition();
	        	
	        	// step 1: pick three agents a, b, c
	        	ArrayList<Integer> agentsPickedFromPopulation = new ArrayList<Integer>();
	        	while (agentsPickedFromPopulation.size() != 3) {
	        		int randomAgentFromPopulation = (randomNumberGenerator.nextInt(populationSize));
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
	        for (int agentIndex = 0; agentIndex < populationSize; ++agentIndex) {
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
	
    /**
     * Prints the solution.
     */
    public void printSolution() {
        System.out.println();
        System.out.println("<------------------------ Solution ------------------------>");
        char[] spaces = new char[10];
        Arrays.fill(spaces, ' ');
        System.out.print(new String(spaces));
        System.out.print("x = [");
        System.out.printf("%.4f", solution[0]);
        for (int solutionElementIndex = 1; solutionElementIndex < solution.length; ++solutionElementIndex) {
            System.out.printf(", %.4f", solution[solutionElementIndex]);
        }
        System.out.print("]");
        System.out.println(", f(x) = " + String.format("%.4f", funcToOptimize.evaluate(solution)));
        System.out.println("<---------------------------------------------------------->");
    }
	
    /**
     * Starting point of the application.
     */
    public static void main(String[] args) {
    	String propertiesFilename = "\\DE.properties";
        DifferentialEvolution differentialEvolutionMethod = new DifferentialEvolution(propertiesFilename);
        differentialEvolutionMethod.run();
        differentialEvolutionMethod.printSolution();
        //System.out.printf("\nOptimum objective function value for solution g: %.4f\n", pso.f.f(pso.getSolution()));
        System.exit(0);
    }
}
