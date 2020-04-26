package dapicard.stream.processor.configuration;

import java.util.HashMap;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

class CsvMap extends HashMap<String, Object> {
    private static final long serialVersionUID = -3160958035415562095L;
    private final CsvSchema schema;

    public CsvMap(CsvSchema schema) {
        this.schema = schema;
    }

    @Override
    public Object put(String key, Object value) {
        value = convertIfNeeded(key, value);
        return super.put(key, value);
    }

    private Object convertIfNeeded(String key, Object value) {
        switch (schema.column(key).getType()) {
            case NUMBER:
            case NUMBER_OR_STRING:
                try {
                    return Long.parseLong(value.toString());
                } catch (NumberFormatException nfe) {
                    try {
                        return Double.parseDouble(value.toString());
                    } catch (NumberFormatException nfe1) {
                    }
                }
                break;
            case BOOLEAN:
                return Boolean.parseBoolean(value.toString());
            default:
                return value;
        }

        return value;
    }
}