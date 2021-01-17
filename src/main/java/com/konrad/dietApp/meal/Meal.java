package com.konrad.dietApp.meal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meal {
    @Id
    private int id;
    private String name;
    private String kcal;

    public Meal(){};
    public Meal(int id, String name, String kcal) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }
}
