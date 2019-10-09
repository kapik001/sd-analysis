package pl.kapusta.sdanalysis.stocksource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockDataResolverImpl implements StockDataResolver {

    @Autowired
    private StockDataProvider stockDataProvider;

    @Override
    public List<Double> loadSome(String stockName) {
        return stockDataProvider.getData(stockName);
    }
}
