package com.konrad.dietApp.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;

public interface MealRepository  extends JpaRepository<Meal, Integer> {
    @Query(value = "SELECT SUM(meal.kcal) FROM meal", nativeQuery = true)
    float getSumKcal();

    //UPDATE diet4.meal SET diet4.meal.portions=9 WHERE diet4.meal.id=4
    @Modifying
    @Transactional
    @Query(value="UPDATE meal SET meal.portions=meal.portions*?2, meal.kcal=meal.kcal*?2, meal.protein=meal.protein*?2, meal.carbo=meal.carbo*?2, meal.fat=meal.fat*?2 WHERE meal.id=?1", nativeQuery = true)
    void updatePortionById(int id, int newPortion);
}
