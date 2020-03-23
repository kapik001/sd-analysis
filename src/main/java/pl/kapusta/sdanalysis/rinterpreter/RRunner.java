package pl.kapusta.sdanalysis.rinterpreter;

import org.renjin.script.RenjinScriptEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.kapusta.sdanalysis.stocksource.StockDataResolver;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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
            engine.eval("library(rpart)");
            engine.eval("library(aod)");
            engine.eval("library('org.renjin.cran:neuralnet')");
            engine.put("stockData", stockDataResolver);
        } catch (ScriptException e) {
            LOG.error("Exception when creating R interpreter", e);
        }
    }

    public InterpreterResult run(String command) {
        try {
            InterpreterResult result = new InterpreterResult();
            result.setResult("");
            engine.put("logger", new pl.kapusta.sdanalysis.strategy.Logger(result));
            String commandResult = engine.eval(command).toString();
            if (!"NULL".equals(commandResult)) {
                result.setResult(result.getResult() + commandResult + "\n");
            }
            result.setOperationResult(OperationResult.INFO);
            return result;
        } catch (Exception e) {
            return new InterpreterResult(e);
        }
    }

    public void put(String objectName, Object object) {
        engine.put(objectName, object);
    }
}
