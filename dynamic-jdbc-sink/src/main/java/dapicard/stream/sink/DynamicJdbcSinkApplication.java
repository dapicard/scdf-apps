package dapicard.stream.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import dapicard.stream.common.configuration.model.DomainReference;

@SpringBootApplication
@Import({org.springframework.cloud.stream.app.jdbc.sink.JdbcSinkConfiguration.class})
@EnableConfigurationProperties(DomainReference.class)
public class DynamicJdbcSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicJdbcSinkApplication.class, args);
	}
}
