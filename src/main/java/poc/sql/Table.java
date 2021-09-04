package poc.sql;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private final String name;
    private final List<Column> columns;
    private final List<Constraint> constraints;
    private final List<Key> keys;

    public Table(final String name) {
        this.name = name;
        columns = new ArrayList<>();
        constraints = new ArrayList<>();
        keys = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void add(final Column column) {
        if (column == null) {
            return;
        }
        columns.add(column);
    }

    public void add(final Constraint con) {
        if (con == null) {
            return;
        }
        constraints.add(con);
    }

    public void add(final Key key) {
        if (key == null) {
            return;
        }
        keys.add(key);
    }

    public void addColumnValue(final Column column, final String value) {
        column.addValue(value);
    }

}
