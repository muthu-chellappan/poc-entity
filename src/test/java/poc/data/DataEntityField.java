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
    private final String ptype;
    private final String name;
    private final Class ct;
    private final String tableFieldName;

    public DataEntityField(final MatchType type, final String name, final String tableFieldName) {
        this.type = type.getType().getSimpleName();
        this.ct = type.getType();
        this.ptype = type.getPtype();
        this.name = name;
        this.tableFieldName = tableFieldName;
    }

    public DataEntityField(final String type, final String name, final String tableFieldName) {
        this.type = type;
        this.ct = null;
        this.ptype = null;
        this.name = name;
        this.tableFieldName = tableFieldName;
    }

    public static MatchType getMatchingType(final String name, final String type, final List<Key> keys) {
        final Optional<Key> key = keys.stream().filter(k -> k.getCol().equals(name)).findFirst();
        DataType dataType = DataType.find(type);
        final MatchType match = new MatchType(name, dataType.type, dataType.ptype);
        if (key.isPresent()) {
            match.setKey(key.get());
        }
        return match;
    }

    public String getName() {
        return conventionalFieldName(name);
    }

    public String getAccessorName() {
        String tempName = conventionalFieldName(name);
        return "get"+tempName.substring(0,1).toUpperCase()+tempName.substring(1, tempName.length());
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
        STRING(String.class, "string", "VARCHAR", "TEXT", "NTEXT", "NVARCHAR", "JSON", "JSONB"),
        INTEGER(Integer.class, "int32", "INT"),
        DOUBLE(Double.class, "double", "NUMERIC"),
        DATE(Date.class, "int64", "TIMESTAMP", "DATE"),
        BOOLEAN(Boolean.class, "bool", "BOOLEAN"),
        CHAR(Character.class, "string", "CHAR");

        private final Class type;
        private final String ptype;
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

        private DataType(final Class type, final String ptype, final String...matches) {
            this.type = type;
            this.ptype = ptype;
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
        private final String ptype;

        private Key key;
    }

}
