package pl.kapusta.sdanalysis.stocksource;

import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.ApiResponseSecurities;
import com.intrinio.models.ApiResponseSecurityStockPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IntrinioDataProviderImpl implements StockDataProvider {
    private static final Logger LOG = LoggerFactory.getLogger(IntrinioDataProviderImpl.class);

    @Override
    public List<StockData> getData(String stockName, Double numberOfDays) {
       return this.getIndividual(stockName, numberOfDays.intValue());
    }

    @Override
    public Map<String, List<StockData>> getMany(List<String> stockNames, Integer numberOfDays) {
        Map<String, List<StockData>> map = new LinkedHashMap<>();
        for (String stockName: stockNames){
            map.put(stockName, this.getIndividual(stockName, numberOfDays));
        }
        return map;
    }

    private List<StockData> getIndividual(String stockName, Integer numberOfDays){
        SecurityApi securityApi = prepareSecurityApi();
        LocalDate startDate = null; // LocalDate | Return prices on or after the date
        LocalDate endDate = null; // LocalDate | Return prices on or before the date
        String frequency = "daily"; // String | Return stock prices in the given frequency
        Integer pageSize = numberOfDays; // Integer | The number of results to return
        String nextPage = null; // String | Gets the next page of data from a previous API call

        ApiResponseSecurityStockPrices result = null;
        try {
            result = securityApi.getSecurityStockPrices(stockName, startDate, endDate, frequency, pageSize, nextPage);
        } catch (ApiException e) {
            LOG.error("Exception when calling SecurityApi#getSecurityStockPrices", e);
            return new LinkedList<>();
        }
        return result.getStockPrices().stream().map(new IntrinioStockDataMapAction()).collect(Collectors.toList());
    }

    public List<CompaniesData> getStockNames(){
        SecurityApi securityApi = prepareSecurityApi();

        ApiResponseSecurities result = null;
        try {
            result = securityApi.getAllSecurities(true, true, null, null, null, null,null,
                    null,null,null,null, null,null,null,null,
                    null, 100,null);
        } catch (ApiException e) {
            LOG.error("Exception when calling SecurityApi#getAllSecurities", e);
            return new LinkedList<>();
        }
        return result.getSecurities().stream().map(new IntrinioCompaniesDataMapAction()).collect(Collectors.toList());
    }

    private SecurityApi prepareSecurityApi() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("OjY0NTUzZmJjZWRkYzI4ZmVkNmIzNGIxMmI2Njg5MWFl");

        return new SecurityApi();
    }
}
