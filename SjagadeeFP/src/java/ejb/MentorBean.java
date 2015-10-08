/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Mentor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Srinivas
 */
@Named
@Stateless
public class MentorBean {
    
    @PersistenceContext(unitName = "SjagadeeFPPU")
    private EntityManager em;

    public MentorBean() {
    }

    public void create(Mentor m){
        em.persist(m);
    }
    
    public void update(Mentor m){
        em.merge(m);
    }
    
    public void remove(Mentor m){
        em.remove(m);
    }
    
    public Mentor find(long id){
        return em.find(Mentor.class, id);
    }
    
    public List<Mentor> findAll(){
        return em.createNamedQuery("Mentor.findAll", Mentor.class).getResultList();
    }
    
    public Mentor findByUsername(String userName){
        return em.createNamedQuery("Mentor.findByUsername", Mentor.class).setParameter("userName", userName).getSingleResult();
    }
}
