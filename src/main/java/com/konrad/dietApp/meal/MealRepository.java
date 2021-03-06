package com.konrad.dietApp.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

public interface MealRepository  extends JpaRepository<Meal, Integer> {
    @Query(value = "SELECT SUM(meal.kcal) FROM meal", nativeQuery = true)
    float getSumKcal();

    @Query(value = "SELECT SUM(meal.kcal) FROM meal WHERE meal.email=?1 AND meal.date=?2", nativeQuery = true)
    Float getSumKcalByEmailAndDate(String email, LocalDate date);

    @Query(value = "SELECT SUM(meal.protein) FROM meal WHERE meal.email=?1 AND meal.date=?2", nativeQuery = true)
    Integer getSumProteinByEmailAndDate(String email, LocalDate date);

    @Query(value = "SELECT SUM(meal.carbo) FROM meal WHERE meal.email=?1 AND meal.date=?2", nativeQuery = true)
    Integer getSumCarboByEmailAndDate(String email, LocalDate date);

    @Query(value = "SELECT SUM(meal.fat) FROM meal WHERE meal.email=?1 AND meal.date=?2", nativeQuery = true)
    Integer getSumFatByEmailAndDate(String email, LocalDate date);
    //UPDATE diet4.meal SET diet4.meal.portions=9 WHERE diet4.meal.id=4
    @Modifying
    @Transactional
    @Query(value="UPDATE meal SET meal.portions=meal.portions*?2, meal.kcal=meal.kcal*?2, meal.protein=meal.protein*?2, meal.carbo=meal.carbo*?2, meal.fat=meal.fat*?2, meal.operation='UPDATE' WHERE meal.id=?1", nativeQuery = true)
    void updatePortionById(int id, int newPortion);

    @Modifying
    @Transactional
    @Query(value="UPDATE meal SET meal.portions=meal.portions*?2, meal.kcal=meal.kcal*?2, meal.protein=meal.protein*?2, meal.carbo=meal.carbo*?2, meal.fat=meal.fat*?2, meal.operation='UPDATE' WHERE meal.id=?1", nativeQuery = true)
    void updatePortionByIdDown(int id, double newPortion);

    @Query(value="SELECT * FROM meal WHERE meal.email=?1", nativeQuery = true)
    List<Meal> findAllByEmail(String email);

    @Query(value="SELECT * FROM meal WHERE meal.email=?1 AND meal.date=?2",nativeQuery = true)
    List<Meal> findAllByEmailAndDate(String email, LocalDate date);

    @Query(value="SELECT * FROM meal WHERE meal.id=?1",nativeQuery = true)
    Meal getMealById(int email);
}
