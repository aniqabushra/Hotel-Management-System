package hotel;

import customer.Customer;
import room.Room;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

//        for (String s : customersCheckedIn) {
//
//            Customer exist = new Customer(
//                    s.split(",")[0],
//                    Integer.parseInt(s.split(",")[1]),
//                    Integer.parseInt(s.split(",")[2]),
//                    Integer.parseInt(s.split(",")[3])
//            );
//
//            hotel[exist.getX()][exist.getY()] = new Room(true, exist);
//        }

        int customersInHotel = 5;

        Random rand = new Random();

        for (int i = 0; i < customersInHotel; i++) {
            int randX = rand.nextInt(X);
            int randY = rand.nextInt(Y);

            Customer dummy = new Customer(i, "DUMMY CUSTOMER", 10, randX, randY);// dummy customer information
            hotel[dummy.getX()][dummy.getY()] = new Room(true, customer);
        }
    }

    @Override
    public void addHotelCustomer(Customer customer) {
//        hotel[customer.getX()][customer.getY()] = new Room(true, customer);

        if (validateRoom(customer)) {
            List<Customer> all = findAllCustomers();
            customer.setId(getNextId(all));
            all.add(customer);
            writeAll(all);
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
    //create a find all method first

    public List<Customer> findAllCustomers() {
        ArrayList<Customer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(fields[0]));
                    customer.setName(fields[1]);
                    customer.setAge(Integer.parseInt(fields[2]));
                    customer.setX(Integer.parseInt(fields[3]));
                    customer.setY(Integer.parseInt(fields[4]));
                    result.add(customer);
                }

            }


        } catch (Exception e) {
            System.out.println("An Error Occurred :(" + e);
        }
        return result;
    }


    public void removeCustomer(int customerId) throws IOException {

//        Files.readAllLines(Paths.get(path));
//        List<String> exits = Files.readAllLines(Paths.get(path));
//        System.out.println("ALL PEOPLE IN FILE" + exits);
//
//        Iterator<String> it = exits.iterator();
//
//        try {
//            while (it.hasNext()) {
//                System.out.println(Arrays.toString(it.next().split(",")));
//                int existingX = Integer.parseInt(it.next().split(",")[2]);
//                int existingY = Integer.parseInt(it.next().split(",")[3]);
//
//                if (existingX == customer.getX() && existingY == customer.getY()) {
//                    it.remove();
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("An error occurred. " + e);
//        }
//        // append the rest of the list to the file overriding it
//        Files.write(Paths.get(path), exits);
        try {
            List<Customer> all = findAllCustomers();
            for (int index = 0; index < all.size(); index++) {
                // if(all.get(index).getX() == customer.getX() && all.get(index).getY()== customer.getY()){
                if (all.get(index).getId() == customerId) {
                    hotel[all.get(index).getX()][all.get(index).getY()] = new Room(false, null);
                    all.remove(index);
                    writeAll(all);
                    System.out.println("You successfully remove a customer from a hotel");
                }
            }

        } catch (Exception e) {
            System.out.println("An Error Occurred");
        }
    }

    private void writeAll(List<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(path)) {
            for (Customer customer : customers) {
                String searalize = String.format("%s,%s,%s,%s,%s",
                        customer.getId(), customer.getName(), customer.getAge(), customer.getX(), customer.getY());
                writer.println(searalize);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private int getNextId(List<Customer> customers) {
        int nextId = 0;
        for (Customer c : customers) {
            nextId = Math.max(nextId, c.getId());
        }
        return nextId + 1;
    }
}
