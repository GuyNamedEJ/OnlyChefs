package com.example.onlychefs;

import java.io.Serializable;

public class Recipe implements Serializable {
    public Recipe()
    {
        name = "---";
        id = "---";
        ingredients = "---";
        instructions = "---";
    }

    public Recipe(String name, String id, String ingredients, String instructions, String thumbnail)
    {
        this.name = name;
        this.id = id;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
    }

    private String name;
    private String id;
    private String ingredients;
    private String instructions;
    private String thumbnail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
