package com.kiraw.LoginKiraw.controller;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.entity.ResponseMessage;
import com.kiraw.LoginKiraw.service.ICommentService;
import com.kiraw.LoginKiraw.service.IPublicationService;
import com.kiraw.LoginKiraw.service.jpa.CommentService;
import com.kiraw.LoginKiraw.service.jpa.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService2;
    @Autowired
    private ICommentService commentService;

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/comments")
    public List<Comments> index() {
        List<Comments> comments = commentService.findAll();

        return comments;
    }


    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @PostMapping("/comment")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Comments create(@Valid @RequestBody Comments comments) {


        return  commentService.save(comments);





    }


}
