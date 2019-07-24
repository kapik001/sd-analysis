package pl.kapusta.sdanalysis.rinterpreter;

import org.renjin.script.RenjinScriptEngineFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Service
public class RRunner {

    private ScriptEngine engine;

    @PostConstruct
    public void init() {
        RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
        engine = factory.getScriptEngine();
    }

    public InterpreterResult run(String command) {
        try {
            return new InterpreterResult(engine.eval(command));
        } catch (Exception e) {
            return new InterpreterResult(e);
        }
    }
}
