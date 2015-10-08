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
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Student.findAll", query = "select s from Student s"),
    @NamedQuery(name = "Student.findByUsername", query = "select s from Student s where s.user.userName = :userName")
})
@Cacheable(false)
public class Student extends CommonEntity implements Serializable {

    private String studentName;
    private String studentEmail;

    @OneToMany(mappedBy = "student")
    private List<Rating> rating = new ArrayList<>();

    @ManyToMany
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
        if (!this.question.contains(q)) {
            this.question.add(q);
        }
        if (!q.getStudent().contains(this)) {
            q.getStudent().add(this);
        }
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;

    }

    public void addRating(Rating r) {
        if (!this.rating.contains(r)) {
            this.rating.add(r);
        }
        if (r.getStudent() != this) {
            r.setStudent(this);
        }
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public Student() {
    }

    public Student(String studentName, String studentEmail) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" + "studentName=" + studentName + ", studentEmail=" + studentEmail + '}';
    }

}
