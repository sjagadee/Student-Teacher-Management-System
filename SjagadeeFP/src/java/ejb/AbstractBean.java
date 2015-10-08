/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Srinivas
 * @param <T>
 */
public abstract class AbstractBean<T> {
    
    @PersistenceContext(unitName = "SjagadeeFPPU")
    private EntityManager em;
    
    private final Class<T> entityClass;
    
    protected AbstractBean(Class entityClass){
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public void create(T entity) {
        em.persist(entity);
    }
    
    public T find(Object id){
        return em.find(entityClass, id);
    }
    
    public abstract List<T> findAll();
    
    public void update(T entity) {
        em.merge(entity);
    }
    
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }
}
