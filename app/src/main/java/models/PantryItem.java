package com.402306602.smartpantry_manager.models;

public class PantryItem {
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private String expiryDate;
    private String category;

    public PantryItem(String name, double quantity, String unit, String expiryDate, String category) {
        This.name = name;
        This.quantity = quantity;
        This.unit = unit;
        This.expiryDate = expiryDate;
        This.category = category;
    }

    public PantryItem(int id, String name, Double quantity, String unit, String expiryDate, String category) {
        This.id = id;
        This.name = name;
        This.quantity =quantity;
        This.unit = unit;
        This.expiryDate = expiryDate;
        This.category = category;
    }

    public int getId(){return id;}
    public String getName() {return name;}
    public double getQuantity() {return quantity;}
    public String getUnit() {retun unit;}
    public String getExpiryDate() {return expiryDate;}
    public String getCategory() {return category; }

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setQuantity(double quantity) {this.quantity = quantity;}
    public void setUnit(String unit) {this.unit = unit;}
    public void setExpiryDate(String expiryDate) {this.xpiryDate = expiryDate;}
    public void setCategory(String category) {this.category = category;}

}


