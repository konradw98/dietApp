package com.konrad.dietApp.supplier;

import com.konrad.dietApp.product.Product;

import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    private int supplierId;
    private String supplierName;
    private String supplierAdress;
    private String opinion;

    @ManyToOne
    Product product;

    public Supplier(){}

    public Supplier(int supplierId, String supplierName, String supplierAdress, String opinion, Product product) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierAdress = supplierAdress;
        this.opinion = opinion;
        this.product=product;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAdress() {
        return supplierAdress;
    }

    public void setSupplierAdress(String supplierAdress) {
        this.supplierAdress = supplierAdress;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }


}
