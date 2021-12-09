/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlets;

import anhnd.beans.BookingBean;
import anhnd.daos.BookingDAO;
import anhnd.daos.BookingDetailDAO;
import anhnd.daos.DiscountDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.BookingDTO;
import anhnd.dtos.BookingDetailDTO;
import anhnd.dtos.BookingModel;
import anhnd.utils.CartUtils;
import anhnd.utils.TextUtils;
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
import org.apache.log4j.Logger;

/**
 *
 * @author anhnd
 */
public class BookingServlet extends HttpServlet {

    private static final String MEMBER_VIEW_ROOM = "member_view_room.jsp";
    private static final String VIEW_CART = "view_cart.jsp";
    private static final String DISCOUNT = "enter_discount.jsp";
    private static final String VIEW_BOOKING = "manage_booking.jsp";
    private static Logger LOG = Logger.getLogger(BookingServlet.class.getName());

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
                String hotelName = request.getParameter("hotelName");
                String roomTypeName = request.getParameter("roomTypeName");
                String description = request.getParameter("description");
                String url = MEMBER_VIEW_ROOM;
                float price = Float.parseFloat(priceText);
                BookingModel bookingModel = new BookingModel(hotelName, roomId, description, roomTypeName, Integer.parseInt(availableQuantity), price, price * CartUtils.getDifferenceDays(Date.valueOf(checkIn), Date.valueOf(checkOut)), Date.valueOf(checkIn), Date.valueOf(checkOut));
                shop.addBooking(bookingModel);
                session.setAttribute("BOOKEDHOTEL", hotelName);
                session.setAttribute("SHOP", shop);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("View cart")) {
                float totalPrice = 0;
                HttpSession session = request.getSession();
                if (session != null) {
                    BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                    if (shop != null) {
                        totalPrice = shop.caculateTotalPrice();
                    }
                }
                request.setAttribute("TOTALPRICE", totalPrice);
                String url = VIEW_CART;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Remove cart")) {
                String roomId = request.getParameter("roomId");
                String url = "ProcessServlet?btAction=View+cart";
                if (roomId != null) {
                    HttpSession session = request.getSession();
                    if (session != null) {
                        BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                        if (shop != null) {
                            shop.removeBooking(roomId);
                            session.setAttribute("SHOP", shop);
                        }
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Update cart")) {
                String roomId = request.getParameter("roomId");
                String newQuantity = request.getParameter("txtQuantity");
                String url = "ProcessServlet?btAction=View+cart";
                HttpSession session = request.getSession();
                if (session != null) {
                    BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                    if (shop != null) {
                        shop.updateBooking(roomId, Integer.parseInt(newQuantity));
                        session.setAttribute("SHOP", shop);
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Skip discount")) {
                HttpSession session = request.getSession();
                BookingDAO bookingDAO = new BookingDAO();
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                if (session != null) {
                    AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                    BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                    String checkIn = request.getParameter("checkIn");
                    String checkOut = request.getParameter("checkOut");
                    String hotelName = (String) session.getAttribute("BOOKEDHOTEL");
                    if (shop != null) {
                        BookingDTO bookingDTO = new BookingDTO(TextUtils.getUUID(),hotelName, account.getEmail(), Date.valueOf(checkIn), Date.valueOf(checkOut), null, 0, shop.caculateTotalPrice(), shop.caculateTotalPrice(), 0);
                        boolean check = bookingDAO.insertBookingDetail(bookingDTO);
                        if (check == true) {
                            for (BookingModel bookingModel : shop.values()) {
                                BookingDetailDTO bookingDetailDTO = new BookingDetailDTO(TextUtils.getUUID(), bookingDTO.getBookingId(), bookingModel.getRoomId(), bookingModel.getBookingPrice(), bookingModel.getRoomQuantity(), bookingModel.getCheckIn(), bookingModel.getCheckOut(), 0);
                                bookingDetailDAO.insertBookingDetail(bookingDetailDTO);
                            }

                        }
                    }
                    session.removeAttribute("SHOP");
                }
                RequestDispatcher rd = request.getRequestDispatcher(VIEW_BOOKING);
                rd.forward(request, response);
            } else if (action.equals("Discount")) {
                HttpSession session = request.getSession();
                BookingDAO bookingDAO = new BookingDAO();
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                DiscountDAO discountDAO = new DiscountDAO();
                boolean checkDiscount = true;
                String url = VIEW_BOOKING;
                String error = "";
                if (session != null) {
                    AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                    BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                    String checkIn = request.getParameter("checkIn");
                    String checkOut = request.getParameter("checkOut");
                    String discountId = request.getParameter("txtCode");
                    String hotelName = (String) session.getAttribute("BOOKEDHOTEL");
                    int discountPercent = discountDAO.checkDiscount(discountId, account.getEmail());
                    if (discountPercent == 0) {
                        checkDiscount = false;
                        error = "Invalid discount code!";
                    }
                    if (checkDiscount == false) {
                        request.setAttribute("ERROR", error);
                        url = DISCOUNT;
                    } else {
                        if (shop != null) {
                            float discount = (float) discountPercent / 100;
                            BookingDTO bookingDTO = new BookingDTO(TextUtils.getUUID(),hotelName, account.getEmail(), Date.valueOf(checkIn), Date.valueOf(checkOut), null, discountPercent, shop.caculateTotalPrice(), shop.caculateTotalPrice() - (shop.caculateTotalPrice() * discount), 0);
                            boolean check = bookingDAO.insertBookingDetail(bookingDTO);
                            if (check == true) {
                                for (BookingModel bookingModel : shop.values()) {
                                    BookingDetailDTO bookingDetailDTO = new BookingDetailDTO(TextUtils.getUUID(), bookingDTO.getBookingId(), bookingModel.getRoomId(), bookingModel.getBookingPrice(), bookingModel.getRoomQuantity(), bookingModel.getCheckIn(), bookingModel.getCheckOut(), 0);
                                    bookingDetailDAO.insertBookingDetail(bookingDetailDTO);
                                }
                                discountDAO.usedDiscount(discountId);
                            }
                        }
                        session.removeAttribute("SHOP");
                        session.removeAttribute("BOOKEDHOTEL");
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (action.equals("Book")) {
                HttpSession session = request.getSession();
                List<String> errors = null;
                String url = DISCOUNT;
                boolean isError = true;
                float totalPrice = 0;
                if (session != null) {
                    BookingBean shop = (BookingBean) session.getAttribute("SHOP");
                    if (shop != null) {
                        totalPrice = shop.caculateTotalPrice();
                        errors = new ArrayList<String>();
                        for (BookingModel item : shop.values()) {
                            if (item.getRoomAvailableQuantity() < item.getRoomQuantity()) {
                                isError = false;
                                errors.add("The room " + item.getDescription() + " is not enought");
                            }
                        }
                    }
                    if (isError == false) {
                        url = VIEW_CART;
                        request.setAttribute("ERROR", errors);
                        request.setAttribute("TOTALPRICE", totalPrice);
                    }
                }

                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            LOG.error("BookingServlet_Exception: " + e.getMessage());
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
