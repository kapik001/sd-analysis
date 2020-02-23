package pl.kapusta.sdanalysis.stocksource;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public interface StockDataResolver {
    String help();

    List<StockData> loadData(String stockName);

    List<StockData> load(String stockName, Integer numberOfDays);

    Map<String, List<StockData>> manyLoadData(List<String> stockNames);

    Map<String, List<StockData>> manyLoad(List<String> stockNames, Integer numberOfDays);

    List<CompaniesData> getStockNames();
}
