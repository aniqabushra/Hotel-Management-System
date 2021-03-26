package hotel;

import customer.Customer;
import room.Room;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HotelImpl implements Hotel {
//    private ArrayList<Customer> customers = new ArrayList<>();
//    HashMap<Customer, String> people = new HashMap<Customer, String>();

    String path = "data\\Customers.txt";
    private Room[][] hotel;
    private Customer customer;

    public HotelImpl() throws IOException {
        hotel = new Room[X][Y];
        Arrays.stream(hotel).forEach(cell -> Arrays.fill(cell, new Room(false, null)));
        fillDummyHotel();
    }

    public void fillDummyHotel() throws IOException {
        List<String> customersCheckedIn = Files.readAllLines(Paths.get(path));

        for (String s : customersCheckedIn) {

            Customer exist = new Customer(
                    s.split(",")[0],
                    Integer.parseInt(s.split(",")[1]),
                    Integer.parseInt(s.split(",")[2]),
                    Integer.parseInt(s.split(",")[3])
            );

            hotel[exist.getX()][exist.getY()] = new Room(true, exist);
        }

        int customersInHotel = 5;

        Random rand = new Random();

        for (int i = 0; i < customersInHotel; i++) {
            int randX = rand.nextInt(X);
            int randY = rand.nextInt(Y);

            Customer dummy = new Customer("DUMMY CUSTOMER", 10, randX, randY);// dummy customer information
            hotel[dummy.getX()][dummy.getY()] = new Room(true, customer);
        }
    }

    @Override
    public void addHotelCustomer(Customer customer) {
//        hotel[customer.getX()][customer.getY()] = new Room(true, customer);

        if (validateRoom(customer)) {
            hotel[customer.getX()][customer.getY()] = new Room(true, customer);

        } else if (!validateRoom(customer)) {
            System.out.println("Already booked");
        }
    }

    private boolean validateRoom(Customer customer) {
        for (int row = 0; row < hotel.length; row++) {
            for (int col = 0; col < hotel[row].length; col++) {
                if (!hotel[customer.getX()][customer.getY()].isHasCustomer()) {
                    System.out.println("You Successfully booked the room.");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void showHotel() {
        // show 2d list
        System.out.println("--------    H O T E L   --------");

        for (Room[] row : hotel) {
            for (Room el : row) {

                if (el.isHasCustomer()) {
                    System.out.printf("| %c ", Room.getBookedSymbol());
                } else {
                    System.out.printf("| %c ", Room.getRoomSymbol());
                }
            }
            System.out.println("|");
        }
    }

    @Override
    public Room getRoom(int x, int y) {
        return hotel[x][y];
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


    public void removeCustomer(Customer customer) throws IOException {
        Files.readAllLines(Paths.get(path));
        hotel[customer.getX()][customer.getY()] = new Room(false, null);
        System.out.println("You successfully remove a customer from a hotel");
    }
}
