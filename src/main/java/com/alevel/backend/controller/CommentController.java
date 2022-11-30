package com.alevel.backend.controller;

import com.alevel.backend.controller.dto.CommentRequestDto;
import com.alevel.backend.controller.dto.CommentSaveDto;
import com.alevel.backend.domain.response.ResultResponse;
import com.alevel.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){this.commentService=commentService;}

    //댓글 등록
    @PostMapping(value="/posts/{id}/comment")
    public ResultResponse saveComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto){
        CommentSaveDto dto = new CommentSaveDto(id, requestDto.getUserid(),requestDto.getContent());
        commentService.saveComment(dto);
        return ResultResponse.success();
    }

    //댓글 삭제
    @DeleteMapping(value="/posts/{post-id}/comment/{comment-id}")
    public ResultResponse deleteComment(@PathVariable("post-id")Long PostId,@PathVariable("comment-id") Long CommentId){
        commentService.delete(CommentId);
        return ResultResponse.success();
    }


}
