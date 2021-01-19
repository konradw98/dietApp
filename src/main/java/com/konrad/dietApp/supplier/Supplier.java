package com.konrad.dietApp.supplier;

import com.konrad.dietApp.product.Product;

import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    private int id;
    private String name;
    private String adress;
    private String opinion;

    @ManyToOne
    Product product;

    public Supplier(){}

    public Supplier(int id, String name, String adress, String opinion, Product product) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.opinion = opinion;
        this.product=product;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }


}
