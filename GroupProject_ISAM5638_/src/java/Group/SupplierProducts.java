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
import java.util.Objects;
import javax.faces.context.FacesContext;

/**
 *
 * @author TATA!
 */
@Named(value = "supplierProducts")
@SessionScoped
public class SupplierProducts implements Serializable {

    
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    
    private ArrayList<Products> list = new ArrayList<>();
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public ArrayList<Products> getList() {
        return list;
    }

    public void setList(ArrayList<Products> list) {
        this.list = list;
    }
    private String s_name;
    /**
     * Creates a new instance of SupplierProducts
     */
    public SupplierProducts() {
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
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
     
     
     public ArrayList<Products> sortProducts(String supplier_name) 
    {
        
        
        loadMySQLDriver();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            String sql = "SELECT * FROM products where supplier_name = \"" + supplier_name + "\";";
            
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

   
}
