<%-- 
    Document   : member_home
    Created on : Dec 6, 2021, 7:54:43 AM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Home Page</title>
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
        <c:set var="hotels" value="${sessionScope.HOTELS}" />
        <c:set var="areas" value="${sessionScope.AREAS}"/>
        <c:set  var="account" value="${sessionScope.ACCOUNT}"/>
        <form action="ProcessServlet" method="POST">
            <input type="submit" value="Logout" name="btAction"/>
        </form>
        <div class="container">
            <h1>Welcome ${account.fullName}!</h1>
            <br/>
            <c:url var="viewBookingHistoryLink" value="ProcessServlet">
                <c:param name="btAction" value="View Booking"/>
            </c:url>
            <a href="${viewBookingHistoryLink}">View Booking</a>
            <br/>
            <form action="ProcessServlet" method="GET">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Hotel Name: </td>
                            <td><input type="hidden" name="forwardTo" value="member"/><input type="text" name="txtSearchName" value="${param.txtSearchName}"/></td>
                        </tr>
                        <tr>
                            <td>Area: </td>
                            <td><select name="areaChoice">
                                    <c:forEach var="area" items="${areas}">
                                        <option value="${area.areaId}">
                                            <c:out value="${area.areaName}"/>
                                        </option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Check in: </td>
                            <td><input type="date" name="txtCheckIn" value="${param.txtCheckIn}"/></td>
                        </tr>
                        <tr>
                            <td>Check out: </td>
                            <td><input type="date" name="txtCheckOut" value="${param.txtCheckOut}"/></td>
                        </tr>
                        <tr>
                            <td>Amount: </td>
                            <td><input type="number" name="txtAmount" value="${param.txtAmount}"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="btAction" value="Search Hotel"/></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <br/>
            <c:if test="${not empty hotels}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Hotel Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach var="dto" items="${hotels}" varStatus="counter">
                        <tbody>
                            <tr>
                                <td>${dto.hotelName}</td>
                                <td>${dto.address}</td>
                                <td>${dto.phone}</td>
                                <td>
                                    <c:url var="viewRoomsLink" value="ProcessServlet">
                                        <c:param name="hotelId" value="${dto.hotelId}"/>
                                        <c:param name="hotelName" value="${dto.hotelName}"/>
                                        <c:param name="selectQuantity" value="${param.txtAmount}"/>
                                        <c:param name="checkIn" value="${param.txtCheckIn}"/>
                                        <c:param name="checkOut" value="${param.txtCheckOut}"/>
                                        <c:param name="forwardTo" value="member"/>
                                        <c:param name="btAction" value="Get Rooms"/>
                                    </c:url>
                                    <a href="${viewRoomsLink}">View Rooms</a>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty hotels}">
                <h1>No record is matched !!!</h1>
            </c:if>
        </div>
    </div>
</body>
</html>
