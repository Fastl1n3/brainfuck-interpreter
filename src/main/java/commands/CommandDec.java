package commands;

import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandDec implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        byte b = cont.getByte();
        b--;
        cont.setByte(b);
    }
}
