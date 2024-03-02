package source;

import factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    static StringBuilder parsingCodeFile(String programName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(programName))) {
            StringBuilder strCode = new StringBuilder();
            while(scanner.hasNextLine()) {
                strCode.append(scanner.next());
            }
            return strCode;
        }
    }
    public static void main(String[] args) {
        logger.info("The program is running...");
        String progName;
        String cfgName;
        if (args.length == 0) {
            System.out.println("Please, enter the both files");
            Scanner scanner = new Scanner(System.in);
            progName = scanner.next();
            cfgName = scanner.next();
        }
        else if(args.length == 1) {
            System.out.println("Please, enter the config file");
            Scanner scanner = new Scanner(System.in);
            progName = args[0];
            cfgName = scanner.next();
        }
        else {
            progName = args[0];
            cfgName = args[1];
        }
        StringBuilder strCode;
        try (Scanner scanner = new Scanner(new File(cfgName))) {
            logger.info("Processing input files...");
            strCode = parsingCodeFile(progName);
            Factory factory = new Factory(scanner);
            logger.info("Processing of input files was SUCCESSFUL.");
            Interpreter intpr = new Interpreter();
            intpr.run(strCode.toString(), factory);
            logger.info("The program has finished its work.\n\n");
        }
        catch (FileNotFoundException e) {
            logger.info("...FAILED.");
            logger.error(e.getMessage());
            logger.info("The program has finished its work.\n\n");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            logger.info("...FAILED.");
            logger.error("Invalid syntax in the config file");
            System.out.println("Invalid syntax in the config file");
            logger.info("The program has finished its work.\n\n");
        }
    }
}