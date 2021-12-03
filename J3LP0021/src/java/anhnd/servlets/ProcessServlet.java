/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhnd
 */
public class ProcessServlet extends HttpServlet {

    private static final String HOME_PAGE = "home.jsp";
    private static final String ADD_NEW_CAKE = "AddNewCakeServlet";
    private static final String CART = "CartServlet";
    private static final String REGISTER = "RegisterServlet";
    private static final String EDIT_CAKE = "EditCakeServlet";
    private static final String GET_ORDER = "GetOrderServlet";
    private static final String LOGIN_GOOGLE = "LoginGoogleServlet";
    private static final String LOGIN = "LoginServlet";
    private static final String LOGOUT = "LogoutServlet";
    private static final String SEARCH_CAKE = "SearchCakeServlet";
    private static final String UPDATE_CAKE = "UpdateCakeServlet";

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
        PrintWriter out = response.getWriter();
        String url = HOME_PAGE;
        try {
            String button = request.getParameter("btAction");
            if (button == null) {

            } else if (button.equals("Login")) {
                url = LOGIN;
            } else if (button.equals("Logout")) {
                url = LOGOUT;
            } else if (button.equals("Register")) {
                url = REGISTER;
            } else if (button.equals("Edit Cake")) {
                url = EDIT_CAKE;
            } else if (button.equals("Update Cake")) {
                url = UPDATE_CAKE;
            } else if (button.equals("Get Order")) {
                url = GET_ORDER;
            } else if (button.equals("Add Cake")) {
                url = ADD_NEW_CAKE;
            } else if (button.equals("Search Cake")) {
                url = SEARCH_CAKE;
            } else if (button.equals("GoogleLogin")) {
                url = LOGIN_GOOGLE;
            } else if (button.equals("Remove_Cart")) {
                url = CART;
            } else if (button.equals("Guest_Order")) {
                url = CART;
            } else if (button.equals("Guest_View_Cart")) {
                url = CART;
            } else if (button.equals("Guest_Add_To_Cart")) {
                url = CART;
            } else if (button.equals("Add_To_Cart")) {
                url = CART;
            } else if (button.equals("Update_Cart")) {
                url = CART;
            } else if (button.equals("View_Cart")) {
                url = CART;
            } else if (button.equals("Confirm_Order")) {
                url = CART;
            } else if (button.equals("Order")) {
                url = CART;
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
