package Personal;

import Group.Util;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manjil Pradhan
 */
@ManagedBean
@SessionScoped

public class shoppingCart1 implements Serializable {

    private ArrayList<Integer> quantities = new ArrayList<Integer>();

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    private int val = 0;
    HttpSession hs = Util.getSession();
    String u_name = (String) hs.getAttribute("user");
    int user_id = (int) hs.getAttribute("id");

    private String error_message;

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public ArrayList<shopList> viewshoppingcart() {

        HttpSession hs = Util.getSession();
        String u_name = (String) hs.getAttribute("user");
        int user_id = (int) hs.getAttribute("id");

        ArrayList<shopList> list2 = new ArrayList<>();

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            rs = stat.executeQuery("select user_id, itemnumber, productname, price, SUM(quantity), SUM(total), Status, Date from shopping_cart where user_id = " + user_id + " GROUP BY itemnumber, price, quantity, price, total");

            while (rs.next()) {
                shopList service2 = new shopList();
                service2.setItnumber(rs.getInt(2));
                service2.setProname(rs.getString(3));
                service2.setPri(rs.getInt(4));
                service2.setQuant(rs.getInt(5));
                service2.setTot(rs.getInt(6));
                service2.setStatus(rs.getString(7));
                service2.setDate(rs.getString(8));
                service2.setUser_id(rs.getInt("user_id"));
                list2.add(service2);

            }
        } catch (SQLException e) {

            error_message = e.getMessage();
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list2;
    }

    public void delete(int itemid1) {

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            String query = "delete from shopping_cart where (itemnumber= " + itemid1 + " AND user_id = " + user_id + ")";
            int r = stat.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String checkout() {

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null, stat1 = null, stat2 = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            stat1 = con.createStatement();
            stat2 = con.createStatement();

            // inserting the data in lineitem
            rs = stat1.executeQuery("select * from shopping_cart");
            while (rs.next()) {

                int I = stat.executeUpdate("insert into lineitem  values ('" + rs.getInt(2) + "','"
                        + rs.getString(3) + "','" + rs.getInt(4) + "','" + rs.getInt(5) + "','" + rs.getInt(6) + "', '" + rs.getString(7) + "', '" + rs.getString(8) + "', '" + rs.getInt(1) + "' )");

            }
            // updateing
            int K = stat2.executeUpdate("update lineitem set status = 'Sent' where user_id = " + user_id + " ");
            // deleteing the items in shoppingcart

            rs = stat1.executeQuery("select * from shopping_cart");
            while (rs.next()) {
                String query = "delete from shopping_cart ";
                stat1.executeUpdate(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                stat1.close();
                stat2.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "message.xhtml";
    }

    public ArrayList<shopList> posent() {

        ArrayList<shopList> list3 = new ArrayList<>();

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            rs = stat.executeQuery("select * from lineitem");

            while (rs.next()) {
                shopList service2 = new shopList();
                service2.setItnumber(rs.getInt(1));
                service2.setProname(rs.getString(2));
                service2.setPri(rs.getInt(3));
                service2.setQuant(rs.getInt(4));
                service2.setTot(rs.getInt(5));
                service2.setStatus(rs.getString(6));
                service2.setDate(rs.getString(7));
                list3.add(service2);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list3;
    }

    public void edit(int itemid, HtmlInputText input, int price) {
        // edit the row

        String a = input.getSubmittedValue().toString();
        if (a != null && !a.equals("")) {
            val = Integer.parseInt(a);
        } else {
            val = 0;
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            String query = "update shopping_cart set quantity = '" + val + "'  where user_id = " + user_id + " and (itemnumber='" + itemid + "')";
            String query1 = "update shopping_cart set total = '" + (val * price) + "'  where user_id = " + user_id + " and (itemnumber='" + itemid + "')";
            //int r = stat.executeUpdate(query);
            //int m = stat.executeUpdate(query1);
            stat.executeUpdate(query);
            stat.executeUpdate(query1);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void delete2(int itemid1) {

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            String query = "delete from lineitem where (itemnumber='" + itemid1 + "')";
            int r = stat.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private double totaltax;

    public double getTotaltax() {
        return totaltax;
    }

    public double tax1;

    public double getTax1() {
        return tax1;
    }

    public void total() {

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/tatap6108";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        double total = 0;
        quantity = 0;
        tax1 = 0;
        totaltax = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DATABASE_URL, "tatap6108", "1456061");
            stat = con.createStatement();
            rs = stat.executeQuery("select quantity, total from lineitem");

            while (rs.next()) {
                quantity += rs.getInt(1);

                total += rs.getInt(2);
                tax1 = (total * 0.08);
                totaltax = tax1 + total;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                stat.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
