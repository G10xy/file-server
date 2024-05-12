package itx.fileserver.services.compression;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Bzip2ProcessStrategy implements ProcessStrategy {

    @Override
    public void compress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
            FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             BZip2CompressorOutputStream bzip2OS = new BZip2CompressorOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bzip2OS.write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    public void decompress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             BZip2CompressorInputStream bzip2IS = new BZip2CompressorInputStream(fis)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bzip2IS.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
