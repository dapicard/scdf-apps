package dapicard.stream.common.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

@Order(1)
public class DomainPropertySourceLocator implements PropertySourceLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DomainPropertySourceLocator.class);

    private ConfigServicePropertySourceLocator locator;

    public DomainPropertySourceLocator(ConfigClientProperties defaultProperties) {
        LOGGER.debug("Init ModelPropertySourceLocator with {}", defaultProperties);
        locator = new ConfigServicePropertySourceLocator(defaultProperties);
    }

    @Override
    public PropertySource<?> locate(Environment environment) {
        LOGGER.info("Locate DomainPropertySourceLocator for {}:{}:{}",
            environment.getProperty("domain.domain-name"),
            environment.getProperty("domain.domain-profile"),
            environment.getProperty("domain.domain-version"));

        StandardEnvironment env = new StandardEnvironment();
        ((StandardEnvironment)environment).getPropertySources().forEach(env.getPropertySources()::addLast);

        String profile = environment.getProperty("domain.domain-profile");
        if(StringUtils.isEmpty(profile)) {
            profile = "default";
        }

        Map<String, Object> patchedProps = new HashMap<>();
        patchedProps.put(ConfigClientProperties.PREFIX + ".name", environment.getProperty("domain.domain-name"));
        patchedProps.put(ConfigClientProperties.PREFIX + ".profile", profile);
        patchedProps.put(ConfigClientProperties.PREFIX + ".label", environment.getProperty("domain.domain-version"));

        env.getPropertySources().addFirst(new MapPropertySource("patchDomainName", patchedProps));

        return locator.locate(env);
    }

}