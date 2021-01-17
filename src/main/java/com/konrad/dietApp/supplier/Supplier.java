package com.konrad.dietApp.supplier;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {
    @Id
    private int supplierId;
    private String supplierName;
    private String supplierAdress;
    private String opinion;

    public Supplier(){}

    public Supplier(int supplierId, String supplierName, String supplierAdress, String opinion) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierAdress = supplierAdress;
        this.opinion = opinion;
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
