package Group;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SaikiranReddy
 */
public class RegistrationJDBC {
    private String message;
    final private String  DB_Url = "jdbc:mysql://mis-sql.uhcl.edu:3306/tatap6108";
    final private String user = "tatap6108";
    final private String password1 = "1456061";
    Connection connection = null;  
	        Statement statement = null;    
	        ResultSet resultSet = null;   
                public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
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
    
    public String saveUser(People people)
    {
        String errorMsg="";
        this.loadMySqlDriver();
        try
        {
            connection = DriverManager.getConnection(DB_Url, 
	            		user, password1);
        
        statement = connection.createStatement();
	resultSet = statement.executeQuery("Select * from team8registration where username = '"+people.getUserName()+"'");
        if(resultSet.next())
               {
	            	String errorMessage="The UserName already exists";
	            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,
	            				                                         errorMessage);
	            				            
	            	FacesContext.getCurrentInstance().addMessage("form1:uname", message);
	            			
	            	return "";
	            }
        if(people.getPassword().equals(people.getConfirmPassword()))
	            {
	            	int i=statement.executeUpdate("insert into team8registration(username,password,role,firstname,lastname,address,vendor,phone,confirmpassword,email)"
	            			+ " values ('"+people.getUserName()+"','"+people.getPassword()+"','"+people.getRole()+"','"+people.getFirstName()+"','"+people.getLastName()+"','"+people.getAddress()+"','"+people.getVendor()+"','"+people.getPhone()+"','"+people.getConfirmPassword()+"','"+people.getEmail()+"')");
	            	if(i>0)
	            	{
	            		             message = people.getRole()+" "+people.getFirstName()+" "+people.getLastName()+" successfully registered with Username "+people.getUserName();
                                             
	            		
	            	}else
	            	{
	            		             message = people.getRole()+" "+people.getFirstName()+" "+people.getLastName()+" not registered";
                                             
	            	}
	            	
                        return message;
                      
	            }else
	            {
	            	String errorMessage="The Password and Confirm Password are not same";
	            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,
	            				                                         errorMessage);
	            				            
	            	FacesContext.getCurrentInstance().addMessage("form1:cpsw", message);
	            			
	            	return "index";
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
	    }

}
