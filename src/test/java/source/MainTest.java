package source;

import commands.*;
import factory.Factory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testFactory() throws Exception {
        Factory factory = new Factory(new Scanner(">->commands.CommandNextUnit"));
        ICommand command = factory.create(">");
        assertTrue(command instanceof CommandNextUnit);
    }
    @Test
    public void testProgrNotFound() {
        Main.main(new String[]{"src/test/test.b", "src/test/testPrograms/cfg.txt"});
        assertEquals("src/test/test.b (No such file or directory)", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testCfgNotFound() {
        Main.main(new String[]{"src/test/testPrograms/hello.b", "src/test/testPrograms/cfgg.txt"});
        assertEquals("src/test/testPrograms/cfgg.txt (No such file or directory)", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testNullPtr() throws Exception {
        Interpreter inter = new Interpreter();
        Factory factory = new Factory(new Scanner(">->commands.CommandNextUnit\n+->commands.CommandInc"));
        inter.run(">+>+$>+", factory);
        assertEquals("Undefined symbol in the program", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testInvalidCfg() {
        Main.main(new String[]{"src/test/testPrograms/alphabet.b","src/test/testPrograms/hello.b"});
        assertEquals("Invalid syntax in the config file", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testInvalidCfg2() throws Exception {
        Factory factory = new Factory(new Scanner(">->commands.ComwqqwmandNextUnit\n+->commands.CommandInc"));
        ICommand command = factory.create(">");
        assertEquals("Error cast class", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testDec() {
        Context cont = new Context("[++[++--+]-><]+");
        ICommand command = new CommandDec();
        command.doAction(cont);
        assertEquals(-1, cont.getByte());
    }
    @Test
    public void testInc() {
        Context cont = new Context("[++[++--+]-><]+");
        ICommand command = new CommandInc();
        command.doAction(cont);
        assertEquals(1, cont.getByte());
    }
    @Test
    public void testNextUnit() {
        Context cont = new Context("[++[++--+]-><]+");
        cont.setByte((byte)1);
        cont.incIdx();
        cont.setByte((byte) 2);
        cont.decIdx();
        ICommand command = new CommandNextUnit();
        command.doAction(cont);
        assertEquals(2, cont.getByte());
    }
    @Test
    public void testPrevUnit() {
        Context cont = new Context("[++[++--+]-><]+");
        ICommand command = new CommandPrevUnit();
        cont.setByte((byte)1);
        cont.incIdx();
        cont.setByte((byte) 2);
        command.doAction(cont);
        assertEquals(1, cont.getByte());
    }
    @Test
    public void testPrint() {
        Context cont = new Context("[++[++--+]-><]+");
        cont.setByte((byte) 65);
        ICommand command = new CommandPrintChar();
        command.doAction(cont);
        assertEquals("A", outputStreamCaptor.toString().trim());
    }
    @Test
    public void testRead() {
        InputStream standardIn = System.in;
        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("A".getBytes());
        System.setIn(inputStreamCaptor);
        Context cont = new Context("[++[++--+]-><]+");
        ICommand command = new CommandReadChar();
        command.doAction(cont);
        assertEquals((byte)'A', cont.getByte());
        System.setIn(standardIn);
    }
    @Test
    public void testBeginWhile() {
        Context cont = new Context("[++[++--+]-><]+");
        ICommand command = new CommandBeginWhile();
        command.doAction(cont);
        assertEquals(']', cont.getElem());
    }
    @Test
    public void testEndWhile() {
        Context cont = new Context("[+[>>>----]-><]+");
        for (int i = 0; i < 9; i++) {
            cont.incItrCode();
        }
        cont.setByte((byte) 1);
        ICommand command = new CommandEndWhile();
        command.doAction(cont);
        assertEquals('+', cont.getElem());
    }
    @Test
    public void helloWorld() {
        Main.main(new String[]{"src/test/testPrograms/helloWorld.b","src/test/testPrograms/cfg.txt"});
        assertEquals("Hello World!", outputStreamCaptor.toString().trim());
    }
    @Test
    public void alphabet() {
        Main.main(new String[]{"src/test/testPrograms/alphabet.b","src/test/testPrograms/cfg.txt"});
        assertEquals("Aa Bb Cc Dd Ee Ff \n" +
                "Gg Hh Ii Jj Kk Ll \n" +
                "Mm Nn Oo Pp Qq Rr \n" +
                "Ss Tt Uu Vv Ww Xx \n" +
                "Yy Zz", outputStreamCaptor.toString().trim());
    }
    @Test
    public void codeItself() {
        Main.main(new String[]{"src/test/testPrograms/codeItself.b","src/test/testPrograms/cfg.txt"});
        assertEquals(">+++++>+++>+++>+++++>+++>+++>+++++>++++++>+>++>+++>++++>++++>+++>+++>+++++>+>+" +
                ">++++>+++++++>+>+++++>+>+>+++++>++++++>+++>+++>++>+>+>++++>++++++>++++>++++>+++" +
                ">+++++>+++>+++>++++>++>+>+>+>+>++>++>++>+>+>++>+>+>++++++>++++++>+>+>++++++" +
                ">++++++>+>+>+>+++++>++++++>+>+++++>+++>+++>++++>++>+>+>++>+>+>++>++>+>+>++>++>+" +
                ">+>+>+>++>+>+>+>++++>++>++>+>+++++>++++++>+++>+++>+++>+++>+++>+++>++>+>+>+>+>++" +
                ">+>+>++++>+++>+++>+++>+++++>+>+++++>++++++>+>+>+>++>+++>+++>+++++++>+++>++++>+" +
                ">++>+>+++++++>++++++>+>+++++>++++++>+++>+++>++>++>++>++>++>++>+>++>++>++>++>++" +
                ">++>++>++>++>+>++++>++>++>++>++>++>++>++>+++++>++++++>++++>+++>+++++>++++++>++++" +
                ">+++>+++>++++>+>+>+>+>+++++>+++>+++++>++++++>+++>+++>+++>++>+>+>+>++++>++++" +
                "[[>>>+<<<-]<]>>>>[<<[-]<[-]+++++++[>+++++++++>++++++<<-]>-.>+>[<.<<+>>>-]>]" +
                "<<<[>>+>>>>+<<<<<<-]>++[>>>+>>>>++>>++>>+>>+[<<]>-]>>>-->>-->>+>>+++>>>>+[<<]" +
                "<[[-[>>+<<-]>>]>.[>>]<<[[<+>-]<<]<<]", outputStreamCaptor.toString().trim());
    }
}