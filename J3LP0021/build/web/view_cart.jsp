<%-- 
    Document   : view_cart.jsp
    Created on : Dec 6, 2021, 6:48:04 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <script>
            function confirmDelete(button) {
                var href = button.getAttribute("href");
                console.log(href);
                var result = confirm("Delete this room out of your cart?");
                if (result) {
                    window.location.href = href;
                } else {
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <c:set var="shop" value="${sessionScope.SHOP}" />
        <c:set var="checkIn" value="${sessionScope.CHECKIN}" />
        <c:set var="checkOut" value="${sessionScope.CHECKOUT}" />
        <c:set var="totalPrice" value="${requestScope.TOTALPRICE}" />
        <c:set var="error" value="${requestScope.ERROR}" />
        <h1>View Room Cart for ${checkIn} - ${checkOut}</h1>
        <c:if test="${not empty shop}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Hotel</th>
                        <th>Room Type</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Booking quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="rows" items="${shop}">
                        <tr>
                            <td>${rows.value.hotelName}</td>
                            <td>${rows.value.roomTypeName}</td>
                            <td>${rows.value.description}</td>
                            <td>${rows.value.bookingPrice}</td>
                            <td>
                                <form action="ProcessServlet" method="GET">
                                    <input type="hidden" name="roomId" value="${rows.value.roomId}"/>
                                    <input type="number" name="txtQuantity" min="1" required value="${rows.value.roomQuantity}">
                                    <input type="submit" value="Update cart" name="btAction"/>
                                    <button onclick="return confirmDelete(this)" name="btAction" value="Remove cart" >Remove</button></form>
                            </td>
                        </tr>
                      
                    </c:forEach>
                        <tr>
                        <td colspan="4"></td>
                        <td><form action="ProcessServlet" method="POST">
                                <input type="hidden" value="${checkIn}" name="checkIn"/>
                                <input type="hidden" value="${checkOut}" name="checkOut"/>
                                <input type="submit" value="Book" name="btAction"/>
                          </form>
                        </td>
                    </tr>
                     <tr>
                        <td colspan="4">Total Price: </td>
                        <td>
                          ${totalPrice}
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty shop}">
            <h1>No Item !!!</h1>
        </c:if>
            <c:if test="${not empty error}">
                <c:forEach var="errorMsg" items="${error}">
                    <p><font color="red">
                                ${errorMsg}
                                </font></p><br/>
                </c:forEach>
            </c:if>
        
    </body>
</html>
