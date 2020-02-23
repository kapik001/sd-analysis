package pl.kapusta.sdanalysis.strategy;

import org.renjin.gnur.api.S;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.OperationResult;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;
import pl.kapusta.sdanalysis.stocksource.StockData;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Strategy {
    private List<String> stockNames;
    private String onStartScript;
    private String onNextDayScript;
    private String onEndScript;

    public void setStockNames(List<String> stockNames) {
        this.stockNames = stockNames;
    }

    public void setOnStartScript(String onStartScript) {
        this.onStartScript = onStartScript;
    }

    public void setOnNextDayScript(String onNextDayScript) {
        this.onNextDayScript = onNextDayScript;
    }

    public void setOnEndScript(String onEndScript) {
        this.onEndScript = onEndScript;
    }

    public InterpreterResult executeStrategy() {
        InterpreterResult result = new InterpreterResult();
        if (this.stockNames == null) {
            result.setOperationResult(OperationResult.NOK);
            result.setResult("Missing stock names");
            return result;
        }
        if (this.onStartScript == null) {
            result.setOperationResult(OperationResult.NOK);
            result.setResult("Missing on start script");
            return result;
        }
        if (this.onNextDayScript == null) {
            result.setOperationResult(OperationResult.NOK);
            result.setResult("Missing on next day script");
            return result;
        }
        if (this.onEndScript == null) {
            result.setOperationResult(OperationResult.NOK);
            result.setResult("Missing on end script");
            return result;
        }
        return result;
    }
}
