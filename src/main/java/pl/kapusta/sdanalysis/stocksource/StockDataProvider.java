package pl.kapusta.sdanalysis.stocksource;

import java.math.BigDecimal;
import java.util.List;

public interface StockDataProvider {
    List<Double> getData(String stockName, Integer numberOfDays);
}
