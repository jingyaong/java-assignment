package assignment;
public class Customer extends person {
    private String customerID;
    private double balance;

    public Customer(String customerID, String password, String name, double balance) {
        super(name, password);
        this.customerID = customerID;
        this.balance = balance;
    }
    // Getter for customerID
    public String getCustomerID() {
        return customerID;
    }

    // Setter for customerID
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
     // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public void displayInfo() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + getName());
        System.out.printf("Balance: $%.2f\n",balance);
    }

 
}
