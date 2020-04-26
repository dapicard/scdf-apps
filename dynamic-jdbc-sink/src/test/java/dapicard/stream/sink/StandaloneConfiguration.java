package dapicard.stream.sink;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = { "dapicard.stream.sink.sink" })
public class StandaloneConfiguration {

}