package pl.kapusta.sdanalysis.stocksource;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;

public interface StockDataResolver {
    String help();
    List<Double> loadClose(String stockName);

}
