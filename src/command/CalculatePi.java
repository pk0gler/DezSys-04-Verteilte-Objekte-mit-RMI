package command;

import java.io.Serializable;

public class CalculatePi implements Command, Serializable {
    private static final long serialVersionUID = 2478264756614L;
    private int digits;

    @Override
    public void execute() {
        calculate();
        returnResult();
    }

    public void calculate() {
        System.out.println("Calculating");
    }

    public void returnResult() {
        System.out.println("Returning Result");
    }
}
