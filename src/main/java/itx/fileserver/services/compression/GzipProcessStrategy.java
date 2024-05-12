package itx.fileserver.services.compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipProcessStrategy implements ProcessStrategy {

    @Override
    public void compress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             GZIPOutputStream gzipOS = new GZIPOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    public void decompress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             GZIPInputStream gzipIS = new GZIPInputStream(fis)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipIS.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
