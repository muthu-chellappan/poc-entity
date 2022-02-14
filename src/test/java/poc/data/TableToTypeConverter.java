package poc.data;

import poc.data.DataEntityField.MatchType;
import poc.sql.Table;

public class TableToTypeConverter {

    public DataEntityType convert(final Table table) {
        final DataEntityType type = new DataEntityType(table.getName(), table.getName(), table.getQueries());
//        type.addDefaultFields();
        if (table.getColumns() == null) {
            return type;
        }
        table.getColumns().forEach(col -> {
            final MatchType mt = DataEntityField.getMatchingType(col.getName(), col.getType(), table.getKeys());
            type.addField(new DataEntityField(mt, mt.getName(),col.getName()));
//            if (mt.getKey() != null) {
//                final String kt = mt.getKey().getTable();
//                final String fn = DataEntityField.conventionalFieldName(kt);
//                final String ft = DataEntityType.conventionalTypeName(kt);
//                type.addField(new DataEntityField(ft, fn,col.getName()));
//            }
        });
        type.getFields().parallelStream().forEach(field -> type.addImport(field.getCt()));
        return type;
    }

}
