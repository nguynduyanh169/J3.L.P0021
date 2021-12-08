/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.BookingDAO;
import anhnd.daos.BookingDetailDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.BookingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
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
public class BookingHistoryServlet extends HttpServlet {

    private static final String BOOKING_HISTORY = "manage_booking.jsp";

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
        String url = BOOKING_HISTORY;
        try {
            if (action.equals("View Booking")) {
                BookingDAO bookingDAO = new BookingDAO();
                HttpSession session = request.getSession();
                if (session != null) {
                    AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                    List<BookingDTO> result = bookingDAO.getBookings(account.getEmail(), "", null);
                    session.setAttribute("BOOKINGHISTORY", result);
                }
            } else if (action.equals("Find booking")) {
                String hotelName = request.getParameter("txtHotelName");
                String bookingDate = request.getParameter("txtBookingDate");
                boolean isError = false;
                String error = "";
                if (bookingDate.isEmpty()) {
                    isError = true;
                    error = "Booking Date must be valid";
                }
                if (isError) {
                    request.setAttribute("ERROR", error);
                } else {
                    BookingDAO bookingDAO = new BookingDAO();
                    HttpSession session = request.getSession();
                    if (session != null) {
                        AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                        List<BookingDTO> result = bookingDAO.getBookings(account.getEmail(), hotelName, Date.valueOf(bookingDate));
                        session.setAttribute("BOOKINGHISTORY", result);
                    }
                }
            }else if(action.equals("Cancel booking")){
                String bookingId = request.getParameter("txtBookingId");
                BookingDAO bookingDAO = new BookingDAO();
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                boolean check = bookingDAO.cancelBooking(bookingId);
                if(check == true){
                    bookingDetailDAO.disableBookingDetailsByBookingId(bookingId);
                    url = "ProcessServlet?btAction=View+Booking";
                }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
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
