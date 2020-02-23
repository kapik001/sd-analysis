package pl.kapusta.sdanalysis.stocksource;

import com.intrinio.models.SecuritySummary;
import com.intrinio.models.StockPriceSummary;
import pl.kapusta.sdanalysis.util.Muter;

import java.util.Date;
import java.util.function.Function;

public class IntrinioCompaniesDataMapAction implements Function<SecuritySummary, CompaniesData> {
    @Override
    public CompaniesData apply(SecuritySummary source) {
        CompaniesData companiesData = new CompaniesData();
        companiesData.setCompanyId(Muter.mute(source::getCompanyId));
        companiesData.setName(Muter.mute(source::getName));
        companiesData.setCode(Muter.mute(source::getCode));
        companiesData.setCurrency(Muter.mute(source::getCurrency));
        return companiesData;
    }
}
