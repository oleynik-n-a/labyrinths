package backend.academy;

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
            printHandler.printMessageLn(applicationProcessor.getApplicationMenu());
            Integer input = inputHandler.tryReadInteger();
            if (input == null) {
                continue;
            }
            String response = applicationProcessor.sendCommand(input);
            if (response != null) {
                printHandler.printMessageLn(response);
            }
        }
//        Cell[][] cells = {{new Cell(SurfaceType.START), new Cell(SurfaceType.WALL), new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.PATHWAY)},
//            {new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.STAR), new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.WALL)},
//            {new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.WALL), new Cell(SurfaceType.FINISH), new Cell(SurfaceType.WALL)},
//            {new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.PATHWAY), new Cell(SurfaceType.WALL)}};
    }
}
