package backend.academy.logic;

import backend.academy.models.Maze;
import backend.academy.models.Position;
import backend.academy.models.SurfaceType;
import backend.academy.stream.handlers.InputHandler;
import backend.academy.stream.handlers.PrintHandler;

public class ApplicationProcessor {
    private final InputHandler inputHandler;
    private final PrintHandler printHandler;

    private final Maze maze = null;
    private final Position startPosition = new Position(2, 2);
    private final Position finishPosition = new Position(5, 5);
    private int height = 10;
    private int width = 10;
    private final Generator generator = new Generator();
    private final Solver solver = new Solver();

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
            \t3. Set start position.
            \t4. Set finish position.
            \t5. Set height: %d.
            \t6. Set width: %d.
            \t7. Generate stars.
            \t8. Generate maze.
            \t9. Solve maze.
            \t10. Exit.


            """,
            (maze == null ? "" : maze.toString()), generator.algorithm().toString(), generator.nextAlgorithm().toString(),
            solver.algorithm().toString(), solver.nextAlgorithm().toString(), height, width
        );
    }

    public String sendCommand(int num) {
        switch (num) {
            case 1:
                generator.switchAlgorithm();
                break;
            case 2:
                solver.switchAlgorithm();
                break;
            case 3:
                setStartPosition();
                break;
            case 4:
                setFinishPosition();
                break;
            case 5:
                setHeight();
                break;
            case 6:
                setWidth();
                break;
            case 7:
                return generator.generateStars(maze);
            case 8:
                return generator.generate(maze);
            case 9:
                return solver.solve(maze);
            case 10:
                System.exit(0);
        }
        return null;
    }

    private void setPosition(Position position) {
        printHandler.printMessage("Input y: ");
        Integer y = inputHandler.tryReadInteger();
        printHandler.printMessage("Input x: ");
        Integer x = inputHandler.tryReadInteger();

        if (y == null || x == null || y < 0 || x < 0 || y >= height || x >= width) {
            return;
        }

        position.y(y);
        position.x(x);

        if (maze != null && maze.cells()[position.y()][position.x()].surface() == SurfaceType.PATHWAY) {
            for (int i = 0; i < maze.height() + height; i++) {
                for (int j = 0; j < maze.width() + width; j++) {
                    if (maze.cells()[i][j].surface() == SurfaceType.START) {
                        maze.cells()[position.y()][position.x()].surface(SurfaceType.START);
                        maze.cells()[i][j].surface(SurfaceType.PATHWAY);
                        return;
                    }
                }
            }
        }
    }

    private void setStartPosition() {
        setPosition(startPosition);
    }

    private void setFinishPosition() {
        setPosition(finishPosition);
    }

    private void setHeight() {
        printHandler.printMessage("Input height: ");
        Integer height = inputHandler.tryReadInteger();
        if (height == null || height < 0 || height >= startPosition.y() || height >= finishPosition.y()) {
            return;
        }
        this.height = height;
    }

    private void setWidth() {
        printHandler.printMessage("Input width: ");
        Integer width = inputHandler.tryReadInteger();
        if (width == null || width < 0 || width >= startPosition.x() || width >= finishPosition.x()) {
            return;
        }
        this.width = width;
    }
}
