package com.konrad.dietApp.meal;

import com.konrad.dietApp.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @RequestMapping("/meals")
    public String viewAllMeals(Model model){
        List<Meal> listMeals=mealService.findAllMeals();
        model.addAttribute("listMeals",listMeals);
        return "meals";
    }

    @RequestMapping("/meals/delete/{id}")
    public String deleteMeal(@PathVariable(name="id") int id){
        mealService.deleteMeal(id);
        return "redirect:/meals";
    }

}
