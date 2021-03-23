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
    }

//    public ArrayList<Customer> getCustomers() {
//        return customers;
//    }

    public Room[][] getHotel() {
        return hotel;
    }

    @Override
    public void fillHotel(Customer customer) {

        int customersInHotel = 3;

        Random rand = new Random();

//        for (int i = 0; i < customersInHotel; i ++) {
            customers.add(customer); // dummy customer information
            hotel[customer.getX()][customer.getY()] = new Room(true, customers);
//        }
    }


    @Override
    public void showHotel() {
        // show 2d list
        System.out.println("--------    H O T E L   --------");

        for(Room[] row : hotel) {
            for(Room el : row) {
                System.out.printf("| %c ", el.getRoomSymbol());
            }
            System.out.println("|");
        }
    }

    @Override
    public String toString() {
        return "toString for HotelImpl{" +
                "customers=" + customers +
                ", hotel=" + Arrays.toString(hotel) +
                '}';
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
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
}
