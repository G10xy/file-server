package itx.fileserver.services.compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipProcessStrategy implements ProcessStrategy {

    @Override
    public void compress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             ZipOutputStream zipOS = new ZipOutputStream(fos)) {
            ZipEntry zipEntry = new ZipEntry(inputPath.getFileName().toString());
            zipOS.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                zipOS.write(buffer, 0, bytesRead);
            }
            zipOS.closeEntry();
        }
    }

    @Override
    public void decompress(Path inputPath, Path outputPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath.toFile());
             FileOutputStream fos = new FileOutputStream(outputPath.toFile());
             ZipInputStream zipIS = new ZipInputStream(fis)) {
            ZipEntry zipEntry = zipIS.getNextEntry();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zipIS.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            zipIS.closeEntry();
        }
    }
}
