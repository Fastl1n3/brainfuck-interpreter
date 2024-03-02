package commands;

import org.jetbrains.annotations.NotNull;
import source.Context;
public class CommandBeginWhile implements ICommand {
    @Override
    public void doAction(@NotNull Context cont) {
        if (cont.getByte() == 0) {
            int brakets = 1;
            while(brakets != 0) {
                cont.incItrCode();
                char sym = cont.getElem();
                if (sym == '[') {
                    brakets++;
                }
                if (sym == ']') {
                    brakets--;
                }
            }
        }
    }
}
