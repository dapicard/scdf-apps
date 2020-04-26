package dapicard.stream.processor.configuration.model;

import java.util.List;
import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "csv.schema")
public class Schema {

    /**
     * CSV Column separator
     */
    public Character columnSeparator;
    public Character enclosingCharacter;
    public Character escapeCharacter;
    public String nullValue;
    public List<ColumnDefinition> columns;

    public Schema() {
    }

    public Schema(Character columnSeparator, String nullValue, Character enclosingCharacter, Character escapeCharacter,
            List<ColumnDefinition> columns) {
        this.columnSeparator = columnSeparator;
        this.nullValue = nullValue;
        this.enclosingCharacter = enclosingCharacter;
        this.escapeCharacter = escapeCharacter;
        this.columns = columns;
    }

    public Character getColumnSeparator() {
        return this.columnSeparator;
    }

    public void setColumnSeparator(Character columnSeparator) {
        this.columnSeparator = columnSeparator;
    }

    public String getNullValue() {
        return this.nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public Character getEnclosingCharacter() {
        return this.enclosingCharacter;
    }

    public void setEnclosingCharacter(Character enclosingCharacter) {
        this.enclosingCharacter = enclosingCharacter;
    }

    public Character getEscapeCharacter() {
        return this.escapeCharacter;
    }

    public void setEscapeCharacter(Character escapeCharacter) {
        this.escapeCharacter = escapeCharacter;
    }

    public List<ColumnDefinition> getColumns() {
        return this.columns;
    }

    public void setColumns(List<ColumnDefinition> columns) {
        this.columns = columns;
    }

    public Schema columnSeparator(Character columnSeparator) {
        this.columnSeparator = columnSeparator;
        return this;
    }

    public Schema nullValue(String nullValue) {
        this.nullValue = nullValue;
        return this;
    }

    public Schema enclosingCharacter(Character enclosingCharacter) {
        this.enclosingCharacter = enclosingCharacter;
        return this;
    }

    public Schema escapeCharacter(Character escapeCharacter) {
        this.escapeCharacter = escapeCharacter;
        return this;
    }

    public Schema columns(List<ColumnDefinition> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Schema)) {
            return false;
        }
        Schema schema = (Schema) o;
        return Objects.equals(columnSeparator, schema.columnSeparator) && Objects.equals(nullValue, schema.nullValue)
                && Objects.equals(enclosingCharacter, schema.enclosingCharacter)
                && Objects.equals(escapeCharacter, schema.escapeCharacter) && Objects.equals(columns, schema.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnSeparator, nullValue, enclosingCharacter, escapeCharacter, columns);
    }

    @Override
    public String toString() {
        return "{" + " columnSeparator='" + getColumnSeparator() + "'" + ", nullValue='" + getNullValue() + "'"
                + ", enclosingCharacter='" + getEnclosingCharacter() + "'" + ", escapeCharacter='"
                + getEscapeCharacter() + "'" + ", columns='" + getColumns() + "'" + "}";
    }

}