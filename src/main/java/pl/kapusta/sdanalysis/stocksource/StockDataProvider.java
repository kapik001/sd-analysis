package pl.kapusta.sdanalysis.stocksource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StockDataProvider {
    List<StockData> getData(String stockName, Double numberOfDays);
    Map<String, List<StockData>> getMany(List<String> stockNames, Integer numberOfDays);
    List<CompaniesData> getStockNames();
}
