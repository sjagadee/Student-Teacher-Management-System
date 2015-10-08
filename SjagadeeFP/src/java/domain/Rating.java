/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author Srinivas
 */
@Entity
@NamedQuery(name = "Rating.findAll", query = "select r from Rating r")
@Cacheable(false)
public class Rating extends CommonEntity implements Serializable {

    private String ratingRelevance;
    private String ratingComment;
    private Integer ratingStar;
    
    @ManyToOne
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        
        if (!student.getRating().contains(this)){
            student.getRating().add(this);
        }
    }

    public Rating() {
    }

    public Rating(String ratingRelevance, String ratingComment, Integer ratingStar) {
        
        this.ratingRelevance = ratingRelevance;
        this.ratingComment = ratingComment;
        this.ratingStar = ratingStar;
    }

    /**
     * Get the value of ratingStar
     *
     * @return the value of ratingStar
     */
    public Integer getRatingStar() {
        return ratingStar;
    }

    /**
     * Set the value of ratingStar
     *
     * @param ratingStar new value of ratingStar
     */
    public void setRatingStar(Integer ratingStar) {
        this.ratingStar = ratingStar;
    }

    
    

    /**
     * Get the value of ratingComment
     *
     * @return the value of ratingComment
     */
    public String getRatingComment() {
        return ratingComment;
    }

    /**
     * Set the value of ratingComment
     *
     * @param ratingComment new value of ratingComment
     */
    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    /**
     * Get the value of ratingRelevance
     *
     * @return the value of ratingRelevance
     */
    public String getRatingRelevance() {
        return ratingRelevance;
    }

    /**
     * Set the value of ratingRelevance
     *
     * @param ratingRelevance new value of ratingRelevance
     */
    public void setRatingRelevance(String ratingRelevance) {
        this.ratingRelevance = ratingRelevance;
    }

    
    @Override
    public String toString() {
        return "Rating{" + ", ratingRelevance=" + ratingRelevance + ", ratingComment=" + ratingComment + ", ratingStar=" + ratingStar + '}';
    }

}
