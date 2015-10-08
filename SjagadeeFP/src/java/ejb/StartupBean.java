/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Mentor;
import domain.Question;
import domain.Rating;
import domain.Student;
import domian.security.Group;
import domian.security.User;
import ejb.security.GroupBean;
import ejb.security.UserBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Srinivas
 */
@Singleton
@Startup
public class StartupBean {
    
    @EJB
    private UserBean userBean;
    @EJB
    private GroupBean groupBean;
    @EJB
    private StudentBean studentBean;
    @EJB
    private RatingBean ratingBean;
    @EJB
    private QuestionBean questionBean;
    @EJB
    private MentorBean mentorBean;

    
    public StartupBean() {
    }

    @PostConstruct
    private void populateDatabas() {

        //groups
        Group studentGroup = new Group("students", "Group of all Students");
        Group mentorsGroup = new Group("mentors", "Group of all Mentors");

        //users
        User u1 = new User("munaz", "munaz");
        User u2 = new User("srinivas", "srinivas");
        User u3 = new User("nandini", "nandini");
        User u4 = new User("scott", "scott");
        User u5 = new User("jermey", "jermey");
        User u6 = new User("carol", "carol");
        u1.addGroup(studentGroup);
        u2.addGroup(studentGroup);
        u3.addGroup(studentGroup);
        u4.addGroup(mentorsGroup);
        u5.addGroup(mentorsGroup);
        u6.addGroup(mentorsGroup);

        //rating entries
        Rating r1 = new Rating("relavent", "Best Professor", 5);
        Rating r2 = new Rating("relavent", "Good Professor", 4);
        Rating r3 = new Rating("relavent", "Bad Professor", 1);

        //student entries
        Student s1 = new Student("Munaz", "munaz@gmail.com");
        Student s2 = new Student("Srinivas", "srini@gmail.com");
        Student s3 = new Student("Nandini", "nandini@gmail.com");
        s1.addRating(r3);
        s2.addRating(r2);
        s3.addRating(r1);
        s1.setUser(u1);
        s2.setUser(u2);
        s3.setUser(u3);

        //question entries
        Question q1 = new Question("ITMD 515", "Forign Key Constraint");
        Question q2 = new Question("ITMD 545", "Cloud is Running");
        Question q3 = new Question("ITMD 540", "Network is Not Connected");
        q1.addStudent(s3);
        q2.addStudent(s2);
        q3.addStudent(s1);

        //mentor entries
        Mentor m1 = new Mentor("Scott", "scott@gmail.com", "ITMD 515");
        Mentor m2 = new Mentor("Jermey", "jermey@gmail.com", "ITMD 545");
        Mentor m3 = new Mentor("Carol", "carol@gmail.com", "ITMD 540");
        m1.addQuestion(q3);
        m2.addQuestion(q2);
        m3.addQuestion(q1);
        m1.setUser(u4);
        m2.setUser(u5);
        m3.setUser(u6);

        Group adminGroup = new Group("administrators", "Administrators Group");
        User adminUser = new User("admin", "admin");
        adminUser.addGroup(adminGroup);
        groupBean.create(adminGroup);
        userBean.create(adminUser);
        
        groupBean.create(studentGroup);
        groupBean.create(mentorsGroup);
        userBean.create(u1);
        userBean.create(u2);
        userBean.create(u3);
        userBean.create(u4);
        userBean.create(u5);
        userBean.create(u6);
        ratingBean.create(r1);
        ratingBean.create(r2);
        ratingBean.create(r3);
        studentBean.create(s1);
        studentBean.create(s2);
        studentBean.create(s3);
        questionBean.create(q1);
        questionBean.create(q2);
        questionBean.create(q3);
        mentorBean.create(m1);
        mentorBean.create(m2);
        mentorBean.create(m3);
    }

}
