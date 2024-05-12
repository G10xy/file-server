package itx.fileserver.services.compression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class FileCompressor {

    private static final Logger LOG = LoggerFactory.getLogger(FileCompressor.class);

    private CompressionStrategy strategy;

    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compressFile(Path sourceFilePath, Path destinationFilePath) {
        try  {
            strategy.compress(sourceFilePath, destinationFilePath);
            LOG.info("File compressed successfully using " + strategy.getClass().getSimpleName());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
