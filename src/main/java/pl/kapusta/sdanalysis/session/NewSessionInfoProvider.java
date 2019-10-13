package pl.kapusta.sdanalysis.session;

import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;

import java.util.List;

public interface NewSessionInfoProvider {
    List<InterpreterResult> getInitInformations();
}
