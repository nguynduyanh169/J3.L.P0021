<%-- 
    Document   : guest_view_room
    Created on : Dec 5, 2021, 9:50:19 AM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Rooms Page</title>
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
        <c:set var="hotelRooms" value="${sessionScope.HOTELROOMS}" />
        <c:set var="hotel" value="${sessionScope.HOTEL}" />
        <c:url var="guestBackHomeLink" value="ProcessServlet">
            <c:param name="btAction" value="Guest Back Home"/>
        </c:url>
        <a href="${guestBackHomeLink}">Back To Home</a>
        <div class="container">
            <h1>View Available Rooms of <c:out value="${hotel.hotelName}"/>!</h1>
            <br/>
            <table border="0">
                <tbody>
                    <tr>
                        <td>Name: </td>
                        <td>${hotel.hotelName}</td>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td>${hotel.address}</td>
                    </tr>
                    <tr>
                        <td>Phone: </td>
                        <td>${hotel.phone}</td>
                    </tr>
                </tbody>
            </table>

            <br/>
            <c:if test="${not empty hotelRooms}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Room Type</th>
                            <th>Description</th>
                            <th>Total Quantity</th>
                            <th>Available Quantity</th>
                            <th>Price per day</th>
                        </tr>
                    </thead>
                    <c:forEach var="room" items="${hotelRooms}" varStatus="counter">
                        <tbody>
                            <tr> <td>${room.roomTypeDTO.roomTypeName}</td>
                                <td>${room.hotelRoomDTO.description}</td>
                                <td>${room.hotelRoomDTO.quantity}</td>
                                <td>${room.actualQuantity}</td>
                                <td>${room.hotelRoomDTO.currentPrice}</td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty hotelRooms}">
                <h1>No record is matched !!!</h1>
            </c:if>
        </div>


    </body>
</html>
