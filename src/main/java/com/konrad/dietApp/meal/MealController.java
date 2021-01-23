package com.konrad.dietApp.meal;

import com.konrad.dietApp.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @RequestMapping("/meals")
    public String viewAllMeals(Model model){
        List<Meal> listMeals=mealService.findAllMeals();
        model.addAttribute("listMeals",listMeals);
        float sumKcal=mealService.getSumKcal();
        model.addAttribute("sumKcal",sumKcal);
        return "meals";
    }

    @RequestMapping("/meals/delete/{id}")
    public String deleteMeal(@PathVariable(name="id") int id){
        mealService.deleteMeal(id);
        return "redirect:/meals";
    }
    @RequestMapping("/new_meal")
    public String showNewMealForm(Model model){
        Meal meal= new Meal();
        model.addAttribute("meal", meal);

        return "new_meal";
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String saveProduct(@ModelAttribute("meal") Meal meal){
        mealService.save(meal);

        return "redirect:/meals";
    }



}
