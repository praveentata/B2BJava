/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author TATA!
 */
@Named(value = "allProducts")
@RequestScoped
public class AllProducts {

    
    private String item_name;
    private double item_price;
    private String item_description;
    private String item_image;
    /**
     * Creates a new instance of AllProducts
     */
    public AllProducts() {
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

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }
    
    
     public ArrayList<Products> preLoad()
    {
        Search s = new Search();
        return s.loadAllProducts();
    }
}
