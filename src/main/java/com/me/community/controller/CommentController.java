package com.me.community.controller;

import com.me.community.consts.WebConst;
import com.me.community.dto.CommentDto;
import com.me.community.dto.Pagination;
import com.me.community.dto.ResponseData;
import com.me.community.pojo.Comment;
import com.me.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/31 11:48
 */
@Controller
public class CommentController extends BaseController{
    @Autowired
    CommentService commentService;
    @PostMapping("/comment")
    @ResponseBody
    public ResponseData comment(HttpServletRequest request,
                                @RequestParam(name = "parentId") Long parentId,
                                @RequestParam(name = "type") Integer type,
                                @RequestParam(name = "content") String content){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        if (parentId==null || type==null || StringUtils.isEmpty(content)){
            build.setStatus(WebConst.ERROR);
            build.setResponse("非法评论");
        }else{
            build.setStatus(WebConst.OK);
            Comment comment = Comment.builder().commentator((Long) getAttributeFromSession(request, WebConst.USER_SESSION_PREFIX))
                    .content(content)
                    .parentId(parentId)
                    .type(type)
                    .build();
            commentService.save(comment);
        }
        return build;
    }
    @PostMapping("/firstLevelComment")
    @ResponseBody
    public ResponseData getFirstLevelComment(@RequestParam(name = "qId",defaultValue = "0") long qId,@RequestParam(name = "currentPage",defaultValue = "1") int currentPage){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        if (currentPage<1 || qId==0){
            build.setStatus(WebConst.ERROR);
            build.setResponse("参数错误");
        }else{
            Pagination pagination = commentService.getFirstLevelComment(qId,currentPage);
            build.setResponse(pagination);
            build.setStatus(WebConst.OK);
        }
        return build;
    }
    @PostMapping("/secondLevelComment")
    @ResponseBody
    public ResponseData getSecondLevelComment(@RequestParam(name = "parentId",defaultValue = "0") long parentId){
        ResponseData build = ResponseData.builder().timeStamp(System.currentTimeMillis()).build();
        if (parentId==0){
            build.setStatus(WebConst.ERROR);
            build.setResponse("参数错误");
        }else{
            build.setStatus(WebConst.OK);

            List<CommentDto> commentDtoList =  commentService.getSecondLevelComment(parentId);
            build.setResponse(commentDtoList);
        }
        return build;
    }
}
