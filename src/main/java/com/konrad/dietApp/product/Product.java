package com.konrad.dietApp.product;

import com.konrad.dietApp.supplier.Supplier;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {

    @Id
    private int id;
    private String name;
    private int protein;
    private int fat;
    private int carbo;
    private float kcal;
    private String description;



    public Product(){}

    public Product(int id, String name, float kcal, String description, int protein, int fat, int carbo) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.description=description;
        this.protein=protein;
        this.carbo=carbo;
        this.fat=fat;
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

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbo() {
        return carbo;
    }

    public void setCarbo(int carbo) {
        this.carbo = carbo;
    }
}
