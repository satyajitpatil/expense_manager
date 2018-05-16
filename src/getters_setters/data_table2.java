/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getters_setters;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author satyajit_patil
 */
public class data_table2 {
    public String product;
    public int amount;
    public String category;
    public Date date;

    public data_table2(String product, int amount, String category, Date date) {
        this.product = product;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
