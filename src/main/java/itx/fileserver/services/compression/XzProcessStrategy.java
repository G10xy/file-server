package itx.fileserver.services.compression;

import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

import java.io.*;
import java.nio.file.Path;

public class XzProcessStrategy implements ProcessStrategy {

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

    @Override
    public void decompress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             BufferedInputStream bis = new BufferedInputStream(fis);
             XZCompressorInputStream xzIn = new XZCompressorInputStream(bis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = xzIn.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }
}
