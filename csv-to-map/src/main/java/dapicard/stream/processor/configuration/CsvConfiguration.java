package dapicard.stream.processor.configuration;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.BinderHeaderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaderMapper;

import dapicard.stream.processor.configuration.model.Schema;

@Configuration
@EnableConfigurationProperties
public class CsvConfiguration {

    @Bean("schemaConfiguration")
    public Schema schemaConfiguration() {
        return new Schema();
    }

    @Bean
    public CsvSchema csvSchema(Schema schemaConfiguration) {
        CsvSchema.Builder schema = new CsvSchema.Builder();
        schema.setColumnSeparator(schemaConfiguration.columnSeparator);
        if(schemaConfiguration.escapeCharacter == null) {
            schema.disableEscapeChar();
        } else {
            schema.setEscapeChar(schemaConfiguration.escapeCharacter);
        }
        if(schemaConfiguration.enclosingCharacter == null) {
            schema.disableQuoteChar();
        } else {
            schema.setQuoteChar(schemaConfiguration.enclosingCharacter);
        }
        schema.setNullValue(schemaConfiguration.nullValue);
        if(schemaConfiguration.columns.isEmpty()) {
            throw new IllegalArgumentException("You must define at least one CSV column at key csv.schema.columns");
        }
        schemaConfiguration.columns.forEach(col -> {
            schema.addColumn(col.name, col.type);
        });

        return schema.build();
    }

    @Bean("csvMapper")
    public ObjectReader csvMapper(CsvSchema schema) {
        CsvMapper mapper = new CsvMapper();
        SimpleModule csvMapModule = new SimpleModule();
        csvMapModule.addValueInstantiator(CsvMap.class, new ValueInstantiator.Base(CsvMap.class) {
            private static final long serialVersionUID = -229391796118090072L;

            @Override
            public Object createUsingDefault(DeserializationContext ctxt) {
                return new CsvMap(schema);
            }

            @Override
            public boolean canCreateUsingDefault() {
                return true;
            }
        });
        return mapper.registerModule(csvMapModule).reader(schema).forType(CsvMap.class);
    }

    @Bean("kafkaBinderHeaderMapper")
    public KafkaHeaderMapper kafkaBinderHeaderMapper() {
        BinderHeaderMapper mapper = new BinderHeaderMapper();
        mapper.setEncodeStrings(true);
        return mapper;
    }
}