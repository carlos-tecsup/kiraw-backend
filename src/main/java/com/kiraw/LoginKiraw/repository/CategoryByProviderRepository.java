package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryByProviderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> listar() {

        String sql = "SELECT name,id FROM categories GROUP BY(name) where categories.provider_id=?";

        List<Category> productos = jdbcTemplate.query(sql, new RowMapper<Category>() {
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                return category;
            }
        });


        return productos;
    }
}
