package com.example.miu.service.impl;

import com.example.miu.mapper.CommentMapper;
import com.example.miu.pojo.table.Comment;
import com.example.miu.pojo.table.CommentExample;
import com.example.miu.service.CommentService;
import com.example.miu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <Description> CommentServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName CommentServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Override
    public void addComment(Comment comment) {
        comment.setLikes(0);
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public List<Comment> listCommentByTagId(Integer tagId) {
        //1.查到该博客的所有评论(不分子父评论)
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTagIdEqualTo(tagId);
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        //设置用户名
        for (Comment comment : commentList) {
            comment.setUsername(userService.getUserById(comment.getUserId()).getUsername());
        }
        //2.处理子父关系博客
        return dealChildAndParentComment(commentList);
    }

    private List<Comment> dealChildAndParentComment(List<Comment> commentList) {
        List<Comment> result = new ArrayList<>();
        //先处理顶级评论
        for(Comment comment : commentList){
            if (comment.getRecommentWho()==-1){
                result.add(comment);
                //递归处理每个评论的次级评论
                dealChildComment(comment,comment,commentList);
            }
        }

        return result;
    }

    private void dealChildComment(Comment topComment, Comment dealComment, List<Comment> commentList) {
        for (Comment comment : commentList){
            if (comment.getRecommentWho()==dealComment.getId()){
                if (dealComment.getId()!= topComment.getId()){
                    comment.setReplyUsername(dealComment.getUsername());
                }
                //添加到顶级comment
                topComment.addChildComment(comment);
                //继续递归处理
                dealChildComment(topComment,comment,commentList);
            }
        }
    }
}
