package backend.academy.algorithms.solvers;

import backend.academy.algorithms.exceptions.NoWayFoundException;
import backend.academy.models.Maze;
import backend.academy.models.SurfaceType;

public class DFS implements SolverAlgorithm {
    @Override
    public void execute(Maze maze) throws NoWayFoundException {
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        int y1 = -1, x1 = -1;
        for (int x = 0; x < maze.height(); x++) {
            for (int y = 0; y < maze.width(); y++) {
                if (maze.cells()[y][x].surface() == SurfaceType.WALL) {
                    visited[y][x] = true;
                }
                if (maze.cells()[y][x].surface() == SurfaceType.START) {
                    y1 = y;
                    x1 = x;
                }
            }
        }
        if (!dfs(maze, visited, y1, x1)) {
            throw new NoWayFoundException("There is no such way");
        }
    }

    private boolean dfs(Maze maze, boolean[][] visited, int y1, int x1) {
        if (visited[y1][x1]) {
            return false;
        }

        visited[y1][x1] = true;

        if (maze.cells()[y1][x1].surface() == SurfaceType.FINISH) {
            return true;
        }

        if (y1 != 0 && dfs(maze, visited,y1 - 1, x1)) {
            if (maze.cells()[y1][x1].surface() == SurfaceType.PATHWAY) {
                maze.cells()[y1][x1].surface(SurfaceType.SOLUTION);
            }
            return true;
        }
        if (x1 != 0 && dfs(maze, visited, y1, x1 - 1)) {
            if (maze.cells()[y1][x1].surface() == SurfaceType.PATHWAY) {
                maze.cells()[y1][x1].surface(SurfaceType.SOLUTION);
            }
            return true;
        }
        if (y1 != maze.height() - 1 && dfs(maze, visited, y1 + 1, x1)) {
            if (maze.cells()[y1][x1].surface() == SurfaceType.PATHWAY) {
                maze.cells()[y1][x1].surface(SurfaceType.SOLUTION);
            }
            return true;
        }
        if (x1 != maze.width() - 1 && dfs(maze, visited, y1, x1 + 1)) {
            if (maze.cells()[y1][x1].surface() == SurfaceType.PATHWAY) {
                maze.cells()[y1][x1].surface(SurfaceType.SOLUTION);
            }
            return true;
        }

        return false;
    }

    public String toString() {
        return "DFS";
    }
}
