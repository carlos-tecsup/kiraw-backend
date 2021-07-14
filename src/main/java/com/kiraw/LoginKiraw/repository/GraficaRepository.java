package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.entity.Roles;
import com.kiraw.LoginKiraw.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GraficaRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductoRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Publication> obtener(Integer id){
        logger.info("call listar()");

        String sql = "SELECT  publication.title_publication,publication.provider_id,COUNT(comments.comment) as total FROM publication  INNER JOIN comments   ON comments.publication_id=publication.id where publication.provider_id=? GROUP by publication.title_publication ORDER BY publication.id desc  limit 5";

        List<Publication> publications= jdbcTemplate.query(sql, new RowMapper<Publication>() {
            public Publication  mapRow(ResultSet rs, int rowNum) throws SQLException {
                Provider provider=new Provider();

                Publication publication=new Publication();
                provider.setId(rs.getInt("provider_id"));
                publication.setProvider(provider);

                publication.setTitle_publication(rs.getString("title_publication"));
                List<Comments> comments2=new ArrayList<>();
                comments2.add(new Comments(rs.getString("total")));
                publication.setComments(comments2);
                return publication;
            }
        },id);

        logger.info("productos: " + publications);

        return publications;
    }

}
