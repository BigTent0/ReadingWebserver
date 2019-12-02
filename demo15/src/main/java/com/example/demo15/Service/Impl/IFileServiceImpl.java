package com.example.demo15.Service.Impl;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.BookBean;
import com.example.demo15.Service.IFileService;
import com.example.demo15.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("IFileService")
public class IFileServiceImpl implements IFileService {
    @Value("${book.baseDir}")
    String baseDir;//文件储存根路径
    private String name="";
    private String userID="001";
    @Autowired
    FileDao fileDao;
    /**
     * 存文件
     * @param inputStream
     * @param fileName
     * @return
     */
    @Override
    public String saveFile(InputStream inputStream,String fileName) {
        DataInputStream dis = new DataInputStream(inputStream);
        File file = new File(baseDir+userID,fileName);
        if(!file.exists()){
            try {
                System.out.println(file.getAbsolutePath());
                 file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        FileOutputStream fps = null;
        try {
            fps = new FileOutputStream(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;

        try {
            while ((length=dis.read(buffer))!=-1){
                fps.write(buffer,0,length);
            }
            fps.flush();
            fps.close();
            dis.close();
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return "上传成功！";
    }

    /**
     * 根据文件名上传文件
     * @param file
     * @param id
     * @return
     */
    @Override
    public ServerResponse uploadFile(MultipartFile file, String id) {
        ServerResponse response;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(new Date());
        if(!file.isEmpty()){
            try {
                String parDir = baseDir+id+"/";
                File temp = new File(parDir);
                if(!temp.exists()){
                    temp.mkdirs();
                }
                String basePath = parDir+time+"_"+file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                Path path = Paths.get(basePath);
                Files.write(path,bytes);
                com.example.demo15.Model.File file1 = new com.example.demo15.Model.File(Integer.parseInt(id)
                        ,file.getOriginalFilename()
                        ,basePath,time,0
                        ,file.getContentType()+"_"+file.getSize());
                fileDao.addFileInfo(file1);

                response = ServerResponse.createSuccessMessage(file.getOriginalFilename() + ":上传成功!");
                System.out.println("上传成功！");
            } catch (IOException e) {
                response = ServerResponse.createErrorMessage("文件上传失败！");
                e.printStackTrace();
            }
        }else {
            response = ServerResponse.createErrorMessage("文件上传失败！");
        }
        return response;
    }

    /**
     * 根据路径上传文件
     */
    @Override
    public ServerResponse download(OutputStream outputStream,int fileNum) {
        com.example.demo15.Model.File f = fileDao.selectFileByFileNum(fileNum);
        if(null == f)
            return ServerResponse.createErrorMessage("无该资源");
        File file = new File(f.getURL());
        System.out.println("文件下载路径："+file.getAbsolutePath());
        if(file.exists()){
            try {
                DataOutputStream out = new DataOutputStream(outputStream);
                FileInputStream fileInputStream = new FileInputStream(file);
                int bufferSize = 1024;
                byte[] bytes = new byte[bufferSize];
                int len = -1;
                while ((len=fileInputStream.read(bytes))!=-1){
                    out.write(bytes,0,len);
                }
                out.flush();
                fileInputStream.close();
                out.close();
                return ServerResponse.createSuccessMessage("下载成功！");
            }catch (Exception e){
                e.printStackTrace();
                return ServerResponse.createErrorMessage("网络链接出错！");
            }
        }
        return ServerResponse.createErrorMessage("无法找到该资源！");
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public ServerResponse<List<BookBean>> getLastBooks()
    {
        List<BookBean> bookBeans=fileDao.getLastBookBeans();
        if(bookBeans==null) {
            return ServerResponse.createErrorMessage("请求失败");
        }
        return ServerResponse.createSuccessMessageAndData("成功",bookBeans);
    }
}
