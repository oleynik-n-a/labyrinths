package backend.academy.stream.handlers;

import backend.academy.models.Maze;
import java.io.PrintStream;
import lombok.Getter;
import lombok.Setter;

public final class PrintHandler {
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
                printStream.print(maze.cells()[i][j].surface().value());
            }
            printStream.println();
        }
    }
}
