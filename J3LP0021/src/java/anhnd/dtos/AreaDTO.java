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
public class AreaDTO implements Serializable{
    private String areaId;
    private String areaName;

    public AreaDTO(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
}
