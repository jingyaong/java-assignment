package assignment;
import java.util.List;

public class Order {
    private List<FoodAss> foodItems;
    private List<baverage> beverageItems;
    private double totalPrice;

    public List<FoodAss> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodAss> foodItems) {
        this.foodItems = foodItems;
    }

    public List<baverage> getBeverageItems() {
        return beverageItems;
    }

    public void setBeverageItems(List<baverage> beverageItems) {
        this.beverageItems = beverageItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
