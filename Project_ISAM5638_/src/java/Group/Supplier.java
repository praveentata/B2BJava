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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author TATA!
 */
@Named(value = "supplier")
@SessionScoped
public class Supplier implements Serializable {

    
    private List<String> supplier = new ArrayList<>();
    
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    private String s_name;
    private String name;
    private 
    ResultSet rs = null;
    /**
     * Creates a new instance of Supplier
     */
    public Supplier() {
        
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
    
     @PostConstruct
    public void addSupplier() 
    {
        
        loadMySQLDriver();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        
        try
        {
            Connection con = DriverManager.getConnection(DB_Url, userName, password);
            
            Statement stmt = (Statement) con.createStatement();
            
            String sql = "SELECT supplier_name from supplier;";
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                supplier.add(rs.getString("supplier_name"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
    

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public List<String> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<String> supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

  
}
