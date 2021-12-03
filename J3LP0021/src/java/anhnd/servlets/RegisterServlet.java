package anhnd.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import anhnd.daos.AccountDAO;
import anhnd.dtos.AccountDTO;
import anhnd.dtos.AccountError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhnd
 */
public class RegisterServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.html";
    private static final String REGISTER_PAGE = "register.jsp";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX
            = Pattern.compile("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", Pattern.CASE_INSENSITIVE);

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
        String url = REGISTER_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String address = request.getParameter("txtAddress");
        String fullName = request.getParameter("txtFullname");
        String phone = request.getParameter("txtPhone");
        AccountError error = new AccountError();
        boolean validated = true;
        try {
            if (VALID_EMAIL_ADDRESS_REGEX.matcher(email).find() == false) {
                validated = false;
                error.setEmailError("Email must be valid");
            }
            if (password.length() < 7 || password.length() > 20) {
                validated = false;
                error.setPasswordError("Password in range 7-20");
            }
            if (address.isEmpty()) {
                validated = false;
                error.setAddressError("Address cannot be blank");
            }
            if (fullName.isEmpty()) {
                validated = false;
                error.setFullNameError("Fullname cannot be blank");
            }
            if (VALID_PHONE_REGEX.matcher(phone).find() == false) {
                validated = false;
                error.setPhoneError("Phone must be valid");
            }
            if (validated == false) {
                request.setAttribute("VALIDATE", error);
            } else {
                AccountDAO accountDAO = new AccountDAO();
                AccountDTO accountDTO = new AccountDTO(email, fullName, phone, address, 0);
                accountDTO.setPassword(password);
                boolean check = accountDAO.insertAccount(accountDTO);
                if (check == true) {
                    url = LOGIN_PAGE;
                } else {
                    request.setAttribute("ERROR", "Cannot register!");
                    url = REGISTER_PAGE;
                }
            }

        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute("ERROR", email + " is existed!");
                url = REGISTER_PAGE;
            }
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
