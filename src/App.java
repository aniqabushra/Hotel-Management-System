import hotel.HotelImpl;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        test();
        View view = new View();
        Controller controller = new Controller(view);
        controller.run();
    }

    // test method for showing hotel
    public static void test() {
        HotelImpl hotel = new HotelImpl();
        hotel.showHotel();
    }


}
