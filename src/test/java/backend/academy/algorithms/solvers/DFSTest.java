package backend.academy.algorithms.solvers;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.logic.ApplicationProcessor;
import backend.academy.logic.Solver;
import backend.academy.models.Cell;
import backend.academy.models.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DFSTest {
    private static Solver solver;
    private static Maze solvedMaze;
    private static Maze solvedMazeWithStars;
    private static Maze unsolvedMazeWithoutStart;
    private static Maze unsolvedMazeWithoutFinish;
    private static Maze unsolvedMazeWithoutSolution;

    private Maze unsolvedMaze;

    @BeforeAll
    public static void setUpBeforeAll() {
        solver = new Solver();

        solvedMaze = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.START, Cell.SOLUTION, Cell.SOLUTION, Cell.PATHWAY, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.SOLUTION, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.SOLUTION, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.SOLUTION, Cell.SOLUTION, Cell.FINISH, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });

        solvedMazeWithStars = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.START, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.SOLUTION, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.SOLUTION, Cell.WALL, Cell.WALL, Cell.WALL, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.SOLUTION, Cell.SOLUTION, Cell.SOLUTION, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.SOLUTION, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.SOLUTION, Cell.SOLUTION, Cell.FINISH, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });

        unsolvedMazeWithoutStart = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.FINISH, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });

        unsolvedMazeWithoutFinish = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.START, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });

        unsolvedMazeWithoutSolution = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.START, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.FINISH, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });
    }

    @BeforeEach
    public void setUpBeforeEach() {
        unsolvedMaze = new Maze(new Cell[][] {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.START, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.STAR, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.STAR, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.PATHWAY, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.WALL, Cell.PATHWAY, Cell.WALL, Cell.PATHWAY, Cell.PATHWAY, Cell.FINISH, Cell.WALL},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
        });
    }

    @Test
    void testIncorrectMaze() {
        int exceptionsThrown = 0;

        Maze nullMaze = null;
        try {
            solver.solve(nullMaze);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE));
        }

        Maze emptyMaze = new Maze(solvedMaze.height(), solvedMaze.width());
        try {
            solver.solve(emptyMaze);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Start or finish not set"));
        }

        try {
            solver.solve(unsolvedMazeWithoutStart);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Start or finish not set"));
        }

        try {
            solver.solve(unsolvedMazeWithoutFinish);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Start or finish not set"));
        }

        try {
            solver.solve(unsolvedMazeWithoutSolution);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("There is no such way"));
        }

        assert(exceptionsThrown == 5);
    }

    @Test
    void testSolution() {
        solver.solve(unsolvedMaze);
        assert(unsolvedMaze.toString().equals(solvedMaze.toString()));
    }

    @Test
    void testSolutionWithStars() {
        solver.solveWithStars(unsolvedMaze);
        assert(unsolvedMaze.toString().equals(solvedMazeWithStars.toString()));
    }
}
