package ru.vsu.vm.model.commands.impls.values;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.args.VariableArgument;
import ru.vsu.vm.model.commands.Command;

public class decl extends Command{
    public void execute() throws Exception {
        if(args.size()==1){
            if(args.get(0) instanceof VariableArgument) {
                String name = ((VariableArgument) args.get(0)).getName();
                virtualMachine.setVarValue(name, new ConstantArgument("undefined"));
            }
            else{
                throw new Exception("decl: argument is not variable");
            }
        }
        else{
            throw new Exception("decl needs only 1 argument");
        }
    }
}
