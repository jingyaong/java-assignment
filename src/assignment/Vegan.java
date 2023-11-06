package assignment;

public class Vegan extends FoodAss {
    private String foodDetail;

    public Vegan(String foodId, String food, double price, String foodDetail,int quantity) {
        super(foodId, food, price,quantity);
        this.foodDetail = foodDetail;
    }

    public void setFoodDetail(String foodDetail) {
        this.foodDetail = foodDetail;
    }

    public String getFoodDetail() {
        return foodDetail;
    }

    @Override
    public String toString() {
        return "Vegan Food ID: " + getFoodID() + "\nFood: " + getFood() + "\nPrice: " + getPrice() + "\nFood Detail: " + foodDetail+ "\nquantity:"+getquantity() ;
    }
}