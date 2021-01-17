package com.konrad.dietApp.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface MealRepository  extends JpaRepository<Meal, Integer> {

}
