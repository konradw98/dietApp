package com.konrad.dietApp.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    @Autowired
    MealRepository mealRepository;

    public void save(Meal meal){
        mealRepository.save(meal);
    }

    List<Meal> findAllMeals(){
        return mealRepository.findAll();

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

    public void updatePortionById(int id, int newPortion){
        mealRepository.updatePortionById(id, newPortion);
    }

}
