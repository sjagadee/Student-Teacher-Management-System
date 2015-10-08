/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domian.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Srinivas
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Mentor.findAll", query = "select m from Mentor m"),
    @NamedQuery(name = "Mentor.findByUsername", query = "select m from Mentor m where m.user.userName = :userName")
})
@Cacheable(false)
public class Mentor extends CommonEntity implements Serializable {

    private String mentorName;
    private String mentorEmail;
    private String mentorCourse;

    @OneToMany(mappedBy = "mentor")
    private List<Question> question = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addQuestion(Question q) {
        if(!this.question.contains(q)) {
            this.question.add(q);
        }
        if(q.getMentor() != this) {
            q.setMentor(this);
        }
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public Mentor() {
    }

    public Mentor(String mentorName, String mentorEmail, String mentorCourse) {
        
        this.mentorName = mentorName;
        this.mentorEmail = mentorEmail;
        this.mentorCourse = mentorCourse;
    }

    /**
     * Get the value of mentorCourse
     *
     * @return the value of mentorCourse
     */
    public String getMentorCourse() {
        return mentorCourse;
    }

    /**
     * Set the value of mentorCourse
     *
     * @param mentorCourse new value of mentorCourse
     */
    public void setMentorCourse(String mentorCourse) {
        this.mentorCourse = mentorCourse;
    }

    /**
     * Get the value of mentorEmail
     *
     * @return the value of mentorEmail
     */
    public String getMentorEmail() {
        return mentorEmail;
    }

    /**
     * Set the value of mentorEmail
     *
     * @param mentorEmail new value of mentorEmail
     */
    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    /**
     * Get the value of mentorName
     *
     * @return the value of mentorName
     */
    public String getMentorName() {
        return mentorName;
    }

    /**
     * Set the value of mentorName
     *
     * @param mentorName new value of mentorName
     */
    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

   

    @Override
    public String toString() {
        return "Mentor{" + ", mentorName=" + mentorName + ", mentorEmail=" + mentorEmail + ", mentorCourse=" + mentorCourse + '}';
    }

}
