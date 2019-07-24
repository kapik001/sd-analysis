package pl.kapusta.sdanalysis.rinterpreter;

import java.io.Serializable;

public class InterpreterResult implements Serializable {
    private String result;
    private OperationResult operationResult;

    public InterpreterResult() {
    }

    public InterpreterResult(Object o) {
        if(o != null){
            result = o.toString();
        }
        operationResult = OperationResult.OK;
    }

    public InterpreterResult(Exception e) {
        if(e != null){
            result = e.getLocalizedMessage();
        }
        operationResult = OperationResult.NOK;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult;
    }
}
