package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICommentDao extends CrudRepository<Comments, Integer> {
    public List<Publication> findAllByPublicationId(Integer id);


}
