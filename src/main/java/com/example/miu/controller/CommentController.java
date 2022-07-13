package com.example.miu.controller;

import com.example.miu.pojo.table.Comment;
import com.example.miu.service.CommentService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
/**
 * <Description> CommentController
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentController
 * @taskId
 * @see com.example.miu.controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ReturnObject<String> addComment(Comment comment){
        commentService.addComment(comment);
        return new ReturnObject<>(Global.SUCCESS,"评论成功！");
    }

    @PostMapping("/updateComment")
    public ReturnObject<String> updateComment(Comment comment){
        commentService.updateComment(comment);
        return new ReturnObject<>(Global.SUCCESS,"点赞成功！");
    }

    @GetMapping("/listCommentByTagId")
    public ReturnObject<List<Comment>> listCommentByTagId(Integer tagId){
        List<Comment> commentList = commentService.listCommentByTagId(tagId);
        return new ReturnObject<>(Global.SUCCESS,commentList);
    }


}
