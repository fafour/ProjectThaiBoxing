package fafour.projectthaiboxing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fafour on 6/2/2560.
 */

public class BookingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    List<DataBooking> data= Collections.emptyList();
    DataBuyItem current;
    int txtincrement = 1;
    int txtdecrement = -1;
    int currentPos=0;

    public BookingAdapter(Context context, List<DataBooking> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.booking_data, parent,false);
        BookingAdapter.MyHolder holder=new BookingAdapter.MyHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final BookingAdapter.MyHolder myHolder= (BookingAdapter.MyHolder) holder;
        final DataBooking current=data.get(position);
        myHolder.nameAccessories.setText(current.bookingName+" "+current.bookingDuration);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
        String yourFormattedString = formatter.format(current.bookingPrice);
        myHolder.priceAccessories.setText(yourFormattedString +" BTH");


        myHolder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
                if( data.size() == 0){
                    BookingActivity.txtStatus.setText("Booking Empty");
                    BookingActivity.txtStatus.setVisibility(View.VISIBLE);
                    BookingActivity.dataBuy.setVisibility(View.GONE);
                }
                DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
                String yourFormattedString = formatter.format(TotalBooking.totalBuyItem());
                BookingActivity.txtTotal.setText(yourFormattedString+"");

            }
        });

        myHolder.display.setText(current.bookingNum+"");


        myHolder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -
                if(current.bookingNum == 1){

                }else {
                    int a = current.bookingNum +txtdecrement;
                    current.bookingNum =a;

                    MainActivity.booking.set(position ,new DataBooking(current.bookingName,current.bookingPrice,a,current.bookingDuration));
                    myHolder.display.setText(current.bookingNum+"");
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
                    String yourFormattedString = formatter.format(TotalBooking.totalBuyItem());
                    String yourFormattedString1 = formatter.format(TotalBooking.totalVat());
                    String yourFormattedString2 = formatter.format(TotalBooking.totalBuyItem()+TotalBooking.totalVat());
                    BookingActivity.txtTotal.setText(yourFormattedString+"");
                    BookingActivity.txtTotalAll.setText(yourFormattedString2+"");
                    BookingActivity.txtvat.setText(yourFormattedString1+"");
                }

            }
        });


        myHolder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // +
                int a = current.bookingNum +txtincrement;
                current.bookingNum =a;

                MainActivity.booking.set(position ,new DataBooking(current.bookingName,current.bookingPrice,a,current.bookingDuration));
                myHolder.display.setText(current.bookingNum+"");
                DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
                String yourFormattedString = formatter.format(TotalBooking.totalBuyItem());
                String yourFormattedString1 = formatter.format(TotalBooking.totalVat());
                String yourFormattedString2 = formatter.format(TotalBooking.totalBuyItem()+TotalBooking.totalVat());
                BookingActivity.txtTotal.setText(yourFormattedString+"");
                BookingActivity.txtTotalAll.setText(yourFormattedString2+"");
                BookingActivity.txtvat.setText(yourFormattedString1+"");


            }
        });



    }

    public void removeAt(int position) {
        MainActivity.booking.remove(position);
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView nameAccessories,priceAccessories,display;
        Button bin,increment,decrement;
        public MyHolder(View itemView) {
            super(itemView);
            increment = (Button) itemView.findViewById(R.id.increment);
            decrement = (Button) itemView.findViewById(R.id.decrement);
            bin = (Button) itemView.findViewById(R.id.bin);
            display= (TextView) itemView.findViewById(R.id.display);
            nameAccessories= (TextView) itemView.findViewById(R.id.nameAccessories);
            priceAccessories= (TextView) itemView.findViewById(R.id.priceAccessories);


        }

    }
}
