package backend.academy.algorithms.generators;

import backend.academy.algorithms.Algorithm;
import backend.academy.algorithms.exceptions.GenerationErrorException;
import backend.academy.models.Maze;

public interface GeneratorAlgorithm extends Algorithm {
    void execute(Maze maze) throws GenerationErrorException;
}
