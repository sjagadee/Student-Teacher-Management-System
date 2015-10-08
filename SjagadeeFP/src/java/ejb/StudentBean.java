/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Srinivas
 */
@Named
@Stateless
public class StudentBean extends AbstractBean<Student> {

    public StudentBean() {
        super(Student.class);
    }

    @Override
    public List<Student> findAll() {
        return getEntityManager().createNamedQuery("Student.findAll",Student.class).getResultList();
    }
    
    public Student findByUsername(String userName){
        
        TypedQuery<Student> query = getEntityManager().createNamedQuery("Student.findByUsername", Student.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();
       // return getEntityManager().createNamedQuery("Student.findByUsername", Student.class).getSingleResult();
    }
    
}
