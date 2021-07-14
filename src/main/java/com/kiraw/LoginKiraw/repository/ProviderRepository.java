package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class    ProviderRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProviderRepository.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void crear(Provider provider) {
        logger.info("crear " + provider);

            String sql = "update provider set img_profile=?,img_background=?,address=?,city=?,phone=?,name=? where id=?";

        jdbcTemplate.update(sql, provider.getImgProfile(),provider.getImgBackground(),provider.getAddress(),provider.getCity(),provider.getPhone(),provider.getName(),provider.getId());
    }

    public void crearimg(Provider provider) {
        logger.info("crear " + provider);

        String sql = "update provider set img_background=? where id=?";

        jdbcTemplate.update(sql, provider.getImgBackground(),provider.getId());
    }



}
