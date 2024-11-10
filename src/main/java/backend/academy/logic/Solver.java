package backend.academy.logic;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.algorithms.solvers.BFS;
import backend.academy.algorithms.solvers.DFS;
import backend.academy.algorithms.solvers.SolverAlgorithm;
import backend.academy.models.Cell;
import backend.academy.models.Maze;
import backend.academy.models.Position;
import java.util.ArrayList;

public final class Solver {
    private int current = 0;
    private final SolverAlgorithm[] algorithms;

    public Solver() {
        this.algorithms = new SolverAlgorithm[]{new DFS(), new BFS()};
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

    public void solve(Maze maze) {
        if (maze == null) {
            throw new MazeException(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE);
        }
        algorithms[current].execute(maze);
    }

    public void solveWithStars(Maze maze) {
        if (maze == null) {
            throw new MazeException(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE);
        }

        Position start = null;
        Position finish = null;
        ArrayList<Position> stars = new ArrayList<>();

        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                if (maze.getSurface(y, x) == Cell.START) {
                    start = new Position(y, x);
                }
                if (maze.getSurface(y, x) == Cell.FINISH) {
                    finish = new Position(y, x);
                }
                if (maze.getSurface(y, x) == Cell.STAR) {
                    stars.add(new Position(y, x));
                }
            }
        }

        if (start == null || finish == null) {
            throw new MazeException("Start or finish not set");
        }

        for (Position star : stars) {
            maze.setSurface(star, Cell.FINISH);
            maze.setSurface(finish, Cell.STAR);
            algorithms[current].execute(maze);
            maze.setSurface(star, Cell.STAR);
            maze.setSurface(finish, Cell.FINISH);
        }
        algorithms[current].execute(maze);
    }
}
