/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TATA!
 */
@Named(value = "shoppingCart")
@SessionScoped
public class ShoppingCart extends LoginJDBC implements Serializable  {

    
    private static ArrayList<Item> cart = new ArrayList<>();
    private double total;
    
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/tatap6108";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";
    int id = 0;
    private String user_name = "";
    
    HttpSession hs = Util.getSession();
    String u_name = (String) hs.getAttribute("user");
    int user_id = (int) hs.getAttribute("id");
    /**
     * Creates a new instance of ShoppingCart
     */
    public ShoppingCart() {
    }
    
    public String addAllToCart(ArrayList<Products> P) throws ParseException
    {
            
        int count = 0;
        boolean found = false;
       
        
        for(Products p : P)
        {
            Item I = new Item();
            found = false;
            
            if(p.isSelected() == true)
            {
                count ++;
                
                for(int i = 0; i < cart.size(); i ++)
                {
                    
                    if(cart.get(i).getP().getItem_id() == p.getItem_id())
                    {
                        found = true;
                        Item IP = cart.get(i);
                        //IP.setQuantity(IP.getQuantity() + 1);
                        //cart.set(i, IP);
                        id = IP.getP().getItem_id();
                        cart.remove(i);
                        //cart.remove(IP);
                        HttpSession hs1 = Util.getSession();
                        if(user_id == (int) hs1.getAttribute("id"))
                        {
                            deleteFromDB();
                        }
                        
                        I.setQuantity(p.getItem_quantity());
                        I.setP(p);
                        cart.add(I);
                        //insertIntoDB();
                        //return "shopping-cart";
                    }
                    
                }

                if(found == false)
                {
                    I.setQuantity(p.getItem_quantity());
                    I.setP(p);
                    cart.add(I);
                }
            }
            
        }
        
        if(!cart.isEmpty())
        {
            deleteItemFromDB();
            insertIntoDB();
            poInsert();
        }
        
        return "index";
        
    }
    
    public String addToCart(Products P) throws ParseException
    {
        HttpSession hs = Util.getSession();
        String u_name = (String) hs.getAttribute("user");
        user_id = (int) hs.getAttribute("id");
        
        for(Item I : getCart())
            {
                if(I.getP().getItem_id() == P.getItem_id())
                {
                    id = I.getP().getItem_id();
                    cart.remove(I);
                    
                    
                    I.setQuantity(P.getItem_quantity());
                    I.setP(P);
                    
                    cart.add(I);
                    
                    HttpSession hs1 = Util.getSession();
        
                    if(user_id == (int) hs.getAttribute("id"))
                    {
                        deleteFromDB();
                    }
                    insertIntoDB();
                    poInsert();
                    
                    //I.setQuantity(I.getQuantity() + 1);
                    return "index";
                }
            }
        
        
        Item I = new Item();
        
        
        I.setQuantity(P.getItem_quantity());
        I.setP(P);
        cart.add(I);
        
        if(!cart.isEmpty())
        {
            deleteItemFromDB();
            insertIntoDB();
            poInsert();
        }
        
        return "index";
    }
    
    
    public void insertIntoDB() throws ParseException
    {
        Random r = new Random();
        String status = "pending";
        
        DateFormat d = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            for(Item I : cart)
            {
                sql = "INSERT INTO shopping_cart(itemnumber, user_id, productname, price, quantity, total, Status, Date)"
                + "Values (" + I.getP().getItem_id() + "," + user_id + "," + "\"" + I.getP().getItem_name() + "\"" + "," + (int) I.getP().getItem_price() + 
                      "," +  I.getQuantity() + "," + (int) I.getP().getItem_price() * I.getQuantity() + "," + "\"" + status + "\"" + "," + "\"" + d.format(date) + "\"" + ")";
                
                stmt.executeUpdate(sql);
            }
            
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteFromDB()
    {
        //String db_url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
        try
        {
        
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            for(Item I : cart)
            {
                sql = "DELETE from shopping_cart where itemnumber = " + id + " AND user_id = " + user_id + ";";
                
                stmt.executeUpdate(sql);
            }
            
            
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteItemFromDB()
    {
        
        try
        {
        
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            for(Item I : cart)
            {
                sql = "DELETE from shopping_cart where itemnumber = " + I.getP().getItem_id() + " AND user_id = " + user_id + ";";
                
                stmt.executeUpdate(sql);
            }
            
            
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Item I)
    {
        for(Item item : cart)
        {
            if(item.equals(I))
            {
                cart.remove(item);
                break;
            }
            
        }
    }

    public void update()
    {
        
    }
    
    public String po()
    {
        SupplierProducts SP = new SupplierProducts();
        SP.loadMySQLDriver();
        Random r = new Random();
        int po_number = r.nextInt(1000);
        
        try
        {
            DateFormat d = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
        
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            for(Item I : cart)
            {
                sql = "INSERT INTO user_po(po_number, item_id, user_id, po_status, supplier_name, item_name, item_description, item_price, "
                + "item_quantity, item_image, date_created, date_approved)"
                + "Values (" + po_number + "," + I.getP().getItem_id() + "," + user_id + "," + "\"" + "pending" + "\"" + ","  + "\"" + I.getP().getSupplier_name() + "\"," + "\"" + I.getP().getItem_name() + "\"" + "," + "\"" 
                        + I.getP().getItem_description() + "\"" + ","  + (int) I.getP().getItem_price() * I.getQuantity() + ","  + I.getQuantity() + ","
                  + "\"" + I.getP().getItem_image() + "\"" + "," + "\"" + d.format(date) + "\"" + "," + "\""  + d.format(date) + "\"" + ")";
                
                stmt.executeUpdate(sql);
            }
            
            rs.close();
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return "pending-po";
    }
    
    public void poInsert()
    {
        SupplierProducts SP = new SupplierProducts();
        SP.loadMySQLDriver();
        Random r = new Random();
        int po_number = r.nextInt(1000);
        
        try
        {
            DateFormat d = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
        
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            for(Item I : cart)
            {
                sql = "INSERT INTO user_po(po_number, item_id, user_id, po_status, supplier_name, item_name, item_description, item_price, "
                + "item_quantity, item_image, date_created, date_approved)"
                + "Values (" + po_number + "," + I.getP().getItem_id() + "," + user_id + "," + "\"" + "pending" + "\"" + ","  + "\"" + I.getP().getSupplier_name() + "\"," + "\"" + I.getP().getItem_name() + "\"" + "," + "\"" 
                        + I.getP().getItem_description() + "\"" + ","  + (int) I.getP().getItem_price() * I.getQuantity() + ","  + I.getQuantity() + ","
                  + "\"" + I.getP().getItem_image() + "\"" + "," + "\"" + d.format(date) + "\"" + "," + "\""  + d.format(date) + "\"" + ")";
                
                stmt.executeUpdate(sql);
            }
            
            
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        
    }
 

    /**
     * @return the total
     */
    public double getTotal() {
        total = 0;
        for(Item I : cart)
        {
            total = total + (I.getQuantity() * I.getP().getItem_price());
        }
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the cart
     */
    public ArrayList<Item> getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(ArrayList<Item> cart) {
        this.cart = cart;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

   


  
    
}
