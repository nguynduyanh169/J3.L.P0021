/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.daos.BookingDetailDAO;
import anhnd.daos.HotelDAO;
import anhnd.daos.HotelRoomDAO;
import anhnd.dtos.HotelDTO;
import anhnd.dtos.HotelRoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class SearchHotelServlet extends HttpServlet {

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
        String searchName = request.getParameter("txtSearchName");
        String checkIn = request.getParameter("txtCheckIn");
        String checkOut = request.getParameter("txtCheckOut");
        String area = request.getParameter("areaChoice");
        String selectQuantity = request.getParameter("txtAmount");
        try {
            HttpSession session = request.getSession();
            HotelDAO hotelDAO = new HotelDAO();
            HotelRoomDAO hotelRoomDAO = new HotelRoomDAO();
            BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
            Set<HotelDTO> availableHotels = new HashSet<>();
            List<HotelDTO> hotels = hotelDAO.getHotels(searchName, area);
            for (HotelDTO hotel : hotels) {
                List<HotelRoomDTO> roomsByHotel = hotelRoomDAO.getHotelRooms(hotel.getHotelId(), Integer.parseInt(selectQuantity));
                if (!roomsByHotel.isEmpty()) {
                    for (HotelRoomDTO hotelRoomDTO : roomsByHotel) {
                        System.out.println(hotelRoomDTO.toString());
                        int actualQuantity = bookingDetailDAO.getQuantityOfBookedRoom(hotelRoomDTO.getHotelRoomId(), Date.valueOf(checkIn), Date.valueOf(checkOut), hotelRoomDTO.getQuantity());
                        if (actualQuantity > Integer.parseInt(selectQuantity)) {
                            availableHotels.add(hotel);
                        }
                    }
                }
            }
            session.setAttribute("HOTELS", availableHotels);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
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
