package com.konrad.dietApp.product;

import com.konrad.dietApp.meal.Meal;
import com.konrad.dietApp.meal.MealService;
import com.konrad.dietApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    //int i=1;
    @Autowired
    ProductService productService;

    @Autowired
    MealService mealService;

    @Autowired
    UserService userService;

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

  @RequestMapping("/products/save/{id}")
    public String saveProduct(@PathVariable(name="id") int id){ // na razie pobieranie bedzie zadeklarowane
      //  i++;
        Optional<Product> product= productService.getProductById(id);
      Meal meal= new Meal();
      meal.setName(product.get().getName());
      meal.setCarbo(product.get().getCarbo());
      meal.setFat(product.get().getFat());
      meal.setProtein(product.get().getProtein());
      meal.setKcal(product.get().getKcal());
      meal.setProduct(product.get());
      meal.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
      meal.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
      meal.setDate(LocalDate.now());
      mealService.save(meal);
      System.out.println(meal.getId()+meal.getName());

        return "redirect:/meals";
    }
}
