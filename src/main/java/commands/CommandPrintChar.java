package commands;
import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandPrintChar implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        System.out.print((char)cont.getByte());
    }
}
