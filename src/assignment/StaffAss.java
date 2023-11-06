package assignment;

public class StaffAss extends person {
    private String staffID;
    private String phone;

    public StaffAss(String staffID, String name, String phone, String password) {
        super(name, password);
        this.staffID = staffID;
        this.phone = phone;
    }
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
      public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public void displayInfo() {
        System.out.println("Staff ID: " + staffID);
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + phone);
    }
}