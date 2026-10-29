package com.402306602.smartpantry_manager.models;

public class Recipe {
    private String name;
    private String ingredient;
    private String instructions;

    public Recipe(String name, String ingredients, String instructions) {
        this.name = name;
        this.ingredient = ingredients;
        this.instructions = instructions;
    }

    public String getName() {return name;}
    public String getIngredient() {return ingredient}
    public String getInstructions() {return instructions}
}
