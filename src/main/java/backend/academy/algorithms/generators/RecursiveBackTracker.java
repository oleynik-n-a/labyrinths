package backend.academy.algorithms.generators;

import backend.academy.models.Cell;
import backend.academy.models.Maze;
import backend.academy.models.Position;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveBackTracker implements GeneratorAlgorithm {
    private static final int[][] DIRECTIONS = {
        {0, -2},
        {0, 2},
        {-2, 0},
        {2, 0}
    };


    @Override
    public void execute(Maze maze) {
        maze.clearMaze();
        SecureRandom random = new SecureRandom();
        Position start = new Position(random.nextInt(maze.height() / 2) * 2 + 1, random.nextInt(maze.width() / 2) * 2
            + 1);

        dfs(maze, start.y(), start.x());
    }

    private void dfs(Maze maze, int y, int x) {
        maze.setSurface(y, x, Cell.PATHWAY);

        List<int[]> dirs = Arrays.asList(DIRECTIONS.clone());
        Collections.shuffle(dirs);

        for (int[] dir : dirs) {
            int dx = dir[0];
            int dy = dir[1];

            int newY = y + dy;
            int newX = x + dx;

            if (newY > 0 && newY < maze.height() && newX > 0 && newX < maze.width()) {
                if (maze.getSurface(newY, newX) == Cell.WALL) {
                    maze.setSurface(y + dy / 2, x + dx / 2, Cell.PATHWAY);
                    dfs(maze, newY, newX);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "RecursiveBackTracker";
    }
}
