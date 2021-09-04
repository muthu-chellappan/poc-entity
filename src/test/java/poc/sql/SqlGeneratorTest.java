package poc.sql;

import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class SqlGeneratorTest {

    @Ignore @Test
    public void testGenerate() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final Table table1 = new Table("test1");
        prepare(table1);
        final Table table2 = new Table("test2");
        prepare(table2);
        context.put("tables", Arrays.asList(table1, table2));
        final String template = "./src/main/resources/postgresql_create.vm";
        final Writer writer = new StringWriter();
        final SqlGenerator gen = new SqlGenerator();
        gen.generate(template, context, writer);
        final String out = writer.toString();
        assertNotNull(out);
        System.out.println(out);
    }

    @Test
    public void testGenerateFromEnum() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final WmAdTable[] tables = WmAdTable.values();
        final List<Table> lt = new ArrayList<>();
        context.put("tables", lt);
        for (final WmAdTable table : tables) {
            lt.add(table.get());
        }
        final String template = "./src/main/resources/postgresql_create.vm";
        final Writer writer = new StringWriter();
        final SqlGenerator gen = new SqlGenerator();
        gen.generate(template, context, writer);
        final String out = writer.toString();
        assertNotNull(out);
        System.out.println(out);
    }

    @Test
    public void testGenerateInsertFromEnum() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final WmAdTableInsert[] inserts = WmAdTableInsert.values();
        final List<Table> lt = new ArrayList<>();
        context.put("tables", lt);
        for (final WmAdTableInsert insert : inserts) {
            lt.add(insert.get());
        }
        final String template = "./src/main/resources/postgresql_insert.vm";
        final Writer writer = new StringWriter();
        final SqlGenerator gen = new SqlGenerator();
        gen.generate(template, context, writer);
        final String out = writer.toString();
        assertNotNull(out);
        System.out.println(out);
    }

    private void prepare(final Table table) {
        table.add(new Column("col1"));
        table.add(new Column("col2"));
        table.add(new Constraint("con1", "UNIQUE", "col1"));
        table.add(new Key("id", "test", "id"));
    }

}
