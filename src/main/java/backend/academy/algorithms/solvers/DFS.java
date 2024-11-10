package backend.academy.algorithms.solvers;

import backend.academy.algorithms.exceptions.NoWayFoundException;
import backend.academy.models.Cell;
import backend.academy.models.Maze;

public class DFS implements SolverAlgorithm {
    @Override
    public void execute(Maze maze) throws NoWayFoundException {
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        int startY = -1;
        int startX = -1;
        boolean hasFinishPosition = false;

        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                if (maze.getSurface(y, x) == Cell.WALL) {
                    visited[y][x] = true;
                }
                if (maze.getSurface(y, x) == Cell.START) {
                    startY = y;
                    startX = x;
                }
                if (maze.getSurface(y, x) == Cell.FINISH) {
                    hasFinishPosition = true;
                }
            }
        }

        if (!hasFinishPosition || startY == -1) {
            throw new NoWayFoundException("Start or finish not set");
        }

        if (!dfs(maze, visited, startY, startX)) {
            throw new NoWayFoundException("There is no such way");
        }
    }

    @SuppressWarnings("returncount")
    private boolean dfs(Maze maze, boolean[][] visited, int y, int x) {
        if (visited[y][x]) {
            return false;
        }

        visited[y][x] = true;

        if (maze.getSurface(y, x) == Cell.FINISH) {
            return true;
        }

        if (y != 0 && dfs(maze, visited, y - 1, x)) {
            if (maze.getSurface(y, x) == Cell.PATHWAY) {
                maze.setSurface(y, x, Cell.SOLUTION);
            }
            return true;
        }
        if (x != 0 && dfs(maze, visited, y, x - 1)) {
            if (maze.getSurface(y, x) == Cell.PATHWAY) {
                maze.setSurface(y, x, Cell.SOLUTION);
            }
            return true;
        }
        if (y != maze.height() - 1 && dfs(maze, visited, y + 1, x)) {
            if (maze.getSurface(y, x) == Cell.PATHWAY) {
                maze.setSurface(y, x, Cell.SOLUTION);
            }
            return true;
        }
        if (x != maze.width() - 1 && dfs(maze, visited, y, x + 1)) {
            if (maze.getSurface(y, x) == Cell.PATHWAY) {
                maze.setSurface(y, x, Cell.SOLUTION);
            }
            return true;
        }

        return false;
    }

    public String toString() {
        return "DFS";
    }
}
