package Group;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import javax.inject.Named;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VJ
 */
@Named
@ManagedBean

public class Backend 
{
    private String searchterm;
    private String searchby;
    private String sortby;
    private String select1;
    private int select2;
    private double tax=0;
    private int sum=0;
    private double totalamount;
    final private String DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/tatap6108";
    final private String userName = "tatap6108";
    final private String password = "1456061";
    
    private static ArrayList<posupplierclass> al = new ArrayList<>();
     private static ArrayList<posupplierclass> al1 = new ArrayList<>();
      private static ArrayList<posupplierclass> al2 = new ArrayList<>();
     posupplierclass p;

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSelect2() {
        return select2;
    }

    public void setSelect2(int select2) {
        this.select2 = select2;
    }

   

    public posupplierclass getP() {
        return p;
    }

    public void setP(posupplierclass p) {
        this.p = p;
    }

    

    public Backend() {
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getSearchby() {
        return searchby;
    }

    public void setSearchby(String searchby) {
        this.searchby = searchby;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }
    
    public String execute() throws ParseException
    {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is okay");
        } catch (ClassNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        }
        
        String colName = "";
        String sort="";
//        if(searchterm==null)
//        {
//            
//        }
       
        if(searchterm!=null &&searchby.equals("Name"))
        { 
            colName = "item_name";
        }
        if(searchterm!=null &&searchby.equals("Date"))
        { 
            colName = "date_created";
        }
        if(searchterm!=null &&searchby.equals("Status"))
        { 
            colName = "po_status";
        }
        if(sortby.equals("Sort_By_Date"))
        {
            sort="date_created";
            
        }
        else if(!sortby.equals("Sort_By_Status")) 
        {
            sort="po_status";
        }
        
        
        // String display = "SELECT * from user_po where "
                // +colName+
                 //" LIKE '%"+searchterm+"%' ORDER BY "  + sort ;
         String sqlTotal = "SELECT po_number, user_id, po_status, SUM(item_price), date_created from user_po where " + colName+ " LIKE "+ "\"%" + searchterm+ "%\"" +" AND "+ "po_status = " + "\"" + "pending" +"\"" + " GROUP BY po_number, user_id ORDER BY "+sort+" desc";
//         SELECT * FROM `user_po` ORDER BY `user_po`.`Date` ASC
         
        
        try {
          Connection c
                    = DriverManager.getConnection(DB_Url, userName, password);
            Statement s = c.createStatement();    
            ResultSet rs = s.executeQuery(sqlTotal);
           
            while(rs.next())
            {
                DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                
            p = new posupplierclass();
            String po_num = rs.getString(1);
                String u_id = rs.getString(2);
                String po_stat = rs.getString(3);
                String sum = rs.getString(4);
                String cr_date = rs.getString(5);
                Date dt = d.parse(cr_date);
                
                p.setPo_num(Integer.parseInt(po_num));
                p.setUser_id(Integer.parseInt(u_id));
                p.setStatus(po_stat);
                p.setTotal(Integer.parseInt(sum));
                p.setDate(dt);
                
                al.add(p);
            
            }
            
            c.close();
            rs.close();
            s.close();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

          return "response";
    }
    
    public String approve() throws SQLException, ParseException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is okay");
        } catch (ClassNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        }
       
         Connection c
                    = DriverManager.getConnection(DB_Url, userName, password);
            Statement s = c.createStatement(); 
            System.out.println("Size:  "+al.size());
        for(int i=0;i<al.size();i++)
        {
            if(al.get(i).isSelected()== true)
            { 
             int x=al.get(i).getPo_num();
              String approve="UPDATE user_po SET po_status=" + 
                      "\"APPROVE" + "\"" + "where po_number='"+x+"'";
             
             
               
               s.executeUpdate(approve);
              al.get(i).setStatus("APPROVE");
            }
        }
        
