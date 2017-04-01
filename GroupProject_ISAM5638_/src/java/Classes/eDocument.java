/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;

/**
 *
 * @author TATA!
 */
public class eDocument {
    
    public class eDocHeader
    {
        private int document_id;
        private String creator;
        private String approver;
        private Date create_date;
        private Date issue_date;

        public eDocHeader(int document_id, Date create_date, Date issue_date) {
            this.document_id = document_id;
            this.create_date = create_date;
            this.issue_date = issue_date;
        }
        private String sender;
        private String receiver;
        private String header_note;

        eDocHeader() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public int getDocument_id() {
            return document_id;
        }

        public void setDocument_id(int document_id) {
            this.document_id = document_id;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public Date getCreate_date() {
            return create_date;
        }

        public void setCreate_date(Date create_date) {
            this.create_date = create_date;
        }

        public Date getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(Date issue_date) {
            this.issue_date = issue_date;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getHeader_note() {
            return header_note;
        }

        public void setHeader_note(String header_note) {
            this.header_note = header_note;
        }
        
        
        
    }
    
}
