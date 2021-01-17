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

    public void deleteMeal(int id){
        mealRepository.deleteById(id);
    }
}