        String sqlTotal = "SELECT po_number, user_id, po_status, SUM(item_price), date_created from user_po where " + "po_status = " + "\"" + "APPROVE" +"\"" + " GROUP BY po_number, user_id ORDER BY date_created desc";
        try {
          Connection d
                    = DriverManager.getConnection(DB_Url, userName, password);
            Statement e = c.createStatement();    
            ResultSet rs = s.executeQuery(sqlTotal);
           
            while(rs.next())
            {
                DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
                
            p = new posupplierclass();
            String po_num = rs.getString(1);
                String u_id = rs.getString(2);
                String po_stat = rs.getString(3);
                String sum = rs.getString(4);
                String cr_date = rs.getString(5);
                Date dt = d1.parse(cr_date);
                
                p.setPo_num(Integer.parseInt(po_num));
                p.setUser_id(Integer.parseInt(u_id));
                p.setStatus(po_stat);
                p.setTotal(Integer.parseInt(sum));
                p.setDate(dt);
                
                al1.add(p);
            
            }
            
            c.close();
            rs.close();
            s.close();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return "final";
    }
    

    public ArrayList<posupplierclass> getAl() {
        return al;
    }

    public void setAl(ArrayList<posupplierclass> al) {
        this.al = al;
    }

    public  ArrayList<posupplierclass> getAl1() {
        return al1;
    }

    public  void setAl1(ArrayList<posupplierclass> al1) {
        this.al1 = al1;
    }

    public  ArrayList<posupplierclass> getAl2() {
        return al2;
    }

    public  void setAl2(ArrayList<posupplierclass> al2) {
        this.al2 = al2;
    }
    
    public String reject() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is okay");
        } catch (ClassNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        }
       
         Connection c
                    = DriverManager.getConnection(DB_Url, userName, password);
            Statement s = c.createStatement(); 
            System.out.println("Size:  "+al.size());
        for(int i=0;i<al.size();i++)
        {
            if(al.get(i).isSelected()== true)
            { 
             int x=al.get(i).getPo_num();
             
             String approve="UPDATE user_po SET po_status=" + 
                      "\"REJECT" + "\"" + "where po_number='"+x+"'";
               
               s.executeUpdate(approve);
              al.get(i).setStatus("REJECT");
            }
        }
        
          return "final";
    }
    public String generate(posupplierclass p) throws ParseException
    {
         select1 = "INV"+ p.getPo_num();
         select2 = p.getPo_num();
      String select="SELECT item_name,item_price,item_quantity, item_price*item_quantity as Total, curdate() from user_po where po_number = "+p.getPo_num();
        try {
          Connection d
                    = DriverManager.getConnection(DB_Url, userName, password);
            Statement e = d.createStatement();  
            
            ResultSet rs = e.executeQuery(select);
           
            while(rs.next())
            {
                DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
                
                
            p = new posupplierclass();
            String productname = rs.getString(1);
                String price = rs.getString(2);
                String quantity = rs.getString(3);
                String total = rs.getString(4);
                String cr_date = rs.getString(5);
                Date dt = d1.parse(cr_date);
                
                p.setProductname(productname);
                p.setPrice(price);
                p.setQuantity(quantity);
                p.setTotal(Integer.parseInt(total));
                p.setDate(dt);
                
                al2.add(p);
                
                         
            }
            for(int i=0;i<al2.size();i++)
        {
             
              sum=sum+al2.get(i).getTotal();
                      
        }
            tax=0.145*sum;
            totalamount = sum+tax;
            
            String process="UPDATE user_po SET po_status=" + 
                      "\"Processed" + "\"" + "where po_number="+select2;
            
             e.executeUpdate(process);
            
             String insert="INSERT INTO invoice_team5(invoice_number, po_num) VALUES ('"+"INV"+select2+"',"+select2+")";
             
             
             
            
             e.executeUpdate(insert);
             
            d.close();
            rs.close();
            e.close();
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "invoice";
    }
     

    
    
    
}
