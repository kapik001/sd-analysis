package pl.kapusta.sdanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;

@RestController
@RequestMapping("run-command")
public class InterpreterController {

    @Autowired
    private RRunner runner;

    @RequestMapping("test/{command}")
    public InterpreterResult runCommand(@PathVariable("command") String command){
        return runner.run(command);
    }
}
