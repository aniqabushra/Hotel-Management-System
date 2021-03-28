import hotel.Hotel;
import hotel.HotelImpl;

import java.io.IOException;

public class Controller {
    private final View view;
   private HotelImpl hotel = new HotelImpl();

    public Controller(View view) throws IOException {
        this.view = view;
    }
    public void run() throws IOException {
        //TODO loop through menu options;
        MenuOptions option;
        do {
            option = view.displayMenuOptionAndSelect();
            //Todo us a method instead of sout
            view.printMessage(option.getTitle());
            switch (option) {
                case EXIT:
                    view.printMessage("Good Bye :)");
                    break;
                case CHECKIN:
                    checkIn();
                    break;
                case CHECKOUT:
                    checkOut();
                    break;
                case UPDATE:
                    updateRoom();
                    break;
                case VIEW_ROOMS:
                    ViewRooms();
                    break;
            }


        } while (option != MenuOptions.EXIT);


    }

    private void updateRoom() {
       view.updateRoom(hotel);
    }

    private void checkOut() throws IOException {
        view.checkOutGuest(hotel);
    }

    private void ViewRooms() {
    hotel.showHotel();
    }

    private void checkIn() {
        view.bookRoom(hotel);
//        System.out.println("room is booked");
    }
}
