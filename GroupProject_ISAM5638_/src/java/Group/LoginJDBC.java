package Group;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;



/**
 *
 * @author Dhanya
 */
@Named(value = "loginJDBC")
@SessionScoped
public class LoginJDBC implements Serializable {

    /**
     * Creates a new instance of LoginJDBC
     */
    public LoginJDBC() {
    }
    private String userName;
    private String password;
    private String vendor;
    private String message;
    private String email;
    final private String  DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/test";
    final private String user = "tatap6108";
    final private String password1 = "1456061";
    Connection connection = null;  
    Statement statement = null;    
    ResultSet resultSet = null;   
    private String retrieveName = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
                
    
    public void loadMySqlDriver()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is ok!");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
public String ValidateUser(String uName, String password)
    {
        String errorMsg="";
        this.loadMySqlDriver();
        String dbPass = "";
        String role = "";
        int id = 0;
        
        
        
        
        try {
            connection = DriverManager.getConnection(DB_Url,
                    user, password1);
            statement = connection.createStatement();
        resultSet = statement.executeQuery("Select userid, username, password, vendor from team8registration where username = \""+uName+"\"");
           
        while(resultSet.next())
        {
            dbPass = resultSet.getString("password");
            role = resultSet.getString("vendor");
            id = resultSet.getInt("userid");
        }
        if(password.equals(dbPass)){
            if(role.equals("supplier")){
                HttpSession hs = Util.getSession();
                hs.setAttribute("user", uName);
                hs.setAttribute("id", id);
                return "supplier-home";
            }
             else if(role.equals("buyer")){
                HttpSession hs = Util.getSession();
                hs.setAttribute("user", uName);
                retrieveName = (String) hs.getAttribute("user");
                hs.setAttribute("id", id);
                return "index"; 
            }
        }
        
        else{
            message = "Invalid Login Credentials";
            return "login";
        }
       
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return message;
    }  

public String forgotPassword()
    {
        String errorMsg="";
        this.loadMySqlDriver();
        String dbUser = "";
        String dbEmail = "";
        String pass = "";
        int id = 0;
        String sql = "";
 
        try {
            connection = DriverManager.getConnection(DB_Url,user, password1);
            statement = connection.createStatement();
            sql = "Select  username,email, password from team8registration where username = \""+userName+ "\"" + " AND email=\"" + email +"\";" ;
        resultSet= statement.executeQuery(sql);
        //statement.executeQuery(Pass);
        
        while(resultSet.next())
        {
            //Users U = new Users();

              dbUser = resultSet.getString("username");
            dbEmail = resultSet.getString("email");
            pass = resultSet.getString("password");
        }
        if(userName.isEmpty() ||  email.isEmpty())
                {
                    String recoveryFailed = "Incorrect Username! Please try again.";
                    return "ForgotPassword";

                }
             else {
                message = "Your password is " + pass;
               
                return "ForgotPassword"; 
            }
        }
         catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return message;
    }       

    public String name()
    {
        return retrieveName;
    }
    public String getRetrieveName() {
        return retrieveName;
    }

    public void setRetrieveName(String retrieveName) {
        this.retrieveName = retrieveName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
