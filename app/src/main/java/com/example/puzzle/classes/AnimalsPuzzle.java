package com.example.puzzle.classes;

public class AnimalsPuzzle {
    public int animal_img;
    public String animal_name;

    public AnimalsPuzzle(int animal_img, String animal_name) {
        this.animal_img = animal_img;
        this.animal_name = animal_name;
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

}
