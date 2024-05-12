package itx.fileserver.services.compression;

import java.io.IOException;
import java.nio.file.Path;

public interface CompressionStrategy {
    void compress(Path inputPath, Path outputPath) throws IOException;
}
