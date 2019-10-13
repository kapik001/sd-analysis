package pl.kapusta.sdanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kapusta.sdanalysis.dto.CodeToExecuteDTO;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;
import pl.kapusta.sdanalysis.session.SessionRepository;

@RestController
@RequestMapping("interpreter-runner")
public class InterpreterController {

    @Autowired
    private RRunner runner;

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(value = "run-code", method = RequestMethod.POST)
    public InterpreterResult runCommand(@RequestBody CodeToExecuteDTO codeToExecute){
        InterpreterResult result = runner.run(codeToExecute.getCodeToRun());
        sessionRepository.putInterpreterResult(result);
        return result;
    }

}
