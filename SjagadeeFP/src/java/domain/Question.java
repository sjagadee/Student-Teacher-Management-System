/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author Srinivas
 */
@Entity
@NamedQuery(name = "Question.findAll", query = "select q from Question q")
@Cacheable(false)
public class Question extends CommonEntity implements Serializable {
    
  
    private String questionCourse;
    private String questionAnswer;
    
    @ManyToOne
    private Mentor mentor;
    
    @ManyToMany(mappedBy = "question")
    private List<Student> student = new ArrayList<>();

    public void addStudent(Student s) {
        if(!this.student.contains(s)) {
            this.student.add(s);
        }
        if(!s.getQuestion().contains(this)) {
            s.getQuestion().add(this);
        }
    }

    public List<Student> getStudent() {
        return student;
    }
    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
       
        if(!mentor.getQuestion().contains(this)){
            mentor.getQuestion().add(this);
        }
    }

    public Question() {
    }

    public Question( String questionCourse, String questionAnswer) {
     
        this.questionCourse = questionCourse;
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionCourse() {
        return questionCourse;
    }

    public void setQuestionCourse(String questionCourse) {
        this.questionCourse = questionCourse;
    }

    @Override
    public String toString() {
        return "Question{" + ", questionCourse=" + questionCourse + ", questionAnswer=" + questionAnswer + '}';
    }

}
