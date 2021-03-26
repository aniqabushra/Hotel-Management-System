import customer.Customer;
import hotel.HotelImpl;
import room.Room;

import java.util.Scanner;


public class View {

    private final Scanner console = new Scanner(System.in);

    public MenuOptions displayMenuOptionAndSelect() {
        printHeader("Main Menu");
        MenuOptions[] values = MenuOptions.values();
        for (int index = 0; index < values.length; index++) {
            System.out.printf("%s. %s %n", index, values[index].getTitle());
        }
        int selection = readInt("\nSelect [0-4] \n", 0, 5);
        return values[selection];
    }


    public void printHeader(String message) {
        printMessage(message);
        System.out.println("*".repeat(message.length()));
        System.out.println();
    }

    public void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    private int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;
        do {
            String value = readRequiredString(prompt);
            //it can throw NumberFormatException
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number");
            }

        } while (!isValid);
        return result;
    }

    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Please Select from %s-%s%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt).trim();
            if (result.length() == 0) {
                System.out.println("value is required");
            }
        } while (result.length() == 0);
        return result;
    }

    public boolean isRoomAvailable(Room room) {
        return !room.isHasCustomer();
    }

    public HotelImpl bookRoom(HotelImpl hotel) {
        String name = readRequiredString("Please Enter customer name,");
        int age = readInt("Please enter customer age.");
        int x = readInt("Please enter X asis", 0, 8);
        int y = readInt("please enter Y axis.", 0, 8);

        if (hotel.getRoom(x, y).isHasCustomer()) {
            System.out.println("Room already taken!");
        }

        Customer customer = new Customer(name, age, x, y);
        hotel.setCustomer(customer);
        hotel.addHotelCustomer(customer);

        return hotel;
    }
}
