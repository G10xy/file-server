package itx.fileserver.services.compression;

import java.io.IOException;
import java.nio.file.Path;

public interface ProcessStrategy {
    void compress(Path inputPath, Path outputPath) throws IOException;

    void decompress(Path inputPath, Path outputPath) throws IOException;
}
