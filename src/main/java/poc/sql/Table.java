package poc.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Table {

    private final String name;
    private final List<Column> columns = new ArrayList<>();
    private final List<Constraint> constraints = new ArrayList<>();
    private final List<Key> keys = new ArrayList<>();
    private final List<Query> queries = new ArrayList<>();

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

    public void addQueries(final List<Query> queries) {
        this.queries.addAll(queries);
    }

}
