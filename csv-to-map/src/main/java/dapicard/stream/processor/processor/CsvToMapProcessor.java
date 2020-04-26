package dapicard.stream.processor.processor;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public class CsvToMapProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvToMapProcessor.class);

    @Autowired
    @Qualifier("csvMapper")
    private ObjectReader csvMapper;

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Map<String, Object> transform(String csvLine, @Headers Map<String, Object> headers) {
        try {
            LOGGER.info("headers {}", headers.toString());
            Map<String, Object> map = csvMapper.readValue(csvLine);
            return map;
        } catch (JsonProcessingException e) {
            LOGGER.error("An error occurs while reading CSV line {} : {}", csvLine, e.getMessage());
            LOGGER.debug(e.getMessage(), e);
            return null;
        }
    }
}