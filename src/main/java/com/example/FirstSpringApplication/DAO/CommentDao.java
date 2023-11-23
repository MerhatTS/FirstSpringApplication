package com.example.FirstSpringApplication.DAO;

import com.example.FirstSpringApplication.Entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDao extends EntityDao<Comment>{
    @Override
    public Comment getById(int id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("from Comment", Comment.class).getResultList();
    }

    @Override
    public void update(Comment entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Comment entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Comment entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
