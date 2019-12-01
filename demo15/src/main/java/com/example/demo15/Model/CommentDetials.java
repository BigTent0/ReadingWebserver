package com.example.demo15.Model;

public class CommentDetials {
    private Comment comment;
    private User user;

    public CommentDetials() {
    }

    public CommentDetials(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentDetials{" +
                "comment=" + comment +
                ", users=" + user +
                '}';
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
