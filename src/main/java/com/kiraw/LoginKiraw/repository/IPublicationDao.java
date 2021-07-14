package com.kiraw.LoginKiraw.repository;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface IPublicationDao  extends JpaRepository<Publication, Integer> {

    //public List<Publication> findPublicationByIdOrderByIdDesc(Integer id);
    //public List<Publication> findPublicationByIdOrderBy(Integer id);

    public List<Publication> findPublicationByIdOrderByCommentsDesc(Integer id);
    public  List<Publication> findPublicationByProviderIdOrderByIdDesc(Integer id);
    Page<Publication> findPublicationByProviderIdOrderByIdDesc(Integer id, Pageable pageable);

}
