package poc.sql;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class SqlGenerator {

    public void generate(final String template, final Map<String, Object> macros, final Writer writer) throws IOException {
        final VelocityContext context = new VelocityContext();
        macros.forEach((k, v) -> context.put(k, v));
        Velocity.evaluate(context, writer, "sql", new FileReader(template));
//        final String out = writer.toString();
//        System.out.println(out);
    }

}
