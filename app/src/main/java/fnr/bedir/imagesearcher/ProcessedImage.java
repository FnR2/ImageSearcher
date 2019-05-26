package fnr.bedir.imagesearcher;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * CREATED BY bbedir on 2019-05-26.
 */

@Entity
public class ProcessedImage {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "file_path")
    private String filePath;
    @ColumnInfo(name = "processed_text")
    private String recognizedText;
    @ColumnInfo(name = "process_result")
    private boolean isProcessed;


    public ProcessedImage(String filePath, String recognizedText, boolean isProcessed) {
        this.filePath = filePath;
        this.recognizedText = recognizedText;
        this.isProcessed = isProcessed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRecognizedText() {
        return recognizedText;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}
