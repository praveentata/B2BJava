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
public class ServicePO extends eDocument {
 
    
    private SESLineItems _sesLine;
    private SESSummary _sesSummary;

    public SESLineItems getSesLine() {
        return _sesLine;
    }

    public void setSesLine(SESLineItems _sesLine) {
        this._sesLine = _sesLine;
    }

    public SESSummary getSesSummary() {
        return _sesSummary;
    }

    public void setSesSummary(SESSummary _sesSummary) {
        this._sesSummary = _sesSummary;
    }
    

    public class SESLineItems extends LineItem
    {
        public void ServicePeriod(Date startDate, Date endTime)
        {
            
        }
    }
    
    public class SESSummary extends eDocSummary
    {
        private double maximum_total_amount;

        public double getMaximum_total_amount() {
            return maximum_total_amount;
        }

        public void setMaximum_total_amount(double maximum_total_amount) {
            this.maximum_total_amount = maximum_total_amount;
        }
        
    }
}
