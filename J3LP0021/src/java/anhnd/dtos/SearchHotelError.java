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
public class SearchHotelError implements Serializable{
    private String checkInError;
    private String checkoutError;
    private String amountError;

    public String getCheckInError() {
        return checkInError;
    }

    public void setCheckInError(String checkInError) {
        this.checkInError = checkInError;
    }

    public String getCheckoutError() {
        return checkoutError;
    }

    public void setCheckoutError(String checkoutError) {
        this.checkoutError = checkoutError;
    }

    public String getAmountError() {
        return amountError;
    }

    public void setAmountError(String amountError) {
        this.amountError = amountError;
    }
    
    
}
