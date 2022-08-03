package com.example.puzzle.classes;

public class AnimalsPuzzleGame {
    int animal_img_game;
    String animal_qes_game;
    boolean flag;

    public AnimalsPuzzleGame(int animal_img_game, String animal_qes_game, boolean flag) {
        this.animal_img_game = animal_img_game;
        this.animal_qes_game = animal_qes_game;
        this.flag = flag;
    }

    public int getAnimal_img_game() {
        return animal_img_game;
    }

    public void setAnimal_img_game(int animal_img_game) {
        this.animal_img_game = animal_img_game;
    }

    public String getAnimal_qes_game() {
        return animal_qes_game;
    }

    public void setAnimal_qes_game(String animal_qes_game) {
        this.animal_qes_game = animal_qes_game;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
