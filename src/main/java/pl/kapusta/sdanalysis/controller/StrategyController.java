package pl.kapusta.sdanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kapusta.sdanalysis.dto.CodeToExecuteDTO;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.strategy.Strategy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    private Strategy strategy;

    @RequestMapping(value = "on-start", method = RequestMethod.POST)
    public void onStart(@RequestBody CodeToExecuteDTO codeToExecute) {
        strategy.setOnStartScript(codeToExecute.getCodeToRun());
    }

    @RequestMapping(value = "on-end", method = RequestMethod.POST)
    public void onEnd(@RequestBody CodeToExecuteDTO codeToExecute) {
        strategy.setOnEndScript(codeToExecute.getCodeToRun());
    }

    @RequestMapping(value = "on-next", method = RequestMethod.POST)
    public void onNextDay(@RequestBody CodeToExecuteDTO codeToExecute) {
        strategy.setOnNextDayScript(codeToExecute.getCodeToRun());
    }

    @RequestMapping(value = "stock-names", method = RequestMethod.POST)
    public void setStockNames(@RequestBody CodeToExecuteDTO stockNames) {
        strategy.setStockNames(Arrays.asList(stockNames.getCodeToRun().toUpperCase().trim().split(";")));
    }

    @RequestMapping(value = "execute", method = RequestMethod.GET)
    public InterpreterResult setStockNames() {
        return strategy.executeStrategy();
    }
}
