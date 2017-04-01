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
public abstract class LineItem {
    
    private int line_number;
    private ArrayList<Products> prodList;
    private double total_amount;

    
    
    public int getLine_number() {
        return line_number;
    }

    public void setLine_number(int line_number) {
        this.line_number = line_number;
    }

    public ArrayList<Products> getProdList() {
        return prodList;
    }

    public void setProdList(ArrayList<Products> prodList) {
        this.prodList = prodList;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    
}
