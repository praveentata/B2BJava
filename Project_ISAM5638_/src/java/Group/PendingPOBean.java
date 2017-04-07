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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author TATA!
 */
@Named(value = "pendingPOBean")
@SessionScoped
public class PendingPOBean implements Serializable {

    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rt = null;
    String sql = "";
    String sqlTotal = "";
    
    
    
    private ArrayList<POLineItems> pendingProduct = new ArrayList<>();
    private ArrayList<POLineItems> pendingProducts = new ArrayList<>();
    private ArrayList<Products> product = new ArrayList<>();
    /**
     * Creates a new instance of PendingPOBean
     */
    public PendingPOBean() {
    }
    
    
     public String pendingPO()
    {
        SupplierProducts SP = new SupplierProducts();
        SP.loadMySQLDriver();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            sqlTotal = "SELECT po_number, user_id, po_status, SUM(item_price), date_created from user_po GROUP BY po_number, user_id;";
            
            rt = stmt.executeQuery(sqlTotal);
            
           
            while(rt.next())
            {
                DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                
                
                POLineItems I = new POLineItems();
                String po_num = rt.getString(1);
                String u_id = rt.getString(2);
                String po_stat = rt.getString(3);
                String sum = rt.getString(4);
                String cr_date = rt.getString(5);
                Date dt = d.parse(cr_date);
                
                I.setLine_number(Integer.parseInt(po_num));
                I.setUser_id(Integer.parseInt(u_id));
                I.setStatus(po_stat);
                I.setTotal_amount(Integer.parseInt(sum));
                I.setDate_created(dt);

                pendingProduct.add(I);
                
            }
            rt.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return "pending-po";
    }
     
     public String pendingPODetails(String option)
     {
         int po_number = 0;
         for(POLineItems PO : pendingProduct)
         {
             if(PO.getRadValue() != null && PO.getRadValue().equals("details"))
             {
                 po_number = PO.getLine_number();
                 break;
             }
         }
         
        SupplierProducts SP = new SupplierProducts();
        SP.loadMySQLDriver();
        
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, password);
            
            stmt = (Statement) con.createStatement();
            
            sqlTotal = sql = "SELECT * FROM user_po where po_number = " + po_number + ";";
            
            rs = stmt.executeQuery(sqlTotal);
            
            while(rs.next())
            {
                POLineItems I = new POLineItems();
                Products P = new Products();
                P.setItem_id(rs.getInt("item_id"));
                P.setItem_name(rs.getString("item_name"));
                P.setItem_image(rs.getString("item_image"));
                P.setItem_price(rs.getInt("item_price"));
                P.setItem_description(rs.getString("item_description"));
                P.setSupplier_name((rs.getString("supplier_name")));
                P.setItem_quantity(rs.getInt("item_quantity"));
                I.setStatus(rs.getString("po_status"));
                product.add(P);
                
                I.setProdList(product);
                I.setLine_number(rs.getInt("po_number"));
                
                I.setTotal_amount(rs.getInt("item_price"));
                
                pendingProducts.add(I);
                
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
         return "pending-po-details";
     }
    

    public ArrayList<POLineItems> getPendingProduct() {
        return pendingProduct;
    }

    public void setPendingProduct(ArrayList<POLineItems> pendingProduct) {
        this.pendingProduct = pendingProduct;
    }

    public ArrayList<Products> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Products> product) {
        this.product = product;
    }

    public ArrayList<POLineItems> getPendingProducts() {
        return pendingProducts;
    }

    public void setPendingProducts(ArrayList<POLineItems> pendingProducts) {
        this.pendingProducts = pendingProducts;
    }

   
}
