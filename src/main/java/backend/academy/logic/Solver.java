package backend.academy.logic;

import backend.academy.algorithms.exceptions.GenerationErrorException;
import backend.academy.algorithms.solvers.DFS;
import backend.academy.algorithms.solvers.SolverAlgorithm;
import backend.academy.models.Maze;

public final class Solver {
    private int current = 0;
    private final SolverAlgorithm[] algorithms;

    public Solver() {
        this.algorithms = new SolverAlgorithm[]{new DFS(), new DFS()};
    }

    public void switchAlgorithm() {
        ++current;
        if (current == algorithms.length) {
            current = 0;
        }
    }

    public SolverAlgorithm algorithm() {
        return algorithms[current];
    }

    public SolverAlgorithm nextAlgorithm() {
        return algorithms[current + 1 == algorithms.length ? 0 : current + 1];
    }

    public String solve(Maze maze) {
        try {
            algorithms[current].execute(maze);
        } catch (GenerationErrorException e) {
            return e.getMessage();
        }
        return null;
    }
}
