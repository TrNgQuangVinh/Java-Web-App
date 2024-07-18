/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KuroZabulus
 */
public class MainController extends HttpServlet {
    private static final String WELCOME = "login.jsp";
    
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    
    private static final String SEARCH="Search";
    private static final String SEARCH_CONTROLLER= "SearchController";
    
    private static final String DELETE="Delete";
    private static final String DELETE_CONTROLLER="DeleteController";
    
    private static final String LOGOUT="Logout";
    private static final String LOGOUT_CONTROLLER="LogoutController";
    
    private static final String UPDATE="Update";
    private static final String UPDATE_CONTROLLER="UpdateController";
    
    private static final String CREATE_USER_PAGE="Create_User_Page";
    private static final String CREATE_USER_PAGE_VIEW="createUser.jsp";
    
    private static final String INSERT="Insert";
    private static final String INSERT_CONTROLLER="InsertController";
    
    private static final String SHOPPING_PAGE="Shopping_Page";
    private static final String SHOPPING_PAGE_CONTROLLER="ShoppingPageController";
    
    private static final String ADD="Add";
    private static final String ADD_CONTROLLER="AddController";
    
    private static final String VIEW_CART="View_Cart";
    private static final String VIEW_CART_PAGE="viewCart.jsp";
    
    private static final String REMOVE="Remove";
    private static final String REMOVE_CONTROLLER="RemoveController";
    
    private static final String CHECKOUT="Checkout";
    private static final String CHECKOUT_CONTROLLER="CheckoutController";
    
    private static final String ORDER="Order";
    private static final String ORDER_CONTROLLER="OrderController";
    
    private static final String EDIT="Edit";
    private static final String EDIT_CONTROLLER="EditController";
    
    private static final String BOTTOM_ONE="Bottom One";
    private static final String BOTTOM_ONE_CONTROLLER="BottomOneController";
    
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
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if(LOGIN.equals(action)){ //de debug hon action.equals(LOGIN)
                url=LOGIN_CONTROLLER;
            }
            else if(SEARCH.equals(action)){
                url=SEARCH_CONTROLLER;
            }
            else if(DELETE.equals(action)){
                url=DELETE_CONTROLLER;
            }
            else if(LOGOUT.equals(action)){
                url=LOGOUT_CONTROLLER;
            }
            else if(UPDATE.equals(action)){
                url=UPDATE_CONTROLLER;
            }
            else if(CREATE_USER_PAGE.equals(action)){
                url=CREATE_USER_PAGE_VIEW;
            }
            else if(INSERT.equals(action)){
                url=INSERT_CONTROLLER;
            }
            else if(SHOPPING_PAGE.equals(action)){
                url=SHOPPING_PAGE_CONTROLLER;
            }
            else if(ADD.equals(action)){
                url=ADD_CONTROLLER;
            }
            else if(VIEW_CART.equals(action)){
                url=VIEW_CART_PAGE;
            }
            else if(REMOVE.equals(action)){
                url=REMOVE_CONTROLLER;
            }
            else if(CHECKOUT.equals(action)){
                url=CHECKOUT_CONTROLLER;
            }
            else if(ORDER.equals(action)){
                url=ORDER_CONTROLLER;
            }
            else if(EDIT.equals(action)){
                url=EDIT_CONTROLLER;
            }
            else if(BOTTOM_ONE.equals(action)){
                url=BOTTOM_ONE_CONTROLLER;
            }
            
        } catch (Exception e) {
            log("Error at MainController: "+e.toString());
        } finally {
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
