/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author KuroZabulus
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR="SearchController";
    private static final String SUCCESS="SearchController";
    private static final String SPECIAL="login.jsp";
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
        String url=ERROR;
        try {
            boolean check = false;
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            String userID = request.getParameter("userID");
            String roleID = request.getParameter("roleID");
            String fullName = request.getParameter("fullName");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            UserDTO user = new UserDTO(userID, fullName, roleID, "");
            if(loginUser!=null){
                if(userID.equals(loginUser.getUserID())){
                    //UserDTO user = new UserDTO(userID, fullName, loginUser.getRoleID(), "");
                    check = dao.update(user);
                    session.setAttribute("LOGIN_USER", user);
                    request.setAttribute("ERROR", "Your info has been changed, please login again!");
                    url = SPECIAL;
                }
                else {
                    //UserDTO user = new UserDTO(userID, fullName, roleID, "");
                    check = dao.update(user);
                    if(check) url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at UpdateController: "+e.toString());
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
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
