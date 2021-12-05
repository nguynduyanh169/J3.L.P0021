/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.dtos;

/**
 *
 * @author anhnd
 */
public class HotelRoomDTO {
    private String hotelRoomId;
    private String hotelId;
    private String roomTypeId;
    private String description;
    private int quantity;
    private float currentPrice;

    public HotelRoomDTO(String hotelRoomId, String hotelId, String roomTypeId, String description, int quantity, float currentPrice) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.description = description;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public String getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(String hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "HotelRoomDTO{" + "hotelRoomId=" + hotelRoomId + ", hotelId=" + hotelId + ", roomTypeId=" + roomTypeId + ", description=" + description + ", quantity=" + quantity + ", currentPrice=" + currentPrice + '}';
    }
    
    
    
}
