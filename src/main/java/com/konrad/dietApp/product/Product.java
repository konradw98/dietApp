package com.konrad.dietApp.product;

import com.konrad.dietApp.supplier.Supplier;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {

    @Id
    private int id;
    private String name;
    private int kcal;
    private String description;



    public Product(){}

    public Product(int id, String name, int kcal, String description) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
