/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.utils;

import java.sql.Date;
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
}
