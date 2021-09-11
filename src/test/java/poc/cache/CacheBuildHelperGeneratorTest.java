package poc.cache;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CacheBuildHelperGeneratorTest {

    @Test
    public void testGenerateFromEnum() throws Exception {
        final Map<String, Object> context = new HashMap<>();
        final CacheHelper[] helpers = CacheHelper.values();
        final CacheBuildHelperGenerator generator = new CacheBuildHelperGenerator();
        final String template = "./src/main/resources/builder_cache.vm";
        for (final CacheHelper helper : helpers) {
            context.put("helper", helper);
            final Writer writer = new StringWriter();
            generator.generate(template, context, writer);
            final String out = writer.toString();
            assertNotNull(out);
            writer.flush();
            System.out.println(out);
            final String path = "src/test/java/com/weedmaps/cache/build/helper/";
            final File dir = new File(path);
            dir.mkdirs();
            final String file = path + helper.getType() + "BuildHelper.java";
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
