package room;

import customer.Customer;

import java.util.ArrayList;

public class Room {

    private boolean hasCustomer;
    private static final char roomSymbol = '_';
    private static final char bookedSymbol = 'B';
    private int y;
    private int x;

//    public Room(boolean hasCustomer, ArrayList<Customer> customerDetails) {
//        this.hasCustomer = hasCustomer;
//        this.customerDetails = customerDetails;
//    }

//    public ArrayList<Customer> getCustomerDetails() {
//        return customerDetails;
//    }
//
//    public void setCustomerDetails(ArrayList<Customer> customerDetails) {
//        this.customerDetails = customerDetails;
//    }

    private Customer customer;


    public Room(boolean hasCustomer, Customer customer) {
        this.hasCustomer = hasCustomer;
        this.customer = customer;
    }

    public boolean isHasCustomer() {
        return hasCustomer;
    }

    public static char getRoomSymbol() {
        return roomSymbol;
    }

    public static char getBookedSymbol() {
        return bookedSymbol;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
