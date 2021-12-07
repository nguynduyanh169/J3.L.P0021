<%-- 
    Document   : enter_discount
    Created on : Dec 7, 2021, 7:19:19 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Discount Page</title>
    </head>
    <body>
        <c:set var="checkIn" value="${sessionScope.CHECKIN}" />
        <c:set var="checkOut" value="${sessionScope.CHECKOUT}" />
        <c:set var="error" value="${requestScope.ERROR}"/>
        <h1>Enter Discount Code!</h1>
        <form action="ProcessServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Discount Code: </td>
                        <td><input type="text" name="txtCode" value="${param.txtCode}"/> <input type="hidden" name="checkIn" value="${checkIn}"/> <input type="hidden" name="checkOut" value="${checkOut}"/></td>
                            <c:if test="${not empty error}">
                            <td><font color="red">
                                ${error}
                                </font>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btAction" value="Discount"/></td>
                        <td><input type="submit" name="btAction" value="Skip discount"/></td>
                    </tr>
                </tbody>
            </table>
        </form>

    </body>
</html>
