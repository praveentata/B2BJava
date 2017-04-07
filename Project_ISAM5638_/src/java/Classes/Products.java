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
public class Products {
    
    public abstract class Item
    {
        private int item_id;
        private String item_name;
        private double item_price;
        private String item_unit_of_measurement;
        private String item_description;
        private String item_notes;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public double getItem_price() {
            return item_price;
        }

        public void setItem_price(double item_price) {
            this.item_price = item_price;
        }

        public String getItem_unit_of_measurement() {
            return item_unit_of_measurement;
        }

        public void setItem_unit_of_measurement(String item_unit_of_measurement) {
            this.item_unit_of_measurement = item_unit_of_measurement;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public String getItem_notes() {
            return item_notes;
        }

        public void setItem_notes(String item_notes) {
            this.item_notes = item_notes;
        }
        
    }
    
    public class MaterialWeight
    {
        private double item_weight;
        
    }
    
    public class ItemSize
    {
        private double item_unit_of_measurement;
        private double item_width;
        private double item_height;
        private double item_depth;

        public double getItem_unit_of_measurement() {
            return item_unit_of_measurement;
        }

        public void setItem_unit_of_measurement(double item_unit_of_measurement) {
            this.item_unit_of_measurement = item_unit_of_measurement;
        }

        public double getItem_width() {
            return item_width;
        }

        public void setItem_width(double item_width) {
            this.item_width = item_width;
        }

        public double getItem_height() {
            return item_height;
        }

        public void setItem_height(double item_height) {
            this.item_height = item_height;
        }

        public double getItem_depth() {
            return item_depth;
        }

        public void setItem_depth(double item_depth) {
            this.item_depth = item_depth;
        }
        
        
    }
    
}
