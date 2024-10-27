package backend.academy.models;

import lombok.Getter;
import lombok.Setter;

@Getter public class Cell {
    @Setter private SurfaceType surface;
    private Position position;

    public Cell(SurfaceType surface) {
        this.surface = surface;
    }
}
