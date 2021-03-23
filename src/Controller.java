import hotel.Hotel;
import hotel.HotelImpl;

import java.io.IOException;

public class Controller {
    private final View view;
   private HotelImpl hotel;

    public Controller(View view) {
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
                    //displayBySections();
                    checkIn();
                    break;
                case CHECKOUT:
                    //addPanel();
                    break;
                case UPDATE:
                    //updatePanel();
                    break;
                case VIEW_ROOMS:
                    ViewRooms();
                    break;
            }


        } while (option != MenuOptions.EXIT);


    }

    private void ViewRooms() {
        hotel.showHotel();
    }

    private void checkIn() {
        HotelImpl hotel = view.bookRoom();
        System.out.println("room is booked");
    }
}
