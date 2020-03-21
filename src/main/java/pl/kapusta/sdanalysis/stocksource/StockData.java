package pl.kapusta.sdanalysis.stocksource;

import java.io.Serializable;
import java.util.Date;

public class StockData implements Serializable {

    private Date date;
    private Boolean intraperiod;
    private String frequency;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private Double adjOpen;
    private Double adjHigh;
    private Double adjLow;
    private Double adjClose;
    private Double adjVolume;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIntraperiod() {
        return intraperiod;
    }

    public void setIntraperiod(Boolean intraperiod) {
        this.intraperiod = intraperiod;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getAdjOpen() {
        return adjOpen;
    }

    public void setAdjOpen(Double adjOpen) {
        this.adjOpen = adjOpen;
    }

    public Double getAdjHigh() {
        return adjHigh;
    }

    public void setAdjHigh(Double adjHigh) {
        this.adjHigh = adjHigh;
    }

    public Double getAdjLow() {
        return adjLow;
    }

    public void setAdjLow(Double adjLow) {
        this.adjLow = adjLow;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public Double getAdjVolume() {
        return adjVolume;
    }

    public void setAdjVolume(Double adjVolume) {
        this.adjVolume = adjVolume;
    }
}
