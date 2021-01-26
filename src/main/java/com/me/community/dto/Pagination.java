package com.me.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 14:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
    /**
     * 当前的页
     */
    private int currentPage;
    /**
     * 每一页的总条数
     */
    private int pageSize;
    /**
     * 总的页数
     */
    private int totalPage;
    /**
     * 最大显示的总页数
     */
    private int maxPageNum;
    /**
     * 当前页面展示的页的集合
     */
    private List<Integer> showPageList;
    /**
     * 是否有可以展示可以到最后一页的按钮
     */
    private boolean showLastPageButton;
    /**
     * 是否展示可以到首页的按钮
     */
    private boolean showFirstPageButton;
    /**
     * 是否展示下一页的按钮
     */
    private boolean showNextPageButton;
    /**
     * 是否展示上一页的按钮
     */
    private boolean showPrePageButton;
    /**
     * 问题的数据
     */
    private List<QuestionDto> questions;

}
