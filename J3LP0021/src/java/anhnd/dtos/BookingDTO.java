/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author anhnd
 */
public class BookingDTO implements Serializable {

    private String bookingId;
    private String bookingBy;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private int discount;
    private float realTotalPrice;
    private float totalPrice;
    private int status;

    public BookingDTO(String bookingId, String bookingBy, Date startDate, Date endDate, Date createDate, int discount, float realTotalPrice, float totalPrice, int status) {
        this.bookingId = bookingId;
        this.bookingBy = bookingBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.discount = discount;
        this.realTotalPrice = realTotalPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingBy() {
        return bookingBy;
    }

    public void setBookingBy(String bookingBy) {
        this.bookingBy = bookingBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getRealTotalPrice() {
        return realTotalPrice;
    }

    public void setRealTotalPrice(float realTotalPrice) {
        this.realTotalPrice = realTotalPrice;
    }

}
