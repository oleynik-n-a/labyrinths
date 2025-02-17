package backend.academy.logic;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.models.Cell;
import backend.academy.models.Maze;
import backend.academy.models.Position;
import backend.academy.stream.handlers.InputHandler;
import backend.academy.stream.handlers.PrintHandler;

public class ApplicationProcessor {
    private final InputHandler inputHandler;
    private final PrintHandler printHandler;

    private Maze maze = null;
    private Position startPosition = null;
    private Position finishPosition = null;
    private Integer height = null;
    private Integer width = null;
    private final Generator generator = new Generator();
    private final Solver solver = new Solver();

    public static final String NULL_MAZE_ERROR_MESSAGE = "Height or width not set";
    private static final int MIN_LABYRINTH_SIDE_SIZE = 5;
    private static final int MAX_LABYRINTH_SIDE_SIZE = 40;

    public ApplicationProcessor(InputHandler inputHandler, PrintHandler printHandler) {
        this.inputHandler = inputHandler;
        this.printHandler = printHandler;
    }

    public String getApplicationMenu() {
        return String.format("""

            Welcome to maze generator application!
            %s

            Menu:
            \t1. Switch generator algorithm: %s (--> %s).
            \t2. Switch solver algorithm: %s (--> %s).
            \t3. Set height: %d.
            \t4. Set width: %d.
            \t5. Generate maze.
            \t6. Generate stars.
            \t7. Set start position.
            \t8. Set finish position.
            \t9. Solve maze.
            \t10. Solve maze with all stars.
            \t11. Exit.

            Input:\s""",
            (maze == null ? "" : maze.toString()), generator.algorithm().toString(),
            generator.nextAlgorithm().toString(), solver.algorithm().toString(), solver.nextAlgorithm().toString(),
            height, width
        );
    }

    @SuppressWarnings({"cyclomaticcomplexity", "magicnumber"})
    public void executeCommand(int num) {
        switch (num) {
            case 1:
                generator.switchAlgorithm();
                break;
            case 2:
                solver.switchAlgorithm();
                break;
            case 3:
                setHeight();
                break;
            case 4:
                setWidth();
                break;
            case 5:
                generator.generate(maze);
                break;
            case 6:
                generator.generateStars(maze);
                break;
            case 7:
                setStartPosition();
                break;
            case 8:
                setFinishPosition();
                break;
            case 9:
                solver.solve(maze);
                break;
            case 10:
                solver.solveWithStars(maze);
                break;
            default:
                System.exit(0);
        }
        if (height != null && width != null && (maze == null || maze.height() != height || maze.width() != width)) {
            maze = new Maze(height, width);
        }
    }

    private Position setPosition() {
        if (height == null || width == null) {
            throw new MazeException(NULL_MAZE_ERROR_MESSAGE);
        }

        printHandler.printMessage(String.format("Input y (0 < y < %d): ", height - 1));
        Integer y = inputHandler.tryReadInteger();
        printHandler.printMessage(String.format("Input x (0 < x < %d): ", width - 1));
        Integer x = inputHandler.tryReadInteger();

        if (y == null || x == null || y < 0 || x < 0 || y >= height || x >= width) {
            throw new MazeException("Incorrect x or y");
        }
        if (maze.getSurface(y, x) == Cell.WALL) {
            throw new MazeException("Start or finish position can't be in the wall");
        }

        return new Position(y, x);
    }

    private void setStartPosition() {
        if (maze == null) {
            throw new MazeException(NULL_MAZE_ERROR_MESSAGE);
        }

        startPosition = setPosition();
        maze.clearMazeObjects(Cell.SOLUTION);
        maze.clearMazeObjects(Cell.START);
        maze.setSurface(startPosition, Cell.START);
    }

    private void setFinishPosition() {
        if (maze == null) {
            throw new MazeException(NULL_MAZE_ERROR_MESSAGE);
        }

        finishPosition = setPosition();
        maze.clearMazeObjects(Cell.SOLUTION);
        maze.clearMazeObjects(Cell.FINISH);
        maze.setSurface(finishPosition, Cell.FINISH);
    }

    private void setHeight() {
        printHandler.printMessage(String.format("Input odd height (%d < height < %d): ", MIN_LABYRINTH_SIDE_SIZE,
            MAX_LABYRINTH_SIDE_SIZE));
        Integer inputHeight = inputHandler.tryReadInteger();
        if (inputHeight == null || inputHeight < MIN_LABYRINTH_SIDE_SIZE || inputHeight >= MAX_LABYRINTH_SIDE_SIZE) {
            throw new MazeException("Incorrect height");
        }
        if (inputHeight % 2 == 0) {
            ++inputHeight;
        }
        this.height = inputHeight;
        checkPositions();
    }

    private void setWidth() {
        printHandler.printMessage(String.format("Input odd width (%d < width < %d): ", MIN_LABYRINTH_SIDE_SIZE,
            MAX_LABYRINTH_SIDE_SIZE));
        Integer inputWidth = inputHandler.tryReadInteger();
        if (inputWidth == null || inputWidth < MIN_LABYRINTH_SIDE_SIZE || inputWidth >= MAX_LABYRINTH_SIDE_SIZE) {
            throw new MazeException("Incorrect width");
        }
        if (inputWidth % 2 == 0) {
            ++inputWidth;
        }
        this.width = inputWidth;
        checkPositions();
    }

    private void checkPositions() {
        if (startPosition != null && (height >= startPosition.y() || width >= startPosition.x())) {
            startPosition = null;
        }
        if (finishPosition != null && (height >= finishPosition.y() || width >= finishPosition.x())) {
            finishPosition = null;
        }
    }
}
