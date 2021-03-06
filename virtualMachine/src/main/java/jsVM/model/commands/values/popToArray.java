package jsVM.model.commands.values;

import jsVM.model.commands.abstracts.Command;
import jsVM.model.args.ConstantArgument;

import java.util.List;

public class popToArray extends Command {

    public void execute() throws Exception {
        if (args.size() == 0) {
            Object arr = virtualMachine.popStack().getValue();
            Object index = virtualMachine.popStack().getValue();
            ConstantArgument value = virtualMachine.popStack();
            if (arr instanceof List) {
                if(index instanceof Integer) {
                    if (((List) arr).size() > (Integer)index) {
                        ((List) arr).set((Integer)index, value);
                    } else {
                        for (int i = ((List) arr).size(); i < (Integer)index; i++) {
                            ((List) arr).add("");
                        }
                        ((List) arr).add(value);
                    }
                }
                else {
                    throw new Exception("popToArray: stack second value is not integer");
                }
            } else {
                throw new Exception("popToArray: stack top value is not array");
            }
        } else {
            throw new Exception("popToArray does not need any arguments");
        }
    }
}
