package pl.kapusta.sdanalysis.rinterpreter;

import org.renjin.script.RenjinScriptEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.kapusta.sdanalysis.stocksource.StockDataProviderImpl;
import pl.kapusta.sdanalysis.stocksource.StockDataResolver;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Service
public class RRunner {

    private ScriptEngine engine;
    private static final Logger LOG = LoggerFactory.getLogger(RRunner.class);

    @Autowired
    private StockDataResolver stockDataResolver;

    @PostConstruct
    public void init() {
        try {
            RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
            engine = factory.getScriptEngine();
            engine.eval("import(pl.kapusta.sdanalysis.stocksource.StockDataResolver)");
            engine.put("stockData", stockDataResolver);

        } catch (ScriptException e) {
            LOG.error("Exception when creating R interpreter", e);
        }
    }

    public InterpreterResult run(String command) {
        try {
            return new InterpreterResult(engine.eval(command));
        } catch (Exception e) {
            return new InterpreterResult(e);
        }
    }
}
