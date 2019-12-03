package com.example.demo15.utils;

import com.example.demo15.Common.ServerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public static String saveFile(MultipartFile file,String id,String baseDir,String time) {
        String basePath = null;
        if (!file.isEmpty()) {
            String parDir = baseDir + id + "/";
            File temp = new File(parDir);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            basePath = parDir + time + "_" + file.getOriginalFilename();

            return basePath;
        }
        return basePath;
    }
}

