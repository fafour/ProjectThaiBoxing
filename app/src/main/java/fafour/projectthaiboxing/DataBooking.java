package fafour.projectthaiboxing;

/**
 * Created by Fafour on 6/2/2560.
 */

public class DataBooking {
    public String bookingName;
    public String bookingDuration;
    public int bookingPrice;
    public int bookingNum;


    public DataBooking(String bookingName,int bookingPrice,int bookingNum,String bookingDuration){
        this.setBookingName(bookingName);
        this.setBookingPrice(bookingPrice);
        this.setBookingNum(bookingNum);
        this.setBookingDuration(bookingDuration);
    }

    public DataBooking(String bookingName,int bookingPrice,int bookingNum){
        this.setBookingName(bookingName);
        this.setBookingPrice(bookingPrice);
        this.setBookingNum(bookingNum);
    }
    public  DataBooking(){

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

    public String getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(String bookingDuration) {
        this.bookingDuration = bookingDuration;
    }
}
