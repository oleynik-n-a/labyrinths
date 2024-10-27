package backend.academy.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class Maze {
    private Cell[][] cells;

    public Maze(Cell[][] cells) {
        this.cells = cells;
    }

    public int height() {
        return cells.length;
    }

    public int width() {
        return cells[0].length;
    }

    public String toString() {
        StringBuilder printableMaze = new StringBuilder();
        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                printableMaze.append(cells[i][j].surface().value());
            }
            printableMaze.append("\n");
        }
        return printableMaze.toString();
    }
}
