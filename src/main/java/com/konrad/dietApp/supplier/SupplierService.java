package com.konrad.dietApp.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }

   /* public List<Supplier> getSupplierByProductId(int id){
        return supplierRepository.findSupplierByProductId(id);
    }*/

    public Supplier getSupplierById(int id){
        return supplierRepository.findSupplierById(id);
    }
}
