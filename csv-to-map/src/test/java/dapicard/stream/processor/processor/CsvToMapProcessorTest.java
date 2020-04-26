package dapicard.stream.processor.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import dapicard.stream.processor.StandaloneConfiguration;

@SpringBootTest(classes = StandaloneConfiguration.class)
public class CsvToMapProcessorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvToMapProcessorTest.class);

    @Autowired
    private CsvToMapProcessor processor;

    @Value("classpath:adresses.csv")
    private Resource adresses;

    @Test
    public void testMapping() throws IOException {
        List<String> lines = Files.readAllLines(adresses.getFile().toPath());
        lines.forEach(line -> {
            Map<String, Object> map = processor.transform(line, new HashMap<>());
            Assertions.assertEquals(20, map.size());
            LOGGER.info("Adresse: {}", map);
        });
    }
}