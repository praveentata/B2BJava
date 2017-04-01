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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TATA!
 */
@Named(value = "registrationBean")
@SessionScoped
public class RegistrationBean12 implements Serializable {

    private String first_name;
    private String last_name;
    private String user_name;
    private String password;
    private String confirm_password;
    private Date date_of_birth;
    private String gender;
    private ArrayList<String> gend = new ArrayList<>();
    final private String  DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    private final String userName = "tataP6108";
    private final String pass = "1456061";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    private String errorMessage = "";
    private String passError = "";
    
    
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean12() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getGend() {
        
        return gend;
    }

    public void setGend(ArrayList<String> gend) {
        gend.add("Male");
        gend.add("Female");
        this.gend = gend;
    }
    
    
    public void submit(String user_name)
    {
        String userName = "";
        try 
        {
            con = DriverManager.getConnection(DB_Url, userName, pass);
            stmt = (Statement) con.createStatement();
            
            String sql = "Select username from team8registration where username = " + user_name + ",";
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                setFirst_name(rs.getString("firstname"));
                userName = getFirst_name();
            }
            
            if(userName.isEmpty())
            {
                register();
            }
            else
            {
                errorMessage = "Username already exits.";
            }
                  
            
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
            
        
    }
    
    
    public String register()
    {
        Random r = new Random();
        String vendor = "buyer";
        String email = "test@test.com";
        String address = "Testing Address";
        try
        {
            con = DriverManager.getConnection(DB_Url, userName, pass);
            
            stmt = (Statement) con.createStatement();
            
            if(password.equals(confirm_password))
            {
            
                String sql = "INSERT INTO team8registration (userid, vendor, firstname, lastname, email, phone, role, username, password, confirmpassword, address) VALUES ("
                        + (r.nextInt(100) + 6) + "," + "\"" + vendor + "\","  + "\"" + first_name + "\"," + "\"" + last_name + "\","  + 
                        "\"" + email + "\"," + "\"" + 1234567890 + "\"," + "\"" + user_name + "\"," + "\"" + password + "\"," +
                        "\"" + confirm_password + "\"," + "\"" + address + "\")";

                stmt.executeUpdate(sql);
            }
            else
            {
                passError = "Password and Confirm Password do not match";
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());

        }
        
        return "thank-you";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPassError() {
        return passError;
    }

    public void setPassError(String passError) {
        this.passError = passError;
    }
}
