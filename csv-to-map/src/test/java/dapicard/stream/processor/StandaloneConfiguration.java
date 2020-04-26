package dapicard.stream.processor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import dapicard.stream.processor.configuration.CsvConfiguration;

@SpringBootConfiguration
@ComponentScan(basePackages = { "dapicard.stream.processor.processor" })
@Import({ CsvConfiguration.class })
public class StandaloneConfiguration {

}