package commands;
import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandPrevUnit implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        cont.decIdx();
    }
}
