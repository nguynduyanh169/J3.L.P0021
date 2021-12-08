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
public class BookingDetailView implements Serializable{
    private String bookingId;
    private String hotelName;
    private BookingDetailDTO bookingDetailDTO;
    private HotelRoomDTO hotelRoomDTO;

    public BookingDetailView(String bookingId, String hotelName, BookingDetailDTO bookingDetailDTO, HotelRoomDTO hotelRoomDTO) {
        this.bookingId = bookingId;
        this.hotelName = hotelName;
        this.bookingDetailDTO = bookingDetailDTO;
        this.hotelRoomDTO = hotelRoomDTO;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public BookingDetailDTO getBookingDetailDTO() {
        return bookingDetailDTO;
    }

    public void setBookingDetailDTO(BookingDetailDTO bookingDetailDTO) {
        this.bookingDetailDTO = bookingDetailDTO;
    }

    public HotelRoomDTO getHotelRoomDTO() {
        return hotelRoomDTO;
    }

    public void setHotelRoomDTO(HotelRoomDTO hotelRoomDTO) {
        this.hotelRoomDTO = hotelRoomDTO;
    }
    
    
}
