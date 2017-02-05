package fafour.projectthaiboxing;

/**
 * Created by Fafour on 6/2/2560.
 */

public class DataBooking {
    public String bookingName;
    public int bookingPrice;
    public int bookingNum;


    public DataBooking(String bookingName,int bookingPrice,int bookingNum){
        this.setBookingName(bookingName);
        this.setBookingPrice(bookingPrice);
        this.setBookingPrice(bookingNum);
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public int getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public int getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(int bookingNum) {
        this.bookingNum = bookingNum;
    }
}
