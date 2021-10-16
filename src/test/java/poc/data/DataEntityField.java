package poc.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.Data;
import poc.exception.NoMatchingDataTypeException;
import poc.sql.Key;

@SuppressWarnings({ "rawtypes" })
@Data
public class DataEntityField {

    private final String type;
    private final String name;
    private final Class ct;
    private final String tableFieldName;

    public DataEntityField(final Class type, final String name, final String tableFieldName) {
        this.type = type.getSimpleName();
        this.ct = type;
        this.name = name;
        this.tableFieldName = tableFieldName;
    }

    public DataEntityField(final String type, final String name, final String tableFieldName) {
        this.type = type;
        this.ct = null;
        this.name = name;
        this.tableFieldName = tableFieldName;
    }

    public static MatchType getMatchingType(final String name, final String type, final List<Key> keys) {
        final Optional<Key> key = keys.stream().filter(k -> k.getCol().equals(name)).findFirst();
        final MatchType match = new MatchType(name, DataType.find(type).type);
        if (key.isPresent()) {
            match.setKey(key.get());
        }
        return match;
    }

    public String getName() {
        return conventionalFieldName(name);
    }

    public static String conventionalFieldName(final String name) {
        final StringBuilder builder = new StringBuilder(name);
        int index = -1;
        while((index = builder.indexOf("_")) != -1) {
            builder.replace(index, index + 2, String.valueOf(Character.toUpperCase(builder.charAt(index + 1))));
        }
        return builder.toString();
    }

    public String getAbsoluteName() {
        return name;
    }

    private enum DataType {
        STRING(String.class, "VARCHAR", "TEXT", "NTEXT", "NVARCHAR"),
        INTEGER(Integer.class, "INT"),
        DOUBLE(Double.class, "NUMERIC"),
        DATE(Date.class, "TIMESTAMP", "DATE"),
        BOOLEAN(Boolean.class, "BOOLEAN"),
        CHAR(Character.class, "CHAR");

        private final Class type;
        private final String[] matches;
        private static final Map<String, DataType> TYPES = new HashMap<>();

        static {
            final DataType[] values = DataType.values();
            for (final DataType value : values) {
                for (final String match : value.matches) {
                    TYPES.put(match, value);
                }
            }
        }

        private DataType(final Class type, final String...matches) {
            this.type = type;
            this.matches = matches;
        }

        private static DataType find(final String type) {
            final String sanitized = sanitize(type.toUpperCase());
            if (TYPES.containsKey(sanitized)) {
                return TYPES.get(sanitized);
            }
            throw new NoMatchingDataTypeException(sanitized);
        }

        private static String sanitize(final String value) {
            final int index = value.indexOf('(');
            if (index > 0) {
                return value.substring(0, index);
            }
            return value;
        }
    }

    @Data
    static class MatchType {
        private final String name;
        private final Class type;

        private Key key;
    }

}
