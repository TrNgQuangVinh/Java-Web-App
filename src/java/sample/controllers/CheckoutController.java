/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.CartDTO;
import sample.shopping.ClothesDTO;
import sample.users.UserDAO;


/**
 *
 * @author KuroZabulus
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {
    
    private static final String ERROR="checkout.jsp";
    private static final String SUCCESS="checkout.jsp";
    
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
        ArrayList<String> removalList = new ArrayList<>();
        boolean checkItem = false;
        boolean checkRemoval = false;
        try{
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            if(session!=null){
                CartDTO cart = new CartDTO();
                cart = (CartDTO) session.getAttribute("CART");
                if(cart!=null){
                    for(ClothesDTO clothes : cart.getCart().values()){
                        checkItem = dao.checkItem(clothes);
                        if(!checkItem){
                            removalList.add(clothes.getID());
                        }
                    }
                    for (int i = 0; i < removalList.size(); i++) {
                        checkRemoval = cart.remove((String) removalList.get(i));
                    } 
                    if(checkRemoval){
                        request.setAttribute("MESSAGE", "Some items have been removed because there were not enough in stock");
                    }
                    url=SUCCESS;
                }
            }
            
        }catch(Exception e){
            log("Error at CheckoutController: " + e.toString());
        }finally{
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
