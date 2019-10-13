package pl.kapusta.sdanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.session.NewSessionInfoProvider;
import pl.kapusta.sdanalysis.session.SessionRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("session-repository")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private NewSessionInfoProvider newSessionInfoProvider;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public List<InterpreterResult> init(){
        List<InterpreterResult> results = new LinkedList<>();
        results.addAll(newSessionInfoProvider.getInitInformations());
        results.addAll(sessionRepository.getStoredInterpreterResults());
        return results;
    }
}
