package pl.kapusta.sdanalysis.stocksource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockDataResolverImpl implements StockDataResolver {

    private static final String HELP_METHOD = "help() - get help\n";
    private static final String LOAD_DATA = "loadData(String stockName) - load last 10 data records\n";
    private static final String LOAD = "load(String stockName, Integer numberOfDays) - load last numberOfDays data records\n";
    private static final String LOAD_MANY_DATA = "manyLoadData(String[] stockNames) - load last 10 data records for many stock names\n";
    private static final String LOAD_MANY = "manyLoad(String[] stockNames, Integer numberOfDays) - load last numberOfDays data records for many stock names\n";
    private static final String LOAD_STOCK_NAMES = "getStockNames() - load first 100 stock names";

    @Autowired
    private StockDataProvider stockDataProvider;

    @Override
    public String help() {
        return HELP_METHOD +
                LOAD_DATA +
                LOAD +
                LOAD_MANY_DATA +
                LOAD_MANY +
                LOAD_STOCK_NAMES;
    }

    @Override
    public List<StockData> loadData(String stockName) {
        return stockDataProvider.getData(stockName, 10d);
    }

    @Override
    public List<StockData> load(String stockName, Double numberOfDays) {
        return stockDataProvider.getData(stockName, numberOfDays);
    }

    @Override
    public Map<String, List<StockData>> manyLoadData(List<String> stockNames) {
        return stockDataProvider.getMany(stockNames, 10);
    }

    @Override
    public Map<String, List<StockData>> manyLoad(List<String> stockNames, Integer numberOfDays) {
        return stockDataProvider.getMany(stockNames, numberOfDays);
    }

    @Override
    public List<CompaniesData> getStockNames() {
        return stockDataProvider.getStockNames();
    }
}
