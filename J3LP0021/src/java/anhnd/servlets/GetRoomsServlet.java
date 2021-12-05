package anhnd.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import anhnd.daos.BookingDetailDAO;
import anhnd.daos.HotelDAO;
import anhnd.daos.HotelRoomDAO;
import anhnd.daos.RoomTypeDAO;
import anhnd.dtos.HotelDTO;
import anhnd.dtos.HotelRoomDTO;
import anhnd.dtos.RoomTypeDTO;
import anhnd.dtos.RoomView;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
public class GetRoomsServlet extends HttpServlet {

    private static final String VIEW_ROOM_PAGE = "guest_view_room.jsp";

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
        String hotelId = request.getParameter("hotelId");
        String selectQuantity = request.getParameter("selectQuantity");
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        try {
            HttpSession session = request.getSession();
            HotelDAO hotelDAO = new HotelDAO();
            RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
            HotelRoomDAO hotelRoomDAO = new HotelRoomDAO();
            BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
            List<HotelRoomDTO> roomsByHotel = hotelRoomDAO.getHotelRooms(hotelId, Integer.parseInt(selectQuantity));
            List<RoomView> availableRooms = new ArrayList<>();
            if (!roomsByHotel.isEmpty()) {              
                for (HotelRoomDTO hotelRoomDTO : roomsByHotel) {
                    int actualQuantity = bookingDetailDAO.getQuantityOfBookedRoom(hotelRoomDTO.getHotelRoomId(), Date.valueOf(checkIn), Date.valueOf(checkOut), hotelRoomDTO.getQuantity());
                    if (actualQuantity > Integer.parseInt(selectQuantity)) {
                        RoomTypeDTO roomTypeDTO = roomTypeDAO.getRoomTypeById(hotelRoomDTO.getRoomTypeId());
                        RoomView roomView = new RoomView(hotelRoomDTO, actualQuantity, roomTypeDTO);
                        availableRooms.add(roomView);
                    }
                }
            }
            HotelDTO selectedHotel = hotelDAO.GetHotelById(hotelId);
            session.setAttribute("HOTEL", selectedHotel);
            session.setAttribute("HOTELROOMS", availableRooms);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_ROOM_PAGE);
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
