package source;

import commands.ICommand;
import factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class Interpreter {
    private static final Logger logger = LogManager.getLogger(Interpreter.class);

    public void run(@NotNull String strCode, @NotNull Factory factory) {
        try {
            logger.info("Interpreter is started...");
            Context cont = new Context(strCode);
            int len = strCode.length();
            logger.info("Start of code processing...");
            while (!cont.isEnd()) {
                char sym = cont.getElem();
                ICommand command = factory.create(String.valueOf(sym));
                command.doAction(cont);
                cont.incItrCode();
            }
            logger.info("Interpreter is finished SUCCESSFUL.");
        }
        catch (NullPointerException e) {
            logger.info("...FAILED.");
            logger.error("Undefined symbol in the program");
            System.out.println("Undefined symbol in the program");
        }
    }
}
