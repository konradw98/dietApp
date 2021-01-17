package com.konrad.dietApp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String viewAllProducts(Model model){
        List<Product> listProducts=productService.getAllProducts();
        model.addAttribute("listProducts",listProducts);
        return "products";
    }

    @RequestMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") int id){
        productService.delete(id);
        return "redirect:/products";
    }
}
