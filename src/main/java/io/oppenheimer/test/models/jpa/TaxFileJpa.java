package io.oppenheimer.test.models.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class TaxFileJpa {

    @Id
    private int id;
    private int currentCount;
    private String filePath;
    private String fileStatus;
    private int totalCount;
    private String fileType;

    public TaxFileJpa() {
    }

    public TaxFileJpa(int id,
                      int currentCount,
                      String filePath,
                      String fileStatus,
                      int totalCount) {
        this.id = id;
        this.currentCount = currentCount;
        this.filePath = filePath;
        this.fileStatus = fileStatus;
        this.totalCount = totalCount;

    }

    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "current_count", nullable = false)
    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    @Column(name = "file_path", nullable = false)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Column(name = "file_status", nullable = false)
    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Column(name = "total_count", nullable = false)
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Column(name = "file_type", nullable = false)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileStatus = fileType;
    }
}
