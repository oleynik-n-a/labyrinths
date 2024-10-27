package backend.academy.algorithms.generators;

import backend.academy.algorithms.Algorithm;
import backend.academy.models.Maze;
import org.openjdk.jmh.generators.core.GenerationException;

public interface GeneratorAlgorithm extends Algorithm {
    void execute(Maze maze) throws GenerationException;
}
