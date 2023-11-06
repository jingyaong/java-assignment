/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author wongy
 */
public class baverage extends FoodAss {

    private String baverageId;
    private String baverage;
    private double price1;
    private int quantity;

    public baverage() {

    }

    public baverage(String baverageId, String baverage, double price, int quantity) {

        this.baverageId = baverageId;
        this.baverage = baverage;
        this.price1 = price;
        this.quantity = quantity;
    }


    public void setbaverage(String baverage) {
        this.baverage = baverage;
    }

    public String getbaverage() {
        return baverage;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    @Override
    public int getquantity() {
        return quantity;
    }

    @Override
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public void setbaverageId(String baverageId) {
        this.baverageId = baverageId;
    }

    public String getbaverageId() {
        return baverageId;
    }

    public double getPrice1() {
        return price1;
    }

    @Override
    public String toString() {
        return baverageId + "\t\t"
                + baverage + "\t\t"
                + price1 + "\t\t"
                + quantity + "\t\t"
                + price1;
    }

    int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setQuantity(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
