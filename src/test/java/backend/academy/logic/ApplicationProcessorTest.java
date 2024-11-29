package backend.academy.logic;

import backend.academy.algorithms.exceptions.MazeException;
import backend.academy.stream.handlers.InputHandler;
import backend.academy.stream.handlers.PrintHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ApplicationProcessorTest {
    private ApplicationProcessor applicationProcessor;

    @BeforeAll
    static void setUpBeforeAll() {
        PrintHandler.INSTANCE().setPrintStream(System.out);
    }

    @BeforeEach
    void setUpBeforeEach() {
        applicationProcessor = new ApplicationProcessor(InputHandler.INSTANCE(), PrintHandler.INSTANCE());
    }

    @Test
    void testSetHeight() {
        int exceptionsThrown = 0;

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("50\n".getBytes()));
        try {
            applicationProcessor.executeCommand(3);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect height"));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("30\n".getBytes()));
        try {
            applicationProcessor.executeCommand(3);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect height"));
        }

        assert(exceptionsThrown == 1);
    }

    @Test
    void testSetWidth() {
        int exceptionsThrown = 0;

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("50\n".getBytes()));
        try {
            applicationProcessor.executeCommand(4);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect width"));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("30\n".getBytes()));
        try {
            applicationProcessor.executeCommand(4);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect width"));
        }

        assert(exceptionsThrown == 1);
    }

    @Test
    void testSetStartPosition() {
        int exceptionsThrown = 0;

        try {
            applicationProcessor.executeCommand(7);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("30\n30\n".getBytes()));
        applicationProcessor.executeCommand(3);
        applicationProcessor.executeCommand(4);

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("50\n50\n".getBytes()));
        try {
            applicationProcessor.executeCommand(7);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect x or y"));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("20\n20\n".getBytes()));
        try {
            applicationProcessor.executeCommand(7);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Start or finish position can't be in the wall"));
        }

        assert(exceptionsThrown == 3);
    }

    @Test
    void testSetFinishPosition() {
        int exceptionsThrown = 0;

        try {
            applicationProcessor.executeCommand(8);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals(ApplicationProcessor.NULL_MAZE_ERROR_MESSAGE));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("30\n30\n".getBytes()));
        applicationProcessor.executeCommand(3);
        applicationProcessor.executeCommand(4);

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("50\n50\n".getBytes()));
        try {
            applicationProcessor.executeCommand(8);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Incorrect x or y"));
        }

        InputHandler.INSTANCE().setInputStream(new ByteArrayInputStream("20\n20\n".getBytes()));
        try {
            applicationProcessor.executeCommand(8);
        } catch (MazeException e) {
            ++exceptionsThrown;
            assert(e.getMessage().equals("Start or finish position can't be in the wall"));
        }

        assert(exceptionsThrown == 3);
    }
}
