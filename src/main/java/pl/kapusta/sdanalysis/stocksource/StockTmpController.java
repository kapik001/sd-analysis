package pl.kapusta.sdanalysis.stocksource;

import com.intrinio.api.*;
import com.intrinio.models.*;
import com.intrinio.invoker.*;
import com.intrinio.invoker.auth.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.*;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTmpController {

    private static final Logger LOG = LoggerFactory.getLogger(StockTmpController.class);

    @RequestMapping("test")
    public ApiResponseSecurityStockPrices test(){
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("X");

        SecurityApi securityApi = new SecurityApi();

        String identifier = "AAPL"; // String | A Security identifier (Ticker, FIGI, ISIN, CUSIP, Intrinio ID)
        LocalDate startDate = null; // LocalDate | Return prices on or after the date
        LocalDate endDate = null; // LocalDate | Return prices on or before the date
        String frequency = "daily"; // String | Return stock prices in the given frequency
        Integer pageSize = 100; // Integer | The number of results to return
        String nextPage = null; // String | Gets the next page of data from a previous API call

        ApiResponseSecurityStockPrices result = null;
        try {
            result = securityApi.getSecurityStockPrices(identifier, startDate, endDate, frequency, pageSize, nextPage);
        } catch (ApiException e) {
            LOG.error("Exception when calling SecurityApi#getSecurityStockPrices", e);
        }
        return result;
    }
}
