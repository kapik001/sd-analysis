package pl.kapusta.sdanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kapusta.sdanalysis.dto.CodeToExecuteDTO;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;

@RestController
@RequestMapping("interpreter-runner")
public class InterpreterController {

    @Autowired
    private RRunner runner;

    @RequestMapping(value = "run-code", method = RequestMethod.POST)
    public InterpreterResult runCommand(@RequestBody CodeToExecuteDTO codeToExecute){
        return runner.run(codeToExecute.getCodeToRun());
    }

}
