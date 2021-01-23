package com.konrad.dietApp.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Id;

public interface MealRepository  extends JpaRepository<Meal, Integer> {
    @Query(value = "SELECT SUM(meal.kcal) FROM meal", nativeQuery = true)
    float getSumKcal();

}
