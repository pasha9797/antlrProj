package ru.vsu.model;

import ru.vsu.model.abstracts.Element;

public class Equals extends Element {
    public Object proceed() throws Exception {
        if(getOperand(0) instanceof java.lang.Number && getOperand(1) instanceof java.lang.Number){
            return (Float)getOperand(0) == (Float)getOperand(1);
        }
        else if(getOperand(0) instanceof String && getOperand(1) instanceof String){
            return ((String)getOperand(0)).compareTo((String)getOperand(1)) ==0;
        }
        if(getOperand(0) instanceof java.lang.Boolean && getOperand(1) instanceof java.lang.Boolean){
            return (Boolean)getOperand(0) == (Boolean)getOperand(1) ;
        }
        else{
            throw new Exception("Unable to ==");
        }
    }

    @Override
    public String toString() {
        try {
            return getOperand(0).toString() + "==" + getOperand(1).toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}