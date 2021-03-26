package hotel;

import customer.Customer;
import room.Room;

public interface Hotel {
    void showHotel();
    void addHotelCustomer(Customer customer);
    Room getRoom(int x, int y);
    int X = 8;
    int Y = 8;
}
