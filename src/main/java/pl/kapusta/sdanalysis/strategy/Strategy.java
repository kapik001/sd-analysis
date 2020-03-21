package pl.kapusta.sdanalysis.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.OperationResult;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;
import pl.kapusta.sdanalysis.stocksource.StockData;
import pl.kapusta.sdanalysis.stocksource.StockDataResolver;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Strategy {

    @Autowired
    private RRunner runner;

    @Autowired
    private StockDataResolver stockDataResolver;

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
        InterpreterResult result = validateBeforeStrategy();
        if (OperationResult.NOK.equals(result.getOperationResult())) {
            return result;
        } else {
            return runStrategy();
        }
    }

    private InterpreterResult validateBeforeStrategy() {
        InterpreterResult result = new InterpreterResult();
        result.setOperationResult(OperationResult.OK);
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

    private InterpreterResult runStrategy() {
        InterpreterResult result = new InterpreterResult();
        result.setResult("");
        runner.put("logger", new Logger(result));
        result.setOperationResult(OperationResult.INFO);
        for (String stockName : stockNames) {
            result.setResult(result.getResult() + "Loading data for:" + stockName + "\n");
            List<StockData> data = stockDataResolver.load(stockName, 365);
            if (data == null || data.isEmpty()) {
                result.setResult(result.getResult() + "No data loaded for:" + stockName + "\n");
            } else {
                runner.put("data", data);
                runner.put("buyer", new Buyer());
                result.setResult(result.getResult() + "Running on start script" + "\n");
                result.setResult(result.getResult() + onStartScript + "\n");
                runCommandAndPropagateToResult(onStartScript, result);
                result.setResult(result.getResult() + "Running on next day script" + "\n");
                result.setResult(result.getResult() + onNextDayScript + "\n");
                runner.put("noOfDays", data.size());
                for (int i = 1; i <= data.size(); i++) {
                    runner.put("today", data.get(i - 1));
                    runner.put("iter", i);
                    runCommandAndPropagateToResult(onNextDayScript, result);
                }
                result.setResult(result.getResult() + "Running on end day script" + "\n");
                result.setResult(result.getResult() + onEndScript + "\n");
                runCommandAndPropagateToResult(onEndScript, result);
            }
        }
        return result;
    }

    private void runCommandAndPropagateToResult(String command, InterpreterResult result) {
        InterpreterResult inResult = runner.run(command);
        result.setResult(result.getResult() + resolveResult(inResult.getResult()) + "\n");
        if (OperationResult.NOK.equals(inResult.getOperationResult())) {
            result.setOperationResult(OperationResult.NOK);
        }
    }

    private String resolveResult(String result) {
        return result != null ? ("NULL".equals(result) ? "" : result) : "";
    }
}
