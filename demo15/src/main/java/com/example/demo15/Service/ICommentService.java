package com.example.demo15.Service;

import com.example.demo15.Model.Comment;
import com.example.demo15.Model.CommentDetials;

import java.util.List;

public interface ICommentService {
    boolean addOneComment(Comment comment);
    boolean delOneComment(int id);
    List<CommentDetials> getAllComment();
}
