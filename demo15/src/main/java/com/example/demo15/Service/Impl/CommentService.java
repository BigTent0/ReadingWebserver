package com.example.demo15.Service.Impl;

import com.example.demo15.Model.Comment;
import com.example.demo15.Model.CommentDetials;
import com.example.demo15.Model.User;
import com.example.demo15.Model.Users;
import com.example.demo15.Service.ICommentService;
import com.example.demo15.dao.CommentDao;
import com.example.demo15.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("commentService")
public class CommentService implements ICommentService {

    @Autowired
    CommentDao commentDao;
    @Override
    public boolean addOneComment(Comment comment) {
        try {
            commentDao.addOneComment(comment);
        }catch (Exception e){
            return false;
        }
        return true;
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
            for(Comment comment:comments){
                User user = userdao.selectUserByUserID(comment.getUserid());
                user.setPassword("");
                detials.add(new CommentDetials(comment,user));
            }
        }catch (Exception e){
            return null;
        }
        return detials;
    }
}
