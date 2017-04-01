/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author TATA!
 */
public class LineItem {
    
    private int line_number;
    private ArrayList<Products> product_list;

    
    public LineItem(){}
    
    public LineItem(int line_number, ArrayList<Products> product_list, double line_item_total_amount) {
        this.line_number = line_number;
        this.product_list = product_list;
        this.line_item_total_amount = line_item_total_amount;
    }
    private double line_item_total_amount;

    public int getLine_number() {
        return line_number;
    }

    public void setLine_number(int line_number) {
        this.line_number = line_number;
    }

    public ArrayList<Products> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(ArrayList<Products> product_list) {
        this.product_list = product_list;
    }

    public double getLine_item_total_amount() {
        return line_item_total_amount;
    }

    public void setLine_item_total_amount(double line_item_total_amount) {
        this.line_item_total_amount = line_item_total_amount;
    }
    
    
}
