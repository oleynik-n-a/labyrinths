package backend.academy.stream.handlers;

import backend.academy.models.Maze;
import lombok.Getter;
import lombok.Setter;
import java.io.PrintStream;

public class PrintHandler {
    @Getter private static final PrintHandler INSTANCE = new PrintHandler();
    @Setter static PrintStream printStream = System.out;

    private PrintHandler() {
    }

    public void printMessage(String message) {
        printStream.println(message);
    }

    public void printMaze(Maze maze) {
        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                printStream.print(maze.cells()[i][j]);
            }
            printStream.println();
        }
    }
}
