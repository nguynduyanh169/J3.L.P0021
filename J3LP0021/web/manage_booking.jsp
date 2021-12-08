<%-- 
    Document   : manage_booking
    Created on : Dec 7, 2021, 7:44:06 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History Page</title>
        <style>
            .container {
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <c:set var="bookingHistory" value="${sessionScope.BOOKINGHISTORY}"/>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <div class="container">
            <h1>Manage your booking</h1>
            <form action="ProcessServlet" method="POST">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Hotel Name: </td>
                            <td><input type="text" name="txtHotelName" value="${param.txtHotelName}"/></td>
                        </tr>
                        <tr>
                            <td>Booking Date: </td>
                            <td><input type="date" name="txtBookingDate" value="${param.txtBookingDate}"/></td>
                            <td><font color="red">
                                ${error}
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="btAction" value="Find booking"/></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <c:if test="${not empty bookingHistory}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Hotel name</th>
                            <th>Booking by</th>
                            <th>Check in</th>
                            <th>Check out</th>
                            <th>Create Date</th>
                            <th>Discount Percent(%)</th>
                            <th>Total Price</th>
                            <th>Have to Pay</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${bookingHistory}" varStatus="counter">
                            <tr>
                                <td>${item.hotelName}</td>
                                <td>${item.bookingBy}</td>
                                <td>${item.startDate}</td>
                                <td>${item.endDate}</td>
                                <td>${item.createDate}</td>
                                <td>${item.discount}</td>
                                <td>${item.realTotalPrice}</td>
                                <td>${item.totalPrice}</td>
                                <td>
                                    <form action="ProcessServlet" method="POST">
                                        <input type="hidden" name="txtBookingId" value="${item.bookingId}"/>
                                        <input type="submit" name="btAction" value="Cancel booking"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty bookingHistory}">
                <h1>No record is matched !!!</h1>
            </c:if>

        </div>
    </body>
</html>
