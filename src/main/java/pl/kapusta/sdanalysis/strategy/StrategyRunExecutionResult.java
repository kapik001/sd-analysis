package pl.kapusta.sdanalysis.strategy;

public class StrategyRunExecutionResult {
    private StrategyRunResult strategyRunResult;
    private String message;

    public StrategyRunResult getStrategyRunResult() {
        return strategyRunResult;
    }

    public void setStrategyRunResult(StrategyRunResult strategyRunResult) {
        this.strategyRunResult = strategyRunResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
