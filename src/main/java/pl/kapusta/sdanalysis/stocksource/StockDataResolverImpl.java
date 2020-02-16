package pl.kapusta.sdanalysis.stocksource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockDataResolverImpl implements StockDataResolver {

    private static final String HELP_METHOD = "help() - get help\n";
    private static final String LOAD_CLOSE = "loadClose(String stockName) - load last 10 close values";

    @Autowired
    private StockDataProvider stockDataProvider;

    @Override
    public String help() {
        return HELP_METHOD + LOAD_CLOSE;
    }

    @Override
    public List<Double> loadClose(String stockName) {
        return stockDataProvider.getData(stockName, 10);
    }
}
