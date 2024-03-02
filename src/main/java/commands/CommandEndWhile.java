package commands;
import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandEndWhile implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        if (cont.getByte() != 0) {
            int brakets = 1;
            while(brakets != 0) {
                cont.decItrCode();
                char sym = cont.getElem();
                if (sym == '[') {
                    brakets--;
                }
                if (sym == ']') {
                    brakets++;
                }
            }
            cont.decItrCode();
        }
    }
}
