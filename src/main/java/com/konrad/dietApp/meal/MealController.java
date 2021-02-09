package com.konrad.dietApp.meal;

import com.konrad.dietApp.product.Product;
import com.konrad.dietApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class MealController {

    @Autowired
    MealService mealService;

    @Autowired
   UserService userService;

    @RequestMapping("/meals")
    public String viewAllMeals(Model model){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDate date=LocalDate.now();
        List<Meal> listMeals=mealService.findAllMealsByEmailAndDate(email,date);
        model.addAttribute("listMeals",listMeals);
        Float sumKcal=mealService.getSumKcalByEmailAndDate(email, date); //Float zamiast float, zeby obslugiwas "null"

        model.addAttribute("sumKcal",sumKcal);
         String newPortion="2";
       model.addAttribute("newPortion",newPortion);

       // System.out.println("ID uzytkownika o mailu "+email+" to "+userService.findIdByEmail(email));
        //String name = SecurityContextHolder.getContext().getAuthentication().getName();
       // LocalDate date=LocalDate.now();
        //System.out.println("/meals aktualny uzytkownik"+name);
       // System.out.println("/meals aktualna data"+date);
       // System.out.println("lista posilkow"+listMeals);
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
        meal.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        meal.setDate(LocalDate.now());
        meal.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
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

    @RequestMapping("/allMeals")
    public String viewAllMealsByUser(Model model){
        List<Meal> listMeals=mealService.findAllMealsByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("listMeals",listMeals);

        return "all_meals";
    }
    @GetMapping("/allMeals/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=meals_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Meal> listMeals=mealService.findAllMealsByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        MealExcelExporter excelExporter= new MealExcelExporter(listMeals);

        excelExporter.export(response);
    }

}
