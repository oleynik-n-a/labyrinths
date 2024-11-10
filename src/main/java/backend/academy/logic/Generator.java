package backend.academy.logic;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.algorithms.generators.GeneratorAlgorithm;
import backend.academy.algorithms.generators.Kruskal;
import backend.academy.algorithms.generators.RecursiveBackTracker;
import backend.academy.algorithms.generators.StarGenerator;
import backend.academy.models.Maze;

public final class Generator {
    private int current = 0;
    private final GeneratorAlgorithm[] algorithms;
    private final StarGenerator starGenerator;

    public Generator() {
        this.algorithms = new GeneratorAlgorithm[]{new RecursiveBackTracker(), new Kruskal()};
        this.starGenerator = new StarGenerator();
    }

    public void switchAlgorithm() {
        ++current;
        if (current == algorithms.length) {
            current = 0;
        }
    }

    public GeneratorAlgorithm algorithm() {
        return algorithms[current];
    }

    public GeneratorAlgorithm nextAlgorithm() {
        return algorithms[current + 1 == algorithms.length ? 0 : current + 1];
    }

    public void generate(Maze maze) {
        if (maze == null) {
            throw new MazeException(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE);
        }
        algorithms[current].execute(maze);
    }

    public void generateStars(Maze maze) {
        if (maze == null) {
            throw new MazeException(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE);
        }
        starGenerator.execute(maze);
    }
}
