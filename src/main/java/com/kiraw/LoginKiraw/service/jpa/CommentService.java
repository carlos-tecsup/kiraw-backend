package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.ICommentDao;
import com.kiraw.LoginKiraw.repository.IPublicationDao;
import com.kiraw.LoginKiraw.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CommentService  implements ICommentService {
    @Autowired
    private ICommentDao commentDao;

    public List<Comments> findAll() {

        return   (List<Comments>) commentDao.findAll();


    }


    public void crear(Comments comments) {
        commentDao.save(comments);
    }








    @Override
    @Transactional
    public Comments save(Comments comments) {
        return commentDao.save(comments);
    }


    public Comments save2(Comments comments) {
        return commentDao.save(comments);
    }


}
