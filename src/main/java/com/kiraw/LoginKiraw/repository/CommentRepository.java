package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void crear(Comments comments) {



        String sql = "insert into comments(comment,clients_id,provider_id,publication_id) VALUES(?,?,?,?)";

        jdbcTemplate.update(sql, comments.getComment(), comments.getClients().getId(),comments.getProvider().getId(), comments.getPublication().getId());
    }


}
