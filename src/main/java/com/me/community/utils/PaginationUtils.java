package com.me.community.utils;

import com.me.community.dto.Pagination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/26 14:52
 */
@Component
public class PaginationUtils {
    public Pagination getPagination(int currentPage,int pageSize,int maxPageNum,int totalCount){
        Pagination pagination = Pagination.builder().currentPage(currentPage).pageSize(pageSize).maxPageNum(maxPageNum).build();
        int totalPage = totalCount%pageSize ==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pagination.setTotalPage(totalPage);
        //如果当前页不为首页就有上一页的按钮
        pagination.setShowPrePageButton(currentPage != 1);
        // 如果当前页不为最后一页就显示有下一页的按钮
        pagination.setShowNextPageButton(currentPage != totalPage);
        //如果当期总共的页数小于等于最大展示页数，就显示全部的页数
        ArrayList<Integer> showPages = new ArrayList<>();
        if (totalPage<=maxPageNum){
            for (int i = 1; i <= totalPage; i++) {
                showPages.add(i);
            }
        }else{
            //如果当前页小于最大页数的一半就显示从第一页到最大页数
            if (currentPage<=maxPageNum/2){
                for (int i = 1; i <=maxPageNum; i++) {
                    showPages.add(i);
                }
            }else{
                //否则就以当前页为中间页面显示
                for (int i = currentPage-(maxPageNum/2); i <=currentPage+(maxPageNum/2) && i<=totalPage; i++) {
                    showPages.add(i);
                }
            }
        }
        pagination.setShowPageList(showPages);
        //如果展示的页里面包含第一页，就不显示到第一页的按钮
        pagination.setShowFirstPageButton(!showPages.contains(1));
        //如果展示的页面里包含最后一页，就不显示到最后一页的按钮
        pagination.setShowLastPageButton(!showPages.contains(totalPage));
        return pagination;
    }
}
