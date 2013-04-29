package org.xezz.zeitabrechnung.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * User: Xezz
 * Date: 28.04.13
 * Time: 00:01
 * see also http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/
 */
public abstract class AbstractJpaDAO<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T getById(Long id){
        return entityManager.find( clazz, id );
    }
    public List<T> findAll(){
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void save(T entity){
        entityManager.persist(entity);
    }

    public void update(T entity){
        entityManager.merge(entity);
    }

    public void delete(T entity){
        entityManager.remove(entity);
    }
    public void deleteById(Long entityId){
        T entity = getById(entityId);
        delete(entity);
    }
}