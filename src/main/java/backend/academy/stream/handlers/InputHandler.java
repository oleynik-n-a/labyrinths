package backend.academy.stream.handlers;

import lombok.Getter;
import java.io.InputStream;
import java.util.Scanner;

public final class InputHandler {
    @Getter private static final InputHandler INSTANCE = new InputHandler();
    private static Scanner scanner = new Scanner(System.in);

    private InputHandler() {
    }

    public void setInputStream(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public Integer tryReadInteger() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
