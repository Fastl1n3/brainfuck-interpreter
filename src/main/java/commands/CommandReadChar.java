package commands;

import org.jetbrains.annotations.NotNull;
import source.Context;

import java.util.Scanner;
public class CommandReadChar implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        Scanner scanner = new Scanner(System.in);
        cont.setByte((byte)scanner.next().charAt(0));
    }
}
