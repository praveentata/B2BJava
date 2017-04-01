/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author TATA!
 */
public class eDocSummary {
    
    private double _amount;
    private double _tax;
    private double discount_amount;
    private double total_amount;

    /**
     * @return the _amount
     */
    public double getAmount() {
        return _amount;
    }

    /**
     * @param _amount the _amount to set
     */
    public void setAmount(double _amount) {
        this._amount = _amount;
    }

    public double getTax() {
        return _tax;
    }

    public void setTax(double _tax) {
        this._tax = _tax;
    }

    public double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    
    
    
}
