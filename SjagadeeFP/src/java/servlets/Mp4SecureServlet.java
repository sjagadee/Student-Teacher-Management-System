/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Mentor;
import domain.Question;
import domain.Student;
import ejb.MentorBean;
import ejb.StudentBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Srinivas
 */
@WebServlet(name = "Mp4SecureServlet", urlPatterns = {"/app/Mp4SecureServlet"})
public class Mp4SecureServlet extends HttpServlet {
    @EJB
    private MentorBean mentorBean;
    @EJB
    private StudentBean studentBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Mp4SecureServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Mp4SecureServlet at " + request.getContextPath() + "</h1>");
            
            if (request.isUserInRole("stu")) {
                Student s = studentBean.findByUsername(request.getRemoteUser());
                out.println("<h2>Hello Student " + s.getStudentName());

                if (!s.getQuestion().isEmpty()) {
                    out.println("The answer to the question are:");
                    out.println("<ul>");
                    for (Question q : s.getQuestion()) {
                        out.println("<li>" + q.getQuestionAnswer());
                    }
                    out.println("</ul>");
                }
            }

            if (request.isUserInRole("ment")) {
                Mentor m = mentorBean.findByUsername(request.getRemoteUser());
                out.println("<h2>Hello Mentor " + m.getMentorName());

                if (!m.getQuestion().isEmpty()) {
                    out.println("The answer you have written are:");
                    out.println("<ul>");
                    for (Question q : m.getQuestion()) {
                        out.println("<li>" + q.getQuestionAnswer());
                    }
                    out.println("</ul>");
                }
            }
            
            out.println("<a href=\" " + request.getContextPath() + "/logout\">Logout </a>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
