package com.konrad.dietApp.supplier;

import com.konrad.dietApp.SupplierToProduct.SupplierToProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierToProductService supplierToProductService;

    @RequestMapping("/suppliers")
    public String viewAllSuppliers(Model model){
        List<Supplier> listSuppliers=supplierService.getAllSupplier();
        model.addAttribute("listSuppliers",listSuppliers);
        return "suppliers";
    }

    @RequestMapping("products/{productId}/suppliers")
    public ModelAndView showSupplierByProductId(@PathVariable(name="productId") int productId){
       // List<Supplier> suppliersList=supplierService.getSupplierByProductId(productId);
        List<Integer> suppliersIdList=supplierToProductService.findSuppliersByProductId(productId);
        List<Supplier> suppliersList=new ArrayList<>();
        for(int i=0; i<suppliersIdList.size(); i++){
            suppliersList.add(supplierService.getSupplierById(suppliersIdList.get(i)));
        }
        ModelAndView mv= new ModelAndView("suppliers");
        mv.addObject("listSuppliers",suppliersList);

        System.out.println(suppliersList);
        return mv;
    }
}
