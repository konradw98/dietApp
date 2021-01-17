package com.konrad.dietApp.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    List<Supplier> findSupplierByProductId(int productId);
}
