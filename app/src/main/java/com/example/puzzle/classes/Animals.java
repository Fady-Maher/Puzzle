package com.example.puzzle.classes;

public class Animals {
    public int animal_img;
    public String animal_name,animal_count;

    public Animals(int animal_img, String animal_nam,String animal_count) {
        this.animal_img = animal_img;
        this.animal_name = animal_nam;
        this.animal_count = animal_count;

    }

    public int getAnimal_img() {
        return animal_img;
    }

    public void setAnimal_img(int animal_img) {
        this.animal_img = animal_img;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_count() {
        return animal_count;
    }

    public void setAnimal_count(String animal_count) {
        this.animal_count = animal_count;
    }
}
