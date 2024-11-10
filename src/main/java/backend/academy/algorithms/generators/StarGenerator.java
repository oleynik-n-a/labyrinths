package backend.academy.algorithms.generators;

import backend.academy.algorithms.exceptions.GenerationErrorException;
import backend.academy.models.Cell;
import backend.academy.models.Maze;
import java.security.SecureRandom;

public class StarGenerator implements GeneratorAlgorithm {
    private static final int PATHWAYS_PER_STAR = 20;

    @Override
    public void execute(Maze maze) throws GenerationErrorException {
        if (maze == null) {
            throw new GenerationErrorException("Height or width not set");
        }

        int pathwaysCount = 0;
        for (int y = 0; y < maze.height(); ++y) {
            for (int x = 0; x < maze.width(); ++x) {
                if (maze.getSurface(y, x) == Cell.PATHWAY) {
                    ++pathwaysCount;
                }
            }
        }

        if (pathwaysCount == 0) {
            throw new GenerationErrorException("No pathways found");
        }

        maze.clearMazeObjects(Cell.SOLUTION);
        maze.clearMazeObjects(Cell.STAR);

        int starsCount = pathwaysCount / PATHWAYS_PER_STAR;
        SecureRandom random = new SecureRandom();
        while (starsCount > 0) {
            int y = random.nextInt(maze.height());
            int x = random.nextInt(maze.width());
            if (maze.getSurface(y, x) == Cell.PATHWAY) {
                maze.setSurface(y, x, Cell.STAR);
                --starsCount;
            }
        }
    }
}
