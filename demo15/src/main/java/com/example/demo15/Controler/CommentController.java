package com.example.demo15.Controler;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.Comment;
import com.example.demo15.Model.CommentDetials;
import com.example.demo15.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {
    @Autowired()
    @Qualifier("commentService")
    ICommentService commentService;
    @RequestMapping("/addComment")
    @ResponseBody
    public ServerResponse addComment(@RequestBody Comment comment){
        if(null==comment||comment.getUserid()==0){
            return ServerResponse.createErrorMessage("发布失败,服务器响应失败");
        }
        if(commentService.addOneComment(comment)){
            return ServerResponse.createSuccessMessage("评论发布成功");
        }
        return ServerResponse.createErrorMessage("操作响应失败");

    }
    @RequestMapping("/getComments")
    @ResponseBody
    public ServerResponse<List<CommentDetials>> getComments(){
        List<CommentDetials> comments;
        comments = commentService.getAllComment();
        if(null==comments){
            return ServerResponse.createErrorMessage("数据访问出错");
        }
        return ServerResponse.createSuccessData(comments);
    }
}
