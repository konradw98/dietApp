package com.konrad.dietApp.meal;

import com.konrad.dietApp.product.Product;
import com.konrad.dietApp.user.User;
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
        Float sumKcal=mealService.getSumKcalByEmailAndDate(email, date);
        model.addAttribute("sumKcal",sumKcal);
        Integer sumProtein=mealService.getSumProteinByEmailAndDate(email, date);
        model.addAttribute("sumProtein",sumProtein);
        Integer sumCarbo=mealService.getSumCarboByEmailAndDate(email, date);
        model.addAttribute("sumCarbo",sumCarbo);
        Integer sumFat=mealService.getSumFatByEmailAndDate(email, date);
        model.addAttribute("sumFat",sumFat);

       User user=userService.findUserByEmail(email);

       model.addAttribute("user",user);

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

    @RequestMapping(value="/saveThread")
    public String saveProductThread(@ModelAttribute("meal") Meal meal){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Thread saveThread = new Thread(() ->{
                meal.setEmail(email);
        meal.setDate(LocalDate.now());
        meal.setUser(userService.findUserByEmail(email));
            try {

                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mealService.save(meal);
            });
        saveThread.start();
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

    @RequestMapping(value = "/meals/updatePortionDown/{id}")
    public String updatePortionDown(@PathVariable(name="id") int id){// @ModelAttribute("newPortion") String newPortion){

        //System.out.println("id:"+id+" newPortion"+newPortion);
        mealService.updatePortionByIdDown(id,0.5);


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
