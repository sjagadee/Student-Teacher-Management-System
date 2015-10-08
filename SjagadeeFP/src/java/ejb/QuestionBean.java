/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Question;
import domain.Student;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class QuestionBean extends AbstractBean<Question> {

    public QuestionBean() {
        super(Question.class);
    }

    @Override
    public List<Question> findAll() {
        return getEntityManager().createNamedQuery("Question.findAll", Question.class).getResultList();
    }

    @Override
    public void delete(Question question) {
        question = getEntityManager().getReference(Question.class, question.getId());
        
        //Handel the mentor relationship with question
        question.getMentor().getQuestion().remove(question);
        
        //Handel the student relationship with question
        for(Student s : question.getStudent()){
            s.getQuestion().remove(question);
        }
        
        getEntityManager().remove(question);
                
    }
    
    

}
