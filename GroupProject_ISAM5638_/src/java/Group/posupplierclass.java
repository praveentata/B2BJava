package Group;


import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VJ
 */
@ManagedBean
@SessionScoped

public class posupplierclass 
{
private int itemnumber;
    private String productname;
    private String price;
    private String quantity;
    private String status;
    private int total;
    private Date date;
    private int po_num;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPo_num() {
        return po_num;
    }

    public void setPo_num(int po_num) {
        this.po_num = po_num;
    }
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
     public posupplierclass() {
    }
    public posupplierclass(int itemnumber, String productname, String price, String quantity, int total, String status, Date date) {
        this.itemnumber = itemnumber;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.total = total;
        this.date = date;
        
    }

    public int getItemnumber() {
        return itemnumber;
    }

    public void setItemnumber(int itemnumber) {
        this.itemnumber = itemnumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

   

   
    
   
}
