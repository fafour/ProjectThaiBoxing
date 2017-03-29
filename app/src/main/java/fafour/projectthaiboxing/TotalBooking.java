package fafour.projectthaiboxing;

/**
 * Created by Fafour on 6/2/2560.
 */

public class TotalBooking {
    public static  int totalBuyItem(){
        int total = 0;
        for(int count=0; count < MainActivity.booking.size(); count++){
            total = total + (MainActivity.booking.get(count).bookingNum *
                    MainActivity.booking.get(count).bookingPrice );
        }

        return total;
    }
    public static  int totalVat(){
        int total = 0;
        for(int count=0; count < MainActivity.booking.size(); count++){
            total = total + (((MainActivity.booking.get(count).bookingNum *
                    MainActivity.booking.get(count).bookingPrice)*7)/100 );
        }

        return total;
    }
}
