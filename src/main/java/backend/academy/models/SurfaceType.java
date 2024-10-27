package backend.academy.models;

import lombok.Getter;

public enum SurfaceType {
    WALL("⬜"),
    PATHWAY("⬛"),
    STAR("⭐"),
    SOLUTION("✅"),
    START("⛺"),
    FINISH("⛳");

    @Getter private final String value;

    SurfaceType(final String value) {
        this.value = value;
    }
}
