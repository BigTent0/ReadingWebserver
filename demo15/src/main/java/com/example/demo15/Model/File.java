package com.example.demo15.Model;

import java.sql.Date;

public class File {

    private int fileNum;        //文件编号
    private int userID;         //用户ID
    private String fileName;    //文件名
    private String URL;         //文件地址
    private String upTime;        //上传时间
    private int downloads;      //下载次数
    private String fileInfo;    //文件信息

    public File(int userID, String fileName, String URL, String upTime, int downloads, String fileInfo) {
        this.userID = userID;
        this.fileName = fileName;
        this.URL = URL;
        this.upTime = upTime;
        this.downloads = downloads;
        this.fileInfo = fileInfo;
    }

    public File() {
    }

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileNum=" + fileNum +
                ", userID=" + userID +
                ", fileName='" + fileName + '\'' +
                ", URL='" + URL + '\'' +
                ", upTime=" + upTime +
                ", downloads=" + downloads +
                ", fileInfo='" + fileInfo + '\'' +
                '}';
    }
}
