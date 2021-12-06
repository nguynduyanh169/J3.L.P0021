/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.beans.BookingBean;
import anhnd.daos.BookingModel;
import anhnd.utils.CartUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anhnd
 */
public class BookingServlet extends HttpServlet {

    private static final String MEMBER_VIEW_ROOM = "member_view_room.jsp";
    private static final String MEMBER_CART = "member_cart.jsp";

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
        String action = request.getParameter("btAction");
        try {
            if (action.equals("Add to cart")) {
                HttpSession session = request.getSession(true);
                BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                if (shop == null) {
                    shop = new BookingBean();
                }
                String roomId = request.getParameter("roomId");
                String availableQuantity = request.getParameter("availableQuantity");
                String priceText = request.getParameter("price");
                String checkIn = request.getParameter("checkIn");
                String checkOut = request.getParameter("checkOut");
                String url = MEMBER_VIEW_ROOM;
                float price = Float.parseFloat(priceText);
                BookingModel bookingModel = new BookingModel(roomId, Integer.parseInt(availableQuantity), price, price * CartUtils.getDifferenceDays(Date.valueOf(checkIn), Date.valueOf(checkOut)), Date.valueOf(checkIn), Date.valueOf(checkOut));
                shop.addBooking(bookingModel);
                session.setAttribute("SHOP", shop);
                for (BookingModel value : shop.values()) {
                    System.out.println(value.toString());
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
