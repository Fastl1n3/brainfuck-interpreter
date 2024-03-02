package commands;

import org.jetbrains.annotations.NotNull;
import source.Context;

public interface ICommand {
    void doAction(@NotNull Context cont);
}
