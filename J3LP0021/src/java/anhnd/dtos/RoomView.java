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
public class RoomView implements Serializable{
    private HotelRoomDTO hotelRoomDTO;
    private int actualQuantity;
    private RoomTypeDTO roomTypeDTO;
    
    public RoomView(HotelRoomDTO hotelRoomDTO, int actualQuantity, RoomTypeDTO roomTypeDTO) {
        this.hotelRoomDTO = hotelRoomDTO;
        this.actualQuantity = actualQuantity;
        this.roomTypeDTO = roomTypeDTO;
}

    
    
    public HotelRoomDTO getHotelRoomDTO() {
        return hotelRoomDTO;
    }

    public void setHotelRoomDTO(HotelRoomDTO hotelRoomDTO) {
        this.hotelRoomDTO = hotelRoomDTO;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public RoomTypeDTO getRoomTypeDTO() {
        return roomTypeDTO;
    }

    public void setRoomTypeDTO(RoomTypeDTO roomTypeDTO) {
        this.roomTypeDTO = roomTypeDTO;
    }
    
    
}
