/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author gajone
 */
public class FoodAss {

    private String foodID;
    String food;
    double price;
    int quantity;

    public FoodAss() {

    }

    public FoodAss(String foodId, String food, double price, int quantity) {
        int id = 1000;
        this.foodID = "f" + Integer.toString(id);
        this.foodID = foodId;
        this.food = food;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFood() {
        return food;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
