package pl.kapusta.sdanalysis.session;

import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;

import java.util.List;

public interface SessionRepository {
    void putInterpreterResult(InterpreterResult interpreterResult);
    List<InterpreterResult> getStoredInterpreterResults();
}
