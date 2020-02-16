package pl.kapusta.sdanalysis.session;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.kapusta.sdanalysis.rinterpreter.InterpreterResult;
import pl.kapusta.sdanalysis.rinterpreter.OperationResult;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NewSessionInfoProviderImpl implements NewSessionInfoProvider {

    @Override
    public List<InterpreterResult> getInitInformations() {
        List<InterpreterResult> initInformations = new LinkedList<>();
        InterpreterResult interpreterResult = new InterpreterResult();
        interpreterResult.setOperationResult(OperationResult.INFO);
        interpreterResult.setResult("Welcome in this application");
        initInformations.add(interpreterResult);
        InterpreterResult interpreterResult2 = new InterpreterResult();
        interpreterResult2.setOperationResult(OperationResult.INFO);
        interpreterResult2.setResult("Run 'stockData$help()' to get help");
        initInformations.add(interpreterResult2);
        return initInformations;
    }
}
