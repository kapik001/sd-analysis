package pl.kapusta.sdanalysis.session;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionRepositoryImpl implements SessionRepository {

    private List<InterpreterResult> interpreterResults;
    private static final Integer MAX_CAPACITY = 20;

    @PostConstruct
    public void init() {
        interpreterResults = new LinkedList<>();
    }

    @Override
    public void putInterpreterResult(InterpreterResult interpreterResult) {
        if (MAX_CAPACITY.equals(interpreterResults.size())) {
            interpreterResults.remove(0);
        }
        interpreterResults.add(interpreterResult);
    }

    @Override
    public List<InterpreterResult> getStoredInterpreterResults() {
        return interpreterResults;
    }
}
