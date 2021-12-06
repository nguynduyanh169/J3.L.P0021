/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import java.sql.Date;

/**
 *
 * @author anhnd
 */
public class BookingModel {
    private String roomId;
    private int roomAvailableQuantity;
    private int roomQuantity;
    private float price;
    private float bookingPrice;
    private Date checkIn;
    private Date checkOut;

    public BookingModel(String roomId, int roomAvailableQuantity, float price, float bookingPrice, Date checkIn, Date checkOut) {
        this.roomId = roomId;
        this.roomAvailableQuantity = roomAvailableQuantity;
        this.roomQuantity = 1;
        this.price = price;
        this.bookingPrice = bookingPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRoomAvailableQuantity() {
        return roomAvailableQuantity;
    }

    public void setRoomAvailableQuantity(int roomAvailableQuantity) {
        this.roomAvailableQuantity = roomAvailableQuantity;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(float bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "BookingModel{" + "roomId=" + roomId + ", roomAvailableQuantity=" + roomAvailableQuantity + ", roomQuantity=" + roomQuantity + ", price=" + price + ", bookingPrice=" + bookingPrice + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }
    
    
    
}
