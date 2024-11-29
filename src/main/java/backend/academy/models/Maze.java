package backend.academy.models;

public class Maze {
    private final Cell[][] cells;

    public Maze(int height, int width) {
        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setSurface(y, x, Cell.WALL);
            }
        }
    }

    public Maze(Cell[][] cells) {
        this.cells = cells;
    }

    public int height() {
        return cells.length;
    }

    public int width() {
        return cells[0].length;
    }

    public Cell getSurface(int y, int x) {
        return cells[y][x];
    }

    public void setSurface(int y, int x, Cell surface) {
        cells[y][x] = surface;
    }

    public void setSurface(Position position, Cell surface) {
        setSurface(position.y(), position.x(), surface);
    }

    public void clearMaze() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                setSurface(y, x, Cell.WALL);
            }
        }
    }

    public void clearMazeObjects(Cell cell) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (cells[y][x] == cell) {
                    setSurface(y, x, Cell.PATHWAY);
                }
            }
        }
    }

    public String toString() {
        StringBuilder printableMaze = new StringBuilder();
        printableMaze.append("\n");
        for (int y = 0; y < height(); ++y) {
            for (int x = 0; x < width(); ++x) {
                printableMaze.append(cells[y][x].value());
            }
            printableMaze.append("\n");
        }
        return printableMaze.toString();
    }
}
