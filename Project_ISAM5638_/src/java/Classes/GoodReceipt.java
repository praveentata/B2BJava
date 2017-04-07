/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.util.Date;



/**
 *
 * @author TATA!
 */
public class GoodReceipt extends eDocument {
    
    private int referece_PO_number;
    
    private int reference_ASN_number;
    private Date goods_receive_date;
    private String gods_receive_status;

    public int getReferece_PO_number() {
        return referece_PO_number;
    }

    public void setReferece_PO_number(int referece_PO_number) {
        this.referece_PO_number = referece_PO_number;
    }

    public int getReference_ASN_number() {
        return reference_ASN_number;
    }

    public void setReference_ASN_number(int reference_ASN_number) {
        this.reference_ASN_number = reference_ASN_number;
    }

    public Date getGoods_receive_date() {
        return goods_receive_date;
    }

    public void setGoods_receive_date(Date goods_receive_date) {
        this.goods_receive_date = goods_receive_date;
    }

    public String getGods_receive_status() {
        return gods_receive_status;
    }

    public void setGods_receive_status(String gods_receive_status) {
        this.gods_receive_status = gods_receive_status;
    }
    
}
