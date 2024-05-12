package itx.fileserver.dto;

public class CompressDecompressRequest {

    private String processedFilePath;

    public CompressDecompressRequest() {
    }

    public CompressDecompressRequest(String processedFilePath) {
        this.processedFilePath = processedFilePath;
    }

    public String getProcessedFilePath() {
        return processedFilePath;
    }

    public void setProcessedFilePath(String processedFilePath) {
        this.processedFilePath = processedFilePath;
    }
}
