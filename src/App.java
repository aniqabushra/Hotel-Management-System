import hotel.HotelImpl;

public class App {
    public static void main(String[] args) {

        System.out.println("test code");
        // this is sam
        test();
    }

    // test method for showing hotel
    public static void test() {
        HotelImpl hotel = new HotelImpl();
        hotel.showHotel();
    }

}
