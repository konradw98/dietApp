package com.konrad.dietApp.SupplierToProduct;

import com.konrad.dietApp.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierToProductService {
    @Autowired
    SupplierToProductRepository supplierToProductRepository;

  public  List<Integer> findSuppliersByProductId(int id){
      return  supplierToProductRepository.findSuppliersByProductId(id);
    }

}
