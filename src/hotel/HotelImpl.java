package hotel;

import customer.Customer;
import room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HotelImpl implements Hotel {
    private ArrayList<Customer> customers = new ArrayList<>();
    private Room[][] hotel;

    public HotelImpl() {
        hotel = new Room[X][Y];
        Arrays.stream(hotel).forEach(cell -> Arrays.fill(cell, new Room(false, null)));

        fillHotel();
    }

    @Override
    public void fillHotel() {
        int randX;
        int randY;
        int customersInHotel = 3;

        Random rand = new Random();

        for (int i = 0; i < customersInHotel; i ++) {
            randX = rand.nextInt(X);
            randY = rand.nextInt(Y);

            customers.add(new Customer("Johnny", 99)); // dummy customer information
            hotel[randX][randY] = new Room(true, customers);
        }
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
}
