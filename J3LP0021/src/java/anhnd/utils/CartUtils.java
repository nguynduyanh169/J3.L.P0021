/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.utils;

import anhnd.beans.BookingBean;
import anhnd.dtos.BookingModel;
import java.sql.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author anhnd
 */
public class CartUtils {

    public static long getDifferenceDays(Date fromDate, Date toDate) {
        long diff = toDate.getTime() - fromDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    public static float caculateTotalPrice(HashMap<String, BookingModel> map) {
        float totalPrice = 0;
        for (BookingModel item : map.values()) {
            totalPrice = totalPrice + item.getBookingPrice();
        }
        return totalPrice;

    }
}
