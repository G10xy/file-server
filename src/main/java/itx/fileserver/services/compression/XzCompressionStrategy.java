package itx.fileserver.services.compression;

import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class XzCompressionStrategy implements CompressionStrategy {
    @Override
    public void compress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             BufferedInputStream bis = new BufferedInputStream(fis);
             XZCompressorOutputStream xzOut = new XZCompressorOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                xzOut.write(buffer, 0, bytesRead);
            }
        }
    }
}
