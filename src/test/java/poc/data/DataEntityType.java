package poc.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@SuppressWarnings({ "rawtypes" })
@Data
public class DataEntityType {

    private final String name;
    private final List<DataEntityField> fields = new ArrayList<>();
    private final Set<Class> imports = new HashSet<>();

    public String getName() {
        return conventionalTypeName(name);
    }

    public static String conventionalTypeName(final String name) {
        final StringBuilder builder = new StringBuilder(name);
        int index = -1;
        builder.replace(0, 1, String.valueOf(Character.toUpperCase(builder.charAt(0))));
        while((index = builder.indexOf("_")) != -1) {
            builder.replace(index, index + 2, String.valueOf(Character.toUpperCase(builder.charAt(index + 1))));
        }
        return builder.toString();
    }

    public void addField(final DataEntityField field) {
        fields.add(field);
    }

    public void addImport(final Class type) {
        imports.add(type);
    }

    public List<String> getSanitizedImports() {
        final List<String> sis = new ArrayList<>();
        imports.stream().filter(i -> i != null && !i.getName().startsWith("java.lang."))
                .forEach(si -> sis.add(si.getName()));
        Collections.sort(sis);
        return sis;
    }

    public void addDefaultFields() {
        fields.add(new DataEntityField(
                DataEntityField.getMatchingType("id", "INT", Collections.emptyList()).getType(), "id"));
        fields.add(new DataEntityField(DataEntityField.getMatchingType("is_deleted", "BOOLEAN", Collections.emptyList())
                .getType(), "is_deleted"));
        fields.add(
                new DataEntityField(DataEntityField.getMatchingType("created_at", "TIMESTAMP", Collections.emptyList())
                        .getType(), "created_at"));
        fields.add(
                new DataEntityField(DataEntityField.getMatchingType("updated_at", "TIMESTAMP", Collections.emptyList())
                        .getType(), "updated_at"));
        fields.add(new DataEntityField(DataEntityField.getMatchingType("created_by", "VARCHAR", Collections.emptyList())
                .getType(), "created_by"));
        fields.add(new DataEntityField(DataEntityField.getMatchingType("updated_by", "VARCHAR", Collections.emptyList())
                .getType(), "updated_by"));
    }

}
