package poc.data;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import poc.sql.MacaTable;
import poc.sql.Table;

public class ProtoGeneratorTest {

    @Test
    public void testGenerateFromEnum() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final MacaTable[] tables = MacaTable.values();
        final List<Table> lt = new ArrayList<>();
        for (final MacaTable table : tables) {
            lt.add(table.get());
        }
        System.out.println("Total tables: " + tables.length);
        final String template = "./src/main/resources/proto.vm";
        final DataEntityGenerator gen = new DataEntityGenerator();
        final TableToTypeConverter converter = new TableToTypeConverter();
        for (final Table table : lt) {
            final Writer writer = new StringWriter();
            final DataEntityType entity = converter.convert(table);
            context.put("type", entity);
            gen.generate(template, context, writer);
            final String out = writer.toString();
            assertNotNull(out);
            writer.flush();
            System.out.println(out);
            final String path = "src/test/resources/proto/";
            final File dir = new File(path);
            dir.mkdirs();
            final String file = path + entity.getTableName() + "_entity.proto";
            write(file, out);
        }
    }

    private void write(final String path, final String content) throws IOException {
        try (final Writer writer = new FileWriter(path);
                final BufferedWriter buff = new BufferedWriter(writer);) {
            buff.append(content);
        }
    }

}
