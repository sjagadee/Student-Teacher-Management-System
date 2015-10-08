/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Srinivas
 */
@Named
@RequestScoped
public class LoginController extends AbstractController {

    @NotNull(message = "Please Enter Password")
    private String password;
    @NotNull(message = "Please Enter User Name")
    private String username;

    @PostConstruct
    protected void postConstruct() {
        super.postConstruct();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginController() {
    }
    
    public String getRemoteUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }

    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }

    public boolean isMentor() {
        return facesContext.getExternalContext().isUserInRole("ment");
    }

    public boolean isStudent() {
        return facesContext.getExternalContext().isUserInRole("stu");
    }

    public String setPortalPathByRole() {
        if (isAdmin()) {
            return "/admin";
        } else if (isStudent()) {
            return "/studentPortal";
        } else if (isMentor()) {
            return "/mentorPortal";
        } else {
            return "/";
        }
    }

    public String doLogin() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.login(username, password);
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: you made a bad Login"));
            return "/login.xhtml";
        }
        return setPortalPathByRole() + "/welcome.xhtml";

    }

    public String doLogout() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout", "Detail: bad Logout"));
            return "/error.xhtml";
        }
        return "/login.xhtml";
    }

}
