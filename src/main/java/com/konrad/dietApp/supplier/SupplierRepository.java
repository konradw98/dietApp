package com.konrad.dietApp.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

   // List<Supplier> findSupplierByProductId(int productId);

   /* @Query(value="SELECT supplier_id FROM supplier_to_product WHERE supplier_to_product.product_id=?1",nativeQuery = true)
    public List<Integer> findSuppliersByProductId(int id);*/

    @Query(value = "SELECT * FROM supplier WHERE id=?1 ", nativeQuery = true)
    public Supplier findSupplierById( int id);
}
