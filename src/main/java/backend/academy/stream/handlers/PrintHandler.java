package backend.academy.stream.handlers;

import java.io.PrintStream;
import lombok.Getter;

public final class PrintHandler {
    @Getter private static final PrintHandler INSTANCE = new PrintHandler();
    private static PrintStream printStream;

    private PrintHandler() {
    }

    public void setPrintStream(PrintStream printStream) {
        PrintHandler.printStream = printStream;
    }

    public void printMessage(String message) {
        printStream.print(message);
    }

    public void printMessageLn(String message) {
        printStream.println(message);
    }
}
