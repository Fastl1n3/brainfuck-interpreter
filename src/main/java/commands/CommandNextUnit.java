package commands;
import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandNextUnit implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        cont.incIdx();
    }
}
