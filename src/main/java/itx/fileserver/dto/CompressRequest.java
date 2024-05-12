package itx.fileserver.dto;

public class CompressRequest {

    private String compressedFilePath;

    public CompressRequest() {
    }

    public CompressRequest(String compressedFilePath) {
        this.compressedFilePath = compressedFilePath;
    }

    public String getCompressedFilePath() {
        return compressedFilePath;
    }

    public void setCompressedFilePath(String compressedFilePath) {
        this.compressedFilePath = compressedFilePath;
    }
}
