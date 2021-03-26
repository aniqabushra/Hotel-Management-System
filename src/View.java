import customer.Customer;
import hotel.HotelImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class View {


    private final Scanner console = new Scanner(System.in);
    static Path path = Paths.get("C:\\Users\\12409\\Desktop\\Week05ProjectPyramid\\data\\Customers.csv");
    static String pa = "C:\\Users\\12409\\Desktop\\Week05ProjectPyramid\\data\\Customers.txt";


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

    public void bookRoom(HotelImpl hotel) {
        Customer customer = getCustomerData("Please Enter customer name to book", "Please enter customer age to book");
        hotel.setCustomer(customer);
        hotel.addHotelCustomer(customer);
        writeCustomerToFile(customer);
    }

    public void writeCustomerToFile(Customer customer) {
        String name = customer.getName();
        int age = customer.getAge();
        int x = customer.getX();
        int y = customer.getY();
        List<String> lines = new ArrayList<>();
        lines.add(name);
        lines.add(String.valueOf(age));
        lines.add(String.valueOf(x));
        lines.add(String.valueOf(y));
        writeToAFile(lines);

    }

    public static void writeToAFile(List<String> lines) {
        try {
            BufferedWriter writter = new BufferedWriter(new FileWriter(pa,true));

            for (String l : lines) {
                String data = l + ",";
                writter.append(data);
            }
            writter.newLine();
            writter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkOutGuest(HotelImpl hotel) {
        Customer customer = getCustomerData("Please Enter customer name to remove", "Please enter customer age to remove");
        hotel.removeCustomer(customer);
    }

    private Customer getCustomerData(String s, String s2) {
        String name = readRequiredString(s);
        int age = readInt(s2);
        int x = readInt("Please enter X axis", 0, 8);
        int y = readInt("Please enter Y axis.", 0, 8);
        return new Customer(name, age, x, y);
    }

    public void updateRoom(HotelImpl hotel) {
        Customer updateCustomer = getCustomerData("Please Enter customer name to update", "Please enter customer age to update");


    }
}
