package ru.gubernik.fileservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Файл
 */
@Entity
@Table(name = "USER_FILE")
public class File {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя файла
     */
    @Column(name = "FILE_NAME", length = 100)
    private String fileName;

    /**
     * Данные файла
     */
    @Column(name = "FILE_DATA")
    private byte[] fileData;

    /**
     * Описание
     */
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    /**
     * Владелец файла
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public File(){

    }

    public Integer getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.getFiles().add(this);
        this.user = user;
    }
}
