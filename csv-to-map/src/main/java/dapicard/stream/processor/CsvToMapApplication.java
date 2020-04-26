package dapicard.stream.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

import dapicard.stream.common.configuration.model.DomainReference;

@SpringBootApplication
@EnableBinding(Processor.class)
@EnableConfigurationProperties(DomainReference.class)
public class CsvToMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvToMapApplication.class, args);
    }

}