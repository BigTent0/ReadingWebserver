package com.example.demo15.Service;
import com.example.demo15.Common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

public interface IFileService {
    String saveFile(InputStream inputStream, String fileName);
    ServerResponse uploadFile(MultipartFile file, String userId);
    ServerResponse download(OutputStream outputStream,int fileNum);
    String getFileName();
}
