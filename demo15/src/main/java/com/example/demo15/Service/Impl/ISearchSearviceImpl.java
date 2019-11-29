package com.example.demo15.Service.Impl;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.BookBean;
import com.example.demo15.Model.File;
import com.example.demo15.Service.ISearchService;
import com.example.demo15.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
@Service("searchService")
public class ISearchSearviceImpl implements ISearchService {
    private final String basePath = "/books/";//文件储存根路径
    private String name="";
    private String userID="";
@Autowired
    FileDao fileDao;
  /*  @Override
    public List<BookBean> search(String name) {
        List<BookBean> list = new ArrayList<>();
        File parFile = new File(basePath);
        if(!parFile.exists())
            return null;
        for(File file : parFile.listFiles()){
            if(file.isDirectory()){
                list.addAll(searchFile(file,name));
            }
        }
        return list;
    }
    private List<BookBean> searchFile(File parDir, String name){
        List<BookBean> list = new ArrayList<>();
        for(File file : parDir.listFiles()){
            if(file.getName().contains(name)){
                list.add(new BookBean(file.getName(),file.getAbsolutePath()));
            }
        }
        return list;
    }
    */

    public ServerResponse<List<BookBean>> search(String fileName)
    {
        System.out.println(fileName);
        List<File> list = fileDao.selectFileByFileName(fileName);
        List<BookBean> bookBeans = new ArrayList<>();
        for(File file: list){
            bookBeans.add(new BookBean(file.getFileName(),file.getFileNum()));
        }
        if (bookBeans==null)
        {
            return ServerResponse.createErrorMessage("没有此小说");
        }
        return ServerResponse.createSuccessMessageAndData("有"+bookBeans.size()+"条结果",bookBeans);
    }
}
