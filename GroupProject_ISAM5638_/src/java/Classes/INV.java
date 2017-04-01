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
public class INV extends eDocument {
    
    private int reference_PO_number;
    private int reference_GR_number;
    private int referece_SESR_number;
    private Date issue_date;
    private Payment payment_info;

    public int getReference_PO_number() {
        return reference_PO_number;
    }

    public void setReference_PO_number(int reference_PO_number) {
        this.reference_PO_number = reference_PO_number;
    }

    public int getReference_GR_number() {
        return reference_GR_number;
    }

    public void setReference_GR_number(int reference_GR_number) {
        this.reference_GR_number = reference_GR_number;
    }

    public int getReferece_SESR_number() {
        return referece_SESR_number;
    }

    public void setReferece_SESR_number(int referece_SESR_number) {
        this.referece_SESR_number = referece_SESR_number;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Payment getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(Payment payment_info) {
        this.payment_info = payment_info;
    }
    
    public INV invoicde()
    {
        eDocument.eDocHeader eD = new eDocument.eDocHeader();
        INV I = new INV();
        I.issue_date = eD.getCreate_date();
        I.reference_PO_number = eD.getDocument_id();
        return I;
        
    }
    
    public class InvLineItems extends LineItem
    {
        
        public LineItem invoiceLine()
        {
            INV.InvLineItems ILI = new INV.InvLineItems();
            
            return ILI;
        }
    }
    
    public class InvSummary extends eDocSummary
    {
        
    }
    
    
    
}
