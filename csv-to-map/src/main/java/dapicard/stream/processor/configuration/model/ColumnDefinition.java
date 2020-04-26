package dapicard.stream.processor.configuration.model;

import java.util.Objects;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ColumnDefinition {

    public String name;
    public CsvSchema.ColumnType type;

    public ColumnDefinition() {
    }

    public ColumnDefinition(String name, CsvSchema.ColumnType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CsvSchema.ColumnType getType() {
        return this.type;
    }

    public void setType(CsvSchema.ColumnType type) {
        this.type = type;
    }

    public ColumnDefinition name(String name) {
        this.name = name;
        return this;
    }

    public ColumnDefinition type(CsvSchema.ColumnType type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ColumnDefinition)) {
            return false;
        }
        ColumnDefinition column = (ColumnDefinition) o;
        return Objects.equals(name, column.name) && Objects.equals(type, column.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", type='" + getType() + "'" + "}";
    }

}