package backend.academy.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Position {
    private int y;
    private int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
