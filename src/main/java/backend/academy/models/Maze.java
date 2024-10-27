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
}
