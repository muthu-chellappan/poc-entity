package poc.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Query {
    private final String name;
    private final String filter;
    private final List<Field> fields;

    public Query(final String name, final String filter, final String... fields) {
        this.name = name;
        this.filter = filter;
        this.fields = new ArrayList<>();
        for (final String field : fields) {
            this.fields.add(new Field(field));
        }
    }

    @Data
    public class Field {
        private final String field;

        public String getType() {
            final int index = field.indexOf(":");
            if (index != -1) {
                return field.substring(0, index);
            }
            return "List<Integer>";
        }

        public String getName() {
            final int index = field.indexOf(":");
            if (index != -1) {
                return field.substring(index + 1);
            }
            return field;
        }
    }

}
