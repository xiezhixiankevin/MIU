package com.example.miu.controller;

import com.example.miu.pojo.table.CommentOfTag;
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
    public ReturnObject<String> addComment(CommentOfTag commentOfTag){
        commentService.addComment(commentOfTag);
        return new ReturnObject<>(Global.SUCCESS,"评论成功！");
    }

    @GetMapping("/listCommentByTagId")
    public ReturnObject<List<CommentOfTag>> listCommentByTagId(Integer tagId){
        List<CommentOfTag> commentOfTagList = commentService.listCommentByTagId(tagId);
        return new ReturnObject<>(Global.SUCCESS,commentOfTagList);
    }


}
