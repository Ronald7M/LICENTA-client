package com.example.licenta.Entities;

public class Category extends Tables {
    private int idCategory;
    private String name;
    private String description;


    public Category(int id, String name, String description) {
        this.idCategory = id;
        this.name = name;
        this.description = description;
    }
    public Category(){

    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return name;
    }
}
