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
    private static final String LOAD_HOME = "LoadHomeServlet";
    private static final String BOOKING = "BookingServlet";
    private static final String BOOKING_HISTORY = "BookingHistoryServlet";
    private static final String REGISTER = "RegisterServlet";
    private static final String GET_ROOMS = "GetRoomsServlet";
    private static final String LOGIN = "LoginServlet";
    private static final String LOGOUT = "LogoutServlet";
    private static final String SEARCH_HOTEL = "SearchHotelServlet";
    private static final String BACK_TO_HOME = "BackToHomeServlet";

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
            } else if (button.equals("Guest Back Home")) {
                url = BACK_TO_HOME;
            } else if (button.equals("Member Back Home")) {
                url = BACK_TO_HOME;
            } else if (button.equals("Cart Back View Room")) {
                url = BACK_TO_HOME;
            } else if (button.equals("View Booking Back Home")) {
                url = BACK_TO_HOME;
            } else if (button.equals("Get Rooms")) {
                url = GET_ROOMS;
            } else if (button.equals("LoadHome")) {
                url = LOAD_HOME;
            } else if (button.equals("Search Hotel")) {
                url = SEARCH_HOTEL;
            } else if (button.equals("Add to cart")) {
                url = BOOKING;
            } else if (button.equals("View cart")) {
                url = BOOKING;
            } else if (button.equals("Remove cart")) {
                url = BOOKING;
            } else if (button.equals("Update cart")) {
                url = BOOKING;
            } else if (button.equals("Book")) {
                url = BOOKING;
            } else if (button.equals("Skip discount")) {
                url = BOOKING;
            } else if (button.equals("Discount")) {
                url = BOOKING;
            } else if (button.equals("View Booking")) {
                url = BOOKING_HISTORY;
            } else if (button.equals("Find booking")) {
                url = BOOKING_HISTORY;
            } else if (button.equals("Cancel booking")) {
                url = BOOKING_HISTORY;
            } else if (button.equals("View booking detail")) {
                url = BOOKING_HISTORY;
            } else if (button.equals("Back To Manage Booking")) {
                url = BACK_TO_HOME;
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
