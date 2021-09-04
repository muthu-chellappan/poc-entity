package poc.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Column {

    private final String name;
    private final String type;
    private final boolean nullable;
    private final String value;
    private final List<String> values = new ArrayList<>();

    public Column(final String name) {
        this.name = name;
        type = "VARCHAR";
        nullable = true;
        value = null;
    }

    public Column(final String name, final String type) {
        this.name = name;
        this.type = type;
        nullable = true;
        value = null;
    }

    public Column(final String name, final String type, final boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        value = null;
    }

    public String getNullable() {
        return nullable ? "" : "NOT NULL";
    }

    public String getValue() {
        return value == null ? "" : ("DEFAULT " + value);
    }

    public void addValue(final String value) {
        values.add(value);
    }

    public static Column get(final String col) {
        final String[] tokens = col.split("#");
        final String type = tokens.length > 1 ? tokens[1] : null;
        final boolean nullable = tokens.length > 2 ? Boolean.valueOf(tokens[2].trim()) : Boolean.TRUE;
        final String value = tokens.length > 3 ? tokens[3] : null;
        final Column column = new Column(tokens[0], type, nullable, value);
        return column;
    }

}
