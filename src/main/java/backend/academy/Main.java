package backend.academy;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.logic.ApplicationProcessor;
import backend.academy.stream.handlers.InputHandler;
import backend.academy.stream.handlers.PrintHandler;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = InputHandler.INSTANCE();
        inputHandler.setInputStream(System.in);
        PrintHandler printHandler = PrintHandler.INSTANCE();
        printHandler.setPrintStream(System.out);

        ApplicationProcessor applicationProcessor = new ApplicationProcessor(inputHandler, printHandler);

        while (true) {
            printHandler.printMessage(applicationProcessor.getApplicationMenu());
            Integer input = inputHandler.tryReadInteger();
            if (input == null) {
                continue;
            }
            try {
                applicationProcessor.executeCommand(input);
            } catch (MazeException e) {
                printHandler.printMessageLn(e.getMessage());
            }
        }
    }
}
