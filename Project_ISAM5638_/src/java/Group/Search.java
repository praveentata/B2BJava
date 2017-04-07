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
import java.util.ArrayList;
import javax.persistence.PostLoad;

/**
 *
 * @author TATA!
 */
@Named(value = "search")
@SessionScoped
public class Search implements Serializable {

    
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/tatap6108";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    private String name_price;
    String sql = "";
    int count = 0;
    
    
    private ArrayList<String> price_name = new ArrayList<>();
    
    ArrayList<Products> product = new ArrayList<>();
    ArrayList<Products> newProduct = new ArrayList<>();
    ResultSet rs = null;
    /**
     * Creates a new instance of Search
     */
    public Search() {
        price_name.add("Sort By Name or Price");
        price_name.add("Name");
        price_name.add("Price");
    }
    
    public void loadMySQLDriver()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is okay.");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @PostLoad
    public ArrayList<Products> loadAllProducts(String namePrice)
    {
        if(count == 0)
        {
            loadMySQLDriver();
            count ++;
        }
        try
        {
            Connection con = DriverManager.getConnection(DB_Url,userName, password);
            
            Statement stmt = (Statement) con.createStatement();
            
            sql = "SELECT * FROM products;";
            
            rs = stmt.executeQuery(sql);
            
//            if(!product.isEmpty() && product.size() > 5)
//            {
//                {
//                    for(int i = 5; i <= product.size(); i ++)
//                    {
//                        product.remove(i);
//                    }
//                }
//            }
            
            if(count == 1)
            {
                count ++;
                while(rs.next())
                {
                    Products P = new Products();
                    P.setItem_id(rs.getInt("item_id"));
                    P.setItem_name(rs.getString("item_name"));
                    P.setItem_image(rs.getString("item_image"));
                    P.setItem_price(rs.getInt("item_price"));
                    P.setItem_description(rs.getString("item_description"));
                    P.setSupplier_name((rs.getString("supplier_name")));
                    product.add(P);
                } 
            }
            
            if(namePrice.equals("Name"))
            {
            
               sortName(namePrice);
            }
            
            else if(namePrice.equals("Price"))
            {
            
               sortPrice(namePrice);
            }
            
        }
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
//         if(!product.isEmpty() && product.size() > 5)
//            {
//                {
//                    for(int i = 5; i <= product.size(); i ++)
//                    {
//                        product.remove(i);
//                    }
//                }
//            }
//        

        for(Products p : product)
        {
            if(p == null)
            {
                product.remove(p);
            }
        }
        return product;
    }
    
    public ArrayList<Products> loadAllProducts()
    {
        loadMySQLDriver();
        
        try
        {
            Connection con = DriverManager.getConnection(DB_Url,userName, password);
            
            Statement stmt = (Statement) con.createStatement();
            
            sql = "SELECT * FROM products;";
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                Products P = new Products();
                P.setItem_id(rs.getInt("item_id"));
                P.setItem_name(rs.getString("item_name"));
                P.setItem_image(rs.getString("item_image"));
                P.setItem_price(rs.getInt("item_price"));
                P.setItem_description(rs.getString("item_description"));
                P.setSupplier_name((rs.getString("supplier_name")));
                product.add(P);
            } 
        }
        
            catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
       
        
        return product;
        
    }
    
    @PostLoad
    public void sortName(String namePrice)
    {
       
        try
        {
            Connection con = DriverManager.getConnection(DB_Url,userName, password);
            
            Statement stmt = (Statement) con.createStatement();
            
            sql = "SELECT * FROM products order by item_name ;";
            
            rs = stmt.executeQuery(sql);
            
//            for(Products p : product)
//            {
//                if(p.isSelected() == true)
//                {
//                    newProduct.add(p);
//                }
//            }
            
            if(!product.isEmpty())
            {
                product.clear();
            }

            while(rs.next())
            {
                Products P = new Products();
                P.setItem_id(rs.getInt("item_id"));
                P.setItem_name(rs.getString("item_name"));
                P.setItem_image(rs.getString("item_image"));
                P.setItem_price(rs.getInt("item_price"));
                P.setItem_description(rs.getString("item_description"));
                P.setSupplier_name((rs.getString("supplier_name")));
                product.add(P);
            } 
//            for(int i = 0; i < newProduct.size(); i ++)
//            {
//                if(newProduct.get(i).getItem_id() == product.get(i).getItem_id())
//                {
//                    product.set(i, newProduct.get(i));
//                }
//            }

        for(Products p : product)
        {
            if(p == null)
            {
                product.remove(p);
            }
        }
        }
        
       
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        //return product;
    }
    
    @PostLoad
        public void sortPrice(String namePrice)
    {
       int newCount = 0;
        try
        {
            Connection con = DriverManager.getConnection(DB_Url,userName, password);
            
            Statement stmt = (Statement) con.createStatement();
            
            sql = "SELECT * FROM products order by item_price ;";
            
            rs = stmt.executeQuery(sql);
            
//            for(Products p : product)
//            {
//                if(p.isSelected() == false)
//                {
//                    product.remove(p);
//                }
//            }
             
//            for(Products p : product)
//            {
//                if(p.isSelected() == true)
//                {
//                    newProduct.add(p);
//                }
//            }
            if(newCount == 0)
            {
                newCount ++;
                if(!product.isEmpty())
                {
                    product.clear();
                }
    //            

                while(rs.next())
                {
                    Products P = new Products();
                    P.setItem_id(rs.getInt("item_id"));
                    P.setItem_name(rs.getString("item_name"));
                    P.setItem_image(rs.getString("item_image"));
                    P.setItem_price(rs.getInt("item_price"));
                    P.setItem_description(rs.getString("item_description"));
                    P.setSupplier_name((rs.getString("supplier_name")));
                    product.add(P);
                }

    //            for(int i = 0; i < newProduct.size(); i ++)
    //            {
    //                if(newProduct.get(i).getItem_id() == product.get(i).getItem_id())
    //                {
    //                    product.set(i, newProduct.get(i));
    //                }
    //            }
            }
            
            for(Products p : product)
        {
            if(p == null)
            {
                product.remove(p);
            }
        }
        }
        
        
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        //return product;
    }
   
    

    public String getName_price() {
        return name_price;
    }

    public void setName_price(String name_price) {
        this.name_price = name_price;
    }

    public ArrayList<String> getPrice_name() {
        return price_name;
    }

    public void setPrice_name(ArrayList<String> price_name) {
        this.price_name = price_name;
    }

  

    
    
    
    
}
