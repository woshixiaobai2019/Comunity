package com.me.community.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/25 19:24
 */
@Mapper
public interface TagMapper {
    /**
     * 保存问题的标签
     * @param qId 问题的id
     * @param tag 标签的名称
     */
    void save(int qId,String tag);
}
