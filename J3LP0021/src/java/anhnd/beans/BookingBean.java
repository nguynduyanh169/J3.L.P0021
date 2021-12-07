/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.beans;

import anhnd.dtos.BookingModel;
import anhnd.utils.CartUtils;
import java.util.HashMap;

/**
 *
 * @author anhnd
 */
public class BookingBean extends HashMap<String, BookingModel> {

    public BookingBean() {
        super();
    }

    public void addBooking(BookingModel bookingModel) {
        String key = bookingModel.getRoomId();
        if (this.containsKey(key)) {
            int oldQuantity = ((BookingModel) this.get(key)).getRoomQuantity();
            ((BookingModel) this.get(key)).setRoomQuantity(oldQuantity + 1);
            int updatedQuanity = ((BookingModel) this.get(key)).getRoomQuantity();
            ((BookingModel) this.get(key)).setBookingPrice(bookingModel.getPrice() * CartUtils.getDifferenceDays(bookingModel.getCheckIn(), bookingModel.getCheckOut()) * updatedQuanity);

        } else {
            this.put(bookingModel.getRoomId(), bookingModel);
        }
    }

    public boolean removeBooking(String roomId) {
        if (this.containsKey(roomId)) {
            this.remove(roomId);
            return true;
        }
        return false;
    }

    public boolean updateBooking(String id, int newQuantity) {
        if (this.containsKey(id)) {
            ((BookingModel) this.get(id)).setRoomQuantity(newQuantity);
            BookingModel editBookingModel = this.get(id);
            ((BookingModel) this.get(id)).setBookingPrice(editBookingModel.getPrice() * CartUtils.getDifferenceDays(editBookingModel.getCheckIn(), editBookingModel.getCheckOut()) * editBookingModel.getRoomQuantity());
            return true;
        }
        return false;
    }
    
    public float caculateTotalPrice(){
        return CartUtils.caculateTotalPrice(this);
    }

}
