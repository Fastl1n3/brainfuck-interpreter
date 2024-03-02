package factory;

import commands.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Scanner;

public class Factory {
    private static final Logger logger = LogManager.getLogger(Factory.class);
    private HashMap<String, String> namesMap = new HashMap<>();
    public Factory(@NotNull Scanner scanner) {
        logger.info("Filling the factory...");
        while(scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] names = str.split("->");
            namesMap.put(names[0], names[1]);
        }
        logger.info("Filling in the factory was SUCCESSFUL.");
    }
    public ICommand create(@NotNull String commandName) {
        if (!namesMap.containsKey(commandName)) {
            return null;
        }
        String nameClass = namesMap.get(commandName);
        ICommand command = null;
        try {
            Class clazz = Class.forName(nameClass).getClassLoader().loadClass(nameClass);
            command = (ICommand) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Error cast class");
        }
        return command;
    }
}
