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

import poc.sql.Table;
import poc.sql.WmAdTable;

public class DataEntityGeneratorTest {

    @Test
    public void testGenerateFromEnum() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final WmAdTable[] tables = WmAdTable.values();
        final List<Table> lt = new ArrayList<>();
        for (final WmAdTable table : tables) {
            lt.add(table.get());
        }
        System.out.println("Total tables: " + tables.length);
        final String template = "./src/main/resources/data_entity.vm";
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
            final String path = "src/test/java/com/weedmaps/dataaccess/entity/";
            final File dir = new File(path);
            dir.mkdirs();
            final String file = path + entity.getName() + ".java";
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
