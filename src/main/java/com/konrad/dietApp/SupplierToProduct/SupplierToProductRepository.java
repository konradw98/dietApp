package com.konrad.dietApp.SupplierToProduct;

import com.konrad.dietApp.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierToProductRepository extends JpaRepository<Supplier,Integer> {

   // @Query(value="SELECT * FROM meal WHERE meal.email=?1", nativeQuery = true)
    @Query(value="SELECT supplier_id FROM supplier_to_product WHERE supplier_to_product.product_id=?1",nativeQuery = true)
    public List<Integer> findSuppliersByProductId(int id);
}
