package pl.kapusta.sdanalysis.strategy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.kapusta.sdanalysis.rinterpreter.RRunner;
import pl.kapusta.sdanalysis.stocksource.StockData;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Strategy {
    private List<StockData> data;
    private String onStartScript;
    private String onNextDayScript;
    private String onEndScript;


}
