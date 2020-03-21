package pl.kapusta.sdanalysis.strategy;

import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;

public class Logger {
    private InterpreterResult interpreterResult;

    public Logger(InterpreterResult interpreterResult) {
        this.interpreterResult = interpreterResult;
    }

    public void put(String log){
        interpreterResult.setResult(interpreterResult.getResult() + log + "\n");
    }
}
