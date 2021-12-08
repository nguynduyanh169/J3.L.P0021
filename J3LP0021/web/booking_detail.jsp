<%-- 
    Document   : booking_detail
    Created on : Dec 8, 2021, 8:42:13 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Detail Page</title>
    </head>
    <body>
        <c:set var="bookingId" value="${requestScope.BOOKINGID}" />
        <c:set var="detailBook" value="${requestScope.DETAILBOOK}" />
        
        <h1>Details of booking ${bookingId}!</h1>
        <br/>
         <c:url var="backToManageLink" value="ProcessServlet">
            <c:param name="btAction" value="Back To Manage Booking"/>
        </c:url>
        <a href="${backToManageLink}">Back To Manage</a>
        <br/>
        <c:if test="${not empty detailBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Hotel Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <c:forEach var="item" items="${detailBook}" varStatus="counter">
                    <tbody>
                        <tr> <td>${item.hotelName}</td>
                            <td>${item.hotelRoomDTO.description}</td>
                            <td>${item.bookingDetailDTO.quantity}</td>
                            <td>${item.bookingDetailDTO.price}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty detailBook}">
            <h1>No record is matched !!!</h1>
        </c:if>
    </body>
</html>
