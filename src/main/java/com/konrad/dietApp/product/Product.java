package com.konrad.dietApp.product;

import com.konrad.dietApp.supplier.Supplier;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {

    @Id
    private int id;
    private String productName;
    private int kcal;



    public Product(){}

    public Product(int productId, String productName, int kcal) {
        this.id = productId;
        this.productName = productName;
        this.kcal = kcal;
    }

    public int getProductId() {
        return id;
    }

    public void setProductId(int productId) {
        this.id = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
