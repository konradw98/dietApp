package com.konrad.dietApp.meal;

import com.konrad.dietApp.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
      String newPortion="5";
       model.addAttribute("newPortion",newPortion);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("/meals aktualny uzytkownik"+name);
        return "meals";
    }

    @RequestMapping(value = "/meals/delete/{id}")
    public String deleteMeal(@PathVariable(name="id") int id){
        mealService.deleteMealById(id);
        return "redirect:/meals";
    }
    @RequestMapping("/new_meal")
    public String showNewMealForm(Model model){
        Meal meal= new Meal();
        model.addAttribute("meal", meal);

        return "new_meal";
    }
    @RequestMapping(value="/delete")
    public String deleteProduct(@ModelAttribute("meal") Meal meal){
        mealService.deleteMealByObject(meal);
        System.out.println(meal.getId()+meal.getName());

        return "redirect:/meals";
    }

    @RequestMapping(value="/save")
    public String saveProduct(@ModelAttribute("meal") Meal meal){
        mealService.save(meal);

        return "redirect:/meals";
    }
   // @RequestMapping(value="updatePortions")
  //  public String updatePortions(@ModelAttribute("meal") Meal meal,)
//meals/upatePortion/'+${meal.getId()}}
//meals/updatePortion/'+${meal.getId()}}
   @RequestMapping(value = "/meals/updatePortion/{id}")
   public String updatePortion(@PathVariable(name="id") int id){// @ModelAttribute("newPortion") String newPortion){

       //System.out.println("id:"+id+" newPortion"+newPortion);
       mealService.updatePortionById(id, 2);


       return "redirect:/meals";
   }

}
