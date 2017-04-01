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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Manjil Pradhan
 */
@Named(value = "registration")
@SessionScoped
public class Registration implements Serializable {
        private String vendor;
private String firstName;
private String lastName;
private String Email;
private String phone;
private String role;
private String userName;
private String password;
private String confirmPassword;
private String address;
private String message;
     public Registration() {
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
final private String  DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/tatap6108";
    final private String user = "tatap6108";
    final private String password1 = "1456061";
    Connection connection = null;  
	        Statement statement = null;    
	        ResultSet resultSet = null;  
   
    public String submit()
    {
      try{
        Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is ok!");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
         try
        {
            connection = DriverManager.getConnection(DB_Url, 
	            		user, password1);
        
        statement = connection.createStatement();
	resultSet = statement.executeQuery("Select * from team8registration where username = '"+getUserName()+"'");
        if(resultSet.next())
               {
	            	String errorMessage="The UserName already exists";
	            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,
	            				                                         errorMessage);
	            				            
	            	FacesContext.getCurrentInstance().addMessage("form1:uname", message);
	            			
	            	//return "";
	            }
       else if(getPassword().equals(getConfirmPassword()))
	            {
	            	int i=statement.executeUpdate("insert into team8registration(username,password,role,firstname,lastname,address,vendor,phone,confirmpassword,email)"
	            			+ " values ('"+getUserName()+"','"+getPassword()+"','"+getRole()+"','"+getFirstName()+"','"+getLastName()+"','"+getAddress()+"','"+getVendor()+"','"+getPhone()+"','"+getConfirmPassword()+"','"+getEmail()+"')");
	            	if(i>0)
	            	{
                                //return "RegistrationSuccessfull";
                            message = getRole().toUpperCase()+" "+getFirstName()+" "+getLastName()+" Successfully Registered with Username "+getUserName();
                                             
	            		
	            	}else
	            	{
	            		             message = getRole()+" "+getFirstName()+" "+getLastName()+" not registered";
                                             
	            	}
	            	//return message;
                        return "RegistrationSuccessfull";
                      
	            }else
	            {
	            	String errorMessage="The Password and Confirm Password are not same";
	            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,
	            				                                         errorMessage);
	            				            
	            	FacesContext.getCurrentInstance().addMessage("form1:cpsw", message);
	            			
	            	return "";
	            }
	          
	            
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	            return ("internalError");
	        }
	        finally
	        {
	            try
	            {
	                resultSet.close();
	                statement.close();
	                connection.close();
	                 
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();    
	            }
	        }
              
         return "";
    }
    
}


    /**
     * Creates a new instance of Registration
     */
   
    

