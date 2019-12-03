package com.example.demo15.Service;

import com.example.demo15.Model.Comment;
import com.example.demo15.Model.CommentDetials;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICommentService {
    boolean addOneComment(Comment comment,MultipartFile file);
    boolean delOneComment(int id);
    List<CommentDetials> getAllComment();

}
