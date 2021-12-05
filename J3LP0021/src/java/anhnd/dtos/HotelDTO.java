/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

import java.io.Serializable;

/**
 *
 * @author anhnd
 */
public class HotelDTO implements Serializable {

    public HotelDTO(String hotelId, String hotelName, String phone, String address, String area, int status) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.status = status;
    }
    private String hotelId;
    private String hotelName;
    private String phone;
    private String address;
    private String area;
    private int status;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HotelDTO{" + "hotelId=" + hotelId + ", hotelName=" + hotelName + ", phone=" + phone + ", address=" + address + ", area=" + area + ", status=" + status + '}';
    }
    
    

}
