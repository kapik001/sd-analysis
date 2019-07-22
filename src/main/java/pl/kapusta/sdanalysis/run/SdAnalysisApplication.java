package pl.kapusta.sdanalysis.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"pl.kapusta.sdanalysis"})
public class SdAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdAnalysisApplication.class, args);
    }

}
