package poc.sql;

import lombok.Data;

@Data
public class Query {
    private final String name;
    private final String filter;
    private final String[] fields;

    public Query(final String name, final String filter, final String... fields) {
        this.name = name;
        this.filter = filter;
        this.fields = fields;
    }

}
