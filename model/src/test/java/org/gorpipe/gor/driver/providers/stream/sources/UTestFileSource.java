package org.gorpipe.gor.driver.providers.stream.sources;

import org.gorpipe.gor.driver.DataSource;
import org.gorpipe.gor.driver.meta.SourceType;
import org.gorpipe.gor.driver.providers.stream.sources.file.FileSource;
import org.gorpipe.gor.driver.providers.stream.sources.file.FileSourceType;
import org.gorpipe.gor.driver.providers.stream.sources.wrappers.RetryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by villi on 25/08/15.
 */
public class UTestFileSource extends CommonStreamTests {

    private static final Logger log = LoggerFactory.getLogger(UTestFileSource.class);

    @Test
    public void testSymlink() throws IOException {
        Path tempDir = Files.createTempDirectory("test");
        File symlink = new File(tempDir.toFile(), "link.txt");
        Files.createSymbolicLink(symlink.toPath(), lines1000File.toPath());

        String linkPath = symlink.getPath();
        // Ensure that we are working with unexpanded path to symlink
        Assert.assertTrue(linkPath.endsWith("/link.txt"));

        StreamSource fs = createSource(linkPath);
        StreamSourceMetadata sourceMetadata = fs.getSourceMetadata();
        log.info("Metadata attributes: {}", sourceMetadata.attributes());
        Assert.assertEquals(lines1000File.lastModified(), sourceMetadata.getLastModified().longValue());
        Assert.assertEquals(4000, sourceMetadata.getLength().longValue());
        Assert.assertEquals("file://" + lines1000File.getCanonicalPath(), sourceMetadata.attributes().get("CanonicalName"));
    }


    @Override
    protected String getDataName(File file) throws IOException {
        return file.getCanonicalPath();
    }

    @Override
    protected StreamSource createSource(String name) {
        return new FileSource(name, null);
    }

    @Override
    protected String expectCanonical(StreamSource source, String name) {
        return "file://" + name;
    }

    @Override
    protected void verifyDriverDataSource(String name, DataSource fs) {
        Assert.assertEquals(RetryWrapper.class, fs.getClass());
        fs = ((RetryWrapper) fs).getWrapped();
        Assert.assertEquals(FileSource.class, fs.getClass());
    }

    @Override
    protected SourceType expectedSourcetype(StreamSource fs) {
        return FileSourceType.FILE;
    }
}
