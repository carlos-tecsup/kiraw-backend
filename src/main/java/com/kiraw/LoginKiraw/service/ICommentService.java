package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;

import java.util.List;

public interface ICommentService {
    public List<Comments> findAll();
    public Comments save(Comments comments);

}
