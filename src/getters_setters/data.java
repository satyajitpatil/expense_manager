/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getters_setters;

/**
 *
 * @author satyajit_patil
 */
public class data {
    public String product;
    public int amount;
    public String category;

    public data(String product, int amount, String category) {
        this.product = product;
        this.amount = amount;
        this.category=category;
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
    public String getCategory(){
        return category;
    }
    
    public void setCategory(String category){
        this.category = category;
    } 
}
