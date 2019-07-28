package pl.kapusta.sdanalysis.dto;

import java.io.Serializable;

public class CodeToExecuteDTO implements Serializable {
    private String codeToRun;

    public String getCodeToRun() {
        return codeToRun;
    }

    public void setCodeToRun(String codeToRun) {
        this.codeToRun = codeToRun;
    }
}
