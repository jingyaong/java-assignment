package assignment;

public class NonVegan extends FoodAss {
    private String foodDetail;

    public NonVegan(String foodId, String food, double price ,String foodDetail,int quantity) {
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
        return "Non vegann Food ID: " + getFoodID() + "\nFood: " + getFood() + "\nPrice: " + getPrice() + "\nFood Detail: " + foodDetail+ "\nquantity:"+getquantity() ;
    }
}