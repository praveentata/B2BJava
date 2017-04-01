/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import java.util.Date;

/**
 *
 * @author TATA!
 */
public class POLineItems extends LineItem{
    
    private String status;
    private int user_id;
    private Date date_created;
    private Date date_approved;
    private String radValue;
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_approved() {
        return date_approved;
    }

    public void setDate_approved(Date date_approved) {
        this.date_approved = date_approved;
    }

    public String getRadValue() {
        return radValue;
    }

    public void setRadValue(String radValue) {
        this.radValue = radValue;
    }
}
