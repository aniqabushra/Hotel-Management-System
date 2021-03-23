package hotel;

import customer.Customer;
import room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class HotelImpl implements Hotel {
   private ArrayList<Customer> customers = new ArrayList<>();
   HashMap<Customer, String> people = new HashMap<Customer, String>();


    private Room[][] hotel;
    private Customer customer;

    public HotelImpl() {
        hotel = new Room[X][Y];
        Arrays.stream(hotel).forEach(cell -> Arrays.fill(cell, new Room(false, null)));

//        fillHotel(customer);
        fillDummyHotel();
    }

//    public ArrayList<Customer> getCustomers() {
//        return customers;
//    }

    public void fillDummyHotel() {
        int customersInHotel = 5;


        Random rand = new Random();

        for (int i = 0; i < customersInHotel; i++) {
            int randX = rand.nextInt(X);
            int randY = rand.nextInt(Y);

            Customer dummy = new Customer("DUMMY CUSTOMER", 10, randX, randY); // dummy customer information
            hotel[dummy.getX()][dummy.getY()] = new Room(true, dummy);
        }
    }

    @Override
    public void addHotelCustomer(Customer customer) {

        hotel[customer.getX()][customer.getY()] = new Room(true, customer);

    }


    @Override
    public void showHotel() {
        // show 2d list
        System.out.println("--------    H O T E L   --------");

        for (Room[] row : hotel) {
            for (Room el : row) {

                if (el.isHasCustomer()) {
                    System.out.printf("| %c ", el.getBookedSymbol());
                } else {
                    System.out.printf("| %c ", el.getRoomSymbol());
                }
            }
            System.out.println("|");
        }
    }

    public Room[][] getHotel() {
        return hotel;
    }


    public void setHotel(Room[][] hotel) {
        this.hotel = hotel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "HotelImpl{" +
                "hotel=" + Arrays.toString(hotel) +
                ", customer=" + customer +
                '}';
    }
}
