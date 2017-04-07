/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import java.util.ArrayList;

/**
 *
 * @author TATA!
 */
public class Products extends SupplierInformation {
    
    private int item_id;
    private String item_name;
    private double item_price;
    private String item_unitOfMeasurement;
    private String item_description;
    private String item_notes;
    private String item_image;
    private int item_quantity;
    private boolean selected;
    private LoginJDBC user;
    
    
    
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    @Override
    public String getSupplier_name() {
        return super.getSupplier_name(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getItem_unitOfMeasurement() {
        return item_unitOfMeasurement;
    }

    public void setItem_unitOfMeasurement(String item_unitOfMeasurement) {
        this.item_unitOfMeasurement = item_unitOfMeasurement;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_notes() {
        return item_notes;
    }

    public void setItem_notes(String item_notes) {
        this.item_notes = item_notes;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public LoginJDBC getUser() {
        return user;
    }

    
    public void setUser(LoginJDBC user) {
        this.user = user;
    }
    
    
   
}
