package com.example.demo15.dao;

import com.example.demo15.Model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentDao {
    public void addOneComment(@Param("comment") Comment comment);
    public void delOneComment(@Param("id") int id);
    public List<Comment> getAllComment();
}
