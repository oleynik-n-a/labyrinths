package backend.academy.models;

import lombok.Getter;

@Getter public enum Cell {
    WALL("⬜"),
    PATHWAY("⬛"),
    STAR("⭐"),
    SOLUTION("✅"),
    START("⛺"),
    FINISH("⛳");

    private final String value;

    Cell(final String value) {
        this.value = value;
    }
}
