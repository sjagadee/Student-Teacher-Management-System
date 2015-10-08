/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import domain.Mentor;
import domain.Question;
import domain.Student;
import ejb.MentorBean;
import ejb.QuestionBean;
import ejb.StudentBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Srinivas
 */
@Named
@RequestScoped
public class QuestionController extends AbstractController {

    @Inject
    private QuestionBean questionBean;
    @Inject
    private StudentBean studentBean;
    @Inject
    private MentorBean mentorBean;
    @Inject
    LoginController loginController;

    private static final Logger LOG = Logger.getLogger(QuestionController.class.getName());

    private List<Question> questions;
    private Question question;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public QuestionController() {
    }

    private void refreshQuestionList() {
        if (loginController.isMentor()) {
            questions = mentorBean.findByUsername(loginController.getRemoteUser()).getQuestion();
        } else if (loginController.isStudent()) {
            questions = studentBean.findByUsername(loginController.getRemoteUser()).getQuestion();
        } else {
            questions = new ArrayList<>();
        }
    }

    @PostConstruct
    protected void postConstruct() {
        super.postConstruct();
        question = new Question();
        question.setMentor(new Mentor());
        refreshQuestionList();

    }

    public String getStudentDisplayName(Question question) {
        List<String> stuName = new ArrayList<>();
        for (Student s : question.getStudent()) {
            stuName.add(s.getStudentName());
        }
        return String.join(",", stuName);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String doUpdate(Question question) {
        this.question = question;
        return loginController.setPortalPathByRole() + "/updateQuestion.xhtml";
    }

    public String executeUpdate() {
        LOG.info("About to update question details " + question.toString());
        questionBean.update(question);
        refreshQuestionList();
        return loginController.setPortalPathByRole() + "/welcome.xhtml";
    }

    public String doDelete(Question question) {
        LOG.info("About to delete question " + question.toString());
        questionBean.delete(question);
        refreshQuestionList();
        return loginController.setPortalPathByRole() + "/welcome.xhtml";
    }
}
