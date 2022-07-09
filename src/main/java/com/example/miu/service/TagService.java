package com.example.miu.service;

import com.example.miu.pojo.table.Tag;

/**
 * <Description> TagService
 *
 * @author 26802
 * @version 1.0
 * @ClassName TagService
 * @taskId
 * @see com.example.miu.service
 */
public interface TagService {

    //添加一个标记点
    void addTag(Tag tag);

    //更新标记点
    void updateTag(Tag tag);

}
