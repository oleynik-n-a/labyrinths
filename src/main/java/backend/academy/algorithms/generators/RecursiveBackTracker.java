package backend.academy.algorithms.generators;

import backend.academy.models.Cell;
import backend.academy.models.Maze;
import backend.academy.models.Position;
import backend.academy.models.SurfaceType;
import org.checkerframework.checker.units.qual.C;
import org.openjdk.jmh.generators.core.GenerationException;
import java.security.SecureRandom;

public class RecursiveBackTracker implements GeneratorAlgorithm {
    @Override
    public void execute(Maze maze) throws GenerationException {
        if (maze == null) {
            maze = new Maze(new Cell[10][10]);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    maze.cells()[i][j] = new Cell(SurfaceType.WALL);
                }
            }
        }

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                maze.cells()[i][j].surface(SurfaceType.WALL);
            }
        }

        SecureRandom random = new SecureRandom();
        Position start = new Position(random.nextInt(1, maze.width() - 1), random.nextInt(1, maze.height() - 1));

        dfs(maze, random, start.y(), start.x());
    }

    private void dfs(Maze maze, SecureRandom random, int y1, int x1) {
        int num = random.nextInt(4);
        maze.cells()[y1][x1].surface(SurfaceType.PATHWAY);

        if (y1 > 1 && num == 0 && maze.cells()[y1 - 1][x1].surface() != SurfaceType.PATHWAY) {
            dfs(maze, random, y1 - 1, x1);
        }
        if (x1 > 1 && num == 1 && maze.cells()[y1][x1 - 1].surface() != SurfaceType.PATHWAY) {
            dfs(maze, random, y1, x1 - 1);
        }
        if (y1 < maze.height() - 1 && num == 0 && maze.cells()[y1 + 1][x1].surface() != SurfaceType.PATHWAY) {
            dfs(maze, random, y1 + 1, x1);
        }
        if (x1 < maze.width() && num == 0 && maze.cells()[y1][x1 + 1].surface() != SurfaceType.PATHWAY) {
            dfs(maze, random, y1, x1 + 1);
        }
    }

    @Override
    public String toString() {
        return "RecursiveBackTracker";
    }
}
