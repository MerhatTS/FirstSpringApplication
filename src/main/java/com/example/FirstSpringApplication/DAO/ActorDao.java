package com.example.FirstSpringApplication.DAO;

import com.example.FirstSpringApplication.Entity.Actor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActorDao extends EntityDao<Actor>{


    @Override
    public Actor getById(int id) {
        return em.find(Actor.class, id);
    }

    @Override
    public List<Actor> getAll() {
        return em.createQuery("from Actor", Actor.class).getResultList();
    }

    @Override
    public void update(Actor entity) {
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
    public void insert(Actor entity) {
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
    public void delete(Actor entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Actor> filterByActor(int actorId){
        return em.createQuery("from Actor a where a.id = :id", Actor.class)
        .setParameter("id", actorId)
        .getResultList();
    }

    @Override
    public void close() throws Exception {

    }
}
