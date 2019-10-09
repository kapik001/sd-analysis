package pl.kapusta.sdanalysis.stocksource;

import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.ApiResponseSecurityStockPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockDataProviderImpl implements StockDataProvider {
    private static final Logger LOG = LoggerFactory.getLogger(StockDataProviderImpl.class);

    @Override
    public List<Double> getData(String stockName) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("OjY0NTUzZmJjZWRkYzI4ZmVkNmIzNGIxMmI2Njg5MWFl");

        SecurityApi securityApi = new SecurityApi();

        LocalDate startDate = null; // LocalDate | Return prices on or after the date
        LocalDate endDate = null; // LocalDate | Return prices on or before the date
        String frequency = "daily"; // String | Return stock prices in the given frequency
        Integer pageSize = 10; // Integer | The number of results to return
        String nextPage = null; // String | Gets the next page of data from a previous API call

        ApiResponseSecurityStockPrices result = null;
        try {
            result = securityApi.getSecurityStockPrices(stockName, startDate, endDate, frequency, pageSize, nextPage);
        } catch (ApiException e) {
            LOG.error("Exception when calling SecurityApi#getSecurityStockPrices", e);
            return new LinkedList<>();
        }
        return result.getStockPrices().stream().map(s -> s.getClose().doubleValue()).collect(Collectors.toList());
    }
}