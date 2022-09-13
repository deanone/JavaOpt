# JavaOpt - A Java library for optimization algorithms

The JavaOpt library contains a collection of [metaheuristics](https://en.wikipedia.org/wiki/Metaheuristic) for optimization.

In computer science and mathematical optimization, a metaheuristic is a higher-level procedure or heuristic designed to find, generate, or select a heuristic (partial search algorithm) that may provide a sufficiently good solution to an optimization problem, especially with incomplete or imperfect information or limited computation capacity.

Metaheuristics make few or no assumptions about the problem being optimized and can search very large spaces of candidate solutions. However, metaheuristics do not guarantee an optimal solution is ever found.

Metaheuristics do not use the gradient of the problem being optimized, which means they does not require the optimization problem to be differentiable, as is required by classic optimization methods such as gradient descent and quasi-newton methods.

Currently, the library contains the following routines:

1. [Particle Swarm Optimization (PSO)](https://en.wikipedia.org/wiki/Particle_swarm_optimization)
2. [Differential Evolution (DE)](https://en.wikipedia.org/wiki/Differential_evolution)

The routines have been tested so far on the following multidimensional real-valued functions:

1. [Sphere function](https://www.sfu.ca/~ssurjano/spheref.html)
2. [Rosenbrock function](https://en.wikipedia.org/wiki/Rosenbrock_function)