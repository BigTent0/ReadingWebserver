package com.example.demo15.Service.Impl;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.Comment;
import com.example.demo15.Model.CommentDetials;
import com.example.demo15.Model.User;
import com.example.demo15.Model.Users;
import com.example.demo15.Service.ICommentService;
import com.example.demo15.dao.CommentDao;
import com.example.demo15.dao.Userdao;
import com.example.demo15.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service("commentService")
public class CommentService implements ICommentService {
    @Value("${image.baseDir}")
    String baseDir;//文件储存根路径
    @Autowired
    CommentDao commentDao;

    @Override
    public boolean addOneComment(Comment comment, MultipartFile file) {
        try {
            String basePath= FileUtil.saveFile(file,String.valueOf(comment.getUserid()),baseDir,comment.getTime());
            if (!(basePath==null))
            {
                comment.setPicture(basePath);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(basePath);
                Files.write(path,bytes);
                commentDao.addOneComment(comment);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean delOneComment(int id) {
        return false;
    }

    @Autowired
    Userdao userdao;

    @Override
    public List<CommentDetials> getAllComment() {
        List<CommentDetials> detials = new ArrayList<>();
        List<Comment> comments;
        try {
            comments = commentDao.getAllComment();
            for (Comment comment : comments) {
                User user = userdao.selectUserByUserID(comment.getUserid());
                user.setPassword("");
                detials.add(new CommentDetials(comment, user));
            }
        } catch (Exception e) {
            return null;
        }
        return detials;
    }
}
