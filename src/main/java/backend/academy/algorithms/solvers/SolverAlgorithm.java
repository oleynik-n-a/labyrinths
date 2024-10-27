package backend.academy.algorithms.solvers;

import backend.academy.algorithms.Algorithm;
import backend.academy.algorithms.exceptions.NoWayFoundException;
import backend.academy.models.Maze;

public interface SolverAlgorithm extends Algorithm {
    void execute(Maze maze) throws NoWayFoundException;
}
