package room;

import customer.Customer;

import java.util.ArrayList;

public class Room {

    private boolean hasCustomer;
    private ArrayList<Customer> customerDetails;
    private int y;
    private int x;
    private static final char roomSymbol = 'r';

    public Room(boolean hasCustomer, ArrayList<Customer> customerDetails) {
        this.hasCustomer = hasCustomer;
        this.customerDetails = customerDetails;
    }

    public static char getRoomSymbol() {
        return roomSymbol;
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

    public boolean isHasCustomer() {
        return hasCustomer;
    }

    public void setHasCustomer(boolean hasCustomer) {
        this.hasCustomer = hasCustomer;
    }
}