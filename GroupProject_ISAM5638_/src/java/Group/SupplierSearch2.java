/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author TATA!
 */
@Named(value = "supplierSearch")
@Dependent
public class SupplierSearch2 {

    
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    private ArrayList<Products> list = new ArrayList<>();
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    private String name;
    /**
     * Creates a new instance of SupplierSearch
     */
    public SupplierSearch2() {
    }

    /**
     * @return the list
     */
    public ArrayList<Products> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<Products> list) {
        this.list = list;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     
     public ArrayList<Products> searchSupplierProducts(String pName) 
    {
        
        
        loadMySQLDriver();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            String sql = "SELECT * FROM products WHERE item_name like  \"%" + pName + "%\"" + " or item_description like \"%" + pName + "%\";"  ;
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                Products P = new Products();
                P.setItem_id(rs.getInt("item_id"));
                P.setItem_image(rs.getString("item_image"));
                P.setItem_name(rs.getString("item_name"));
                P.setItem_description(rs.getString("item_description"));
                P.setSupplier_name((rs.getString("supplier_name")));
                P.setItem_price(rs.getInt("item_price"));
                
                list.add(P);
            }
            rs.close();
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
     
     public ArrayList<Products> searchSupplierProducts(String pName, String supplier_name) 
    {
        
        
        loadMySQLDriver();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            String sql = "SELECT * FROM products WHERE item_name like  \"%" + pName + "%\"" + " or item_description like \"%" + pName + "%\" and supplier_name = \"" + supplier_name + "\";";
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                Products P = new Products();
                P.setItem_id(rs.getInt("item_id"));
                P.setItem_image(rs.getString("item_image"));
                P.setItem_price(rs.getDouble("item_price"));
                P.setItem_name(rs.getString("item_name"));
                P.setSupplier_name((rs.getString("supplier_name")));
                P.setItem_description(rs.getString("item_description"));
                list.add(P);
            }
            rs.close();
            stmt.close();
            con.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
