package pl.kapusta.sdanalysis.stocksource;
import java.math.BigDecimal;
import java.util.Date;

import com.intrinio.models.StockPriceSummary;
import pl.kapusta.sdanalysis.util.Muter;

import java.util.function.Function;

public class IntrinioStockDataMapAction implements Function<StockPriceSummary, StockData> {
    @Override
    public StockData apply(StockPriceSummary source) {
        StockData stockData = new StockData();
        stockData.setDate(Muter.mute(()-> new Date(source.getClose().longValue())));
        stockData.setIntraperiod(Muter.mute(source::isisIntraperiod));
        stockData.setFrequency(Muter.mute(() -> source.getFrequency().name()));
        stockData.setOpen(Muter.mute(() -> source.getOpen().doubleValue()));
        stockData.setHigh(Muter.mute(() -> source.getHigh().doubleValue()));
        stockData.setLow(Muter.mute(() -> source.getLow().doubleValue()));
        stockData.setClose(Muter.mute(() -> source.getClose().doubleValue()));
        stockData.setVolume(Muter.mute(() -> source.getVolume().doubleValue()));
        stockData.setAdjOpen(Muter.mute(() -> source.getAdjOpen().doubleValue()));
        stockData.setAdjHigh(Muter.mute(() -> source.getAdjHigh().doubleValue()));
        stockData.setAdjLow(Muter.mute(() -> source.getAdjLow().doubleValue()));
        stockData.setAdjClose(Muter.mute(() -> source.getAdjClose().doubleValue()));
        stockData.setAdjVolume(Muter.mute(() -> source.getAdjVolume().doubleValue()));
        return stockData;
    }
}
