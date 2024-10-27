package backend.academy.algorithms.generators;

import backend.academy.algorithms.exceptions.GenerationErrorException;
import backend.academy.models.Maze;
import backend.academy.models.SurfaceType;
import java.security.SecureRandom;

public class StarGenerator implements GeneratorAlgorithm {
    private static final int PATHWAYS_PER_STAR = 9;

    @Override
    public void execute(Maze maze) throws GenerationErrorException {
        if (maze == null) {
            throw new GenerationErrorException("Maze is empty");
        }

        int pathwaysCount = 0;
        for (int y = 0; y < maze.height(); ++y) {
            for (int x = 0; x < maze.width(); ++x) {
                if (maze.cells()[y][x].surface() == SurfaceType.PATHWAY) {
                    ++pathwaysCount;
                }
            }
        }

        int starsCount = pathwaysCount / PATHWAYS_PER_STAR;
        SecureRandom random = new SecureRandom();
        for (int y = 0; y < maze.height(); ++y) {
            for (int x = 0; x < maze.width(); ++x) {
                if (starsCount == 0) {
                    return;
                }
                if (random.nextInt(PATHWAYS_PER_STAR) == 0) {
                    maze.cells()[y][x].surface(SurfaceType.STAR);
                    --starsCount;
                }
            }
        }
    }
}
