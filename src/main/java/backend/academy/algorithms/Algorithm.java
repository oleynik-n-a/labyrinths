package backend.academy.algorithms;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.models.Maze;

public interface Algorithm {
    void execute(Maze maze) throws MazeException;
    String toString();
}
