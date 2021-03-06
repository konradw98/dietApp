package com.konrad.dietApp.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {
    @Autowired
    MealRepository mealRepository;

    public void save(Meal meal){
        mealRepository.save(meal);
    }

    public List<Meal> findAllMeals(){
        return mealRepository.findAll();

    }
    public List<Meal> findAllMealsByEmail(String email){
        return mealRepository.findAllByEmail(email);

    }
   public List<Meal> findAllMealsByEmailAndDate(String email, LocalDate date){
        return  mealRepository.findAllByEmailAndDate(email,date);
   }


    public void deleteMealById(int id){
        mealRepository.deleteById(id);
    }
    public void deleteMealByObject(Meal meal){
        mealRepository.delete(meal);
    }


    public float getSumKcal(){
        return mealRepository.getSumKcal();
    }

    public Float getSumKcalByEmailAndDate(String email,LocalDate date){
        return mealRepository.getSumKcalByEmailAndDate(email, date);
    }

    public Integer getSumProteinByEmailAndDate(String email,LocalDate date){
        return mealRepository.getSumProteinByEmailAndDate(email, date);
    }
    public Integer getSumCarboByEmailAndDate(String email,LocalDate date){
        return mealRepository.getSumCarboByEmailAndDate(email, date);
    }
    public Integer getSumFatByEmailAndDate(String email,LocalDate date){
        return mealRepository.getSumFatByEmailAndDate(email, date);
    }
    public void updatePortionById(int id, int newPortion){
        mealRepository.updatePortionById(id, newPortion);
    }

    public void updatePortionByIdDown(int id, double newPortion){
        mealRepository.updatePortionByIdDown(id, newPortion);
    }
    public Meal getMealById(int id){
      return  mealRepository.getMealById(id);
    }

}
