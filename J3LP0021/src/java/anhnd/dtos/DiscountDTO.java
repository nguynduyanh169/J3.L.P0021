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
public class DiscountDTO implements Serializable{
    private String discountId;
    private int discountPercent;
    private String discountFor;
    private Date expirationDate;
    private int status;

    public DiscountDTO(String discountId, int discountPercent, String discountFor, Date expirationDate, int status) {
        this.discountId = discountId;
        this.discountPercent = discountPercent;
        this.discountFor = discountFor;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountFor() {
        return discountFor;
    }

    public void setDiscountFor(String discountFor) {
        this.discountFor = discountFor;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
