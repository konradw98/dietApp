package com.konrad.dietApp.SupplierToProduct;

import com.konrad.dietApp.product.Product;
import com.konrad.dietApp.supplier.Supplier;

import javax.persistence.*;

@Entity
public class SupplierToProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    Product product;

    @ManyToOne
    Supplier supplier;

}
