# JavaOpt - A Java library for optimization algorithms

The JavaOpt library contains a collection of [metaheuristics](https://en.wikipedia.org/wiki/Metaheuristic) for optimization.

<div style="text-align: justify;">
	<p>
		In computer science and mathematical optimization, a metaheuristic is a higher-level procedure or heuristic designed to find, 		generate, or select a heuristic (partial search algorithm) that may provide a sufficiently good solution to an optimization 		problem, especially with incomplete or imperfect information or limited computation capacity.
	</p>
	<p>
		Metaheuristics make few or no assumptions about the problem being optimized and can search very large spaces of candidate 		solutions. However, metaheuristics do not guarantee an optimal solution is ever found.
	</p>
	<p>
		Metaheuristics do not use the gradient of the problem being optimized, which means they do not require the optimization 		problem to be differentiable, as is required by classic optimization methods such as gradient descent and Quasi-Newton methods.
	</p>
</div>

## Optimization algorithms

Currently, the library contains the following routines:

1. [Particle Swarm Optimization (PSO)](https://en.wikipedia.org/wiki/Particle_swarm_optimization)
2. [Differential Evolution (DE)](https://en.wikipedia.org/wiki/Differential_evolution)

## Multidimensional real-valued functions

The routines have been tested so far on the following multidimensional real-valued functions:

1. [Sphere function](https://www.sfu.ca/~ssurjano/spheref.html)
2. [Rosenbrock function](https://en.wikipedia.org/wiki/Rosenbrock_function)

## Terminology used in code

A set of specific terms/names is used in the source code. In particular:

1. Agent: a basic candidate solution
2. Particle: an enhanced candidate solution
3. Swarm: a set of candidate solutions