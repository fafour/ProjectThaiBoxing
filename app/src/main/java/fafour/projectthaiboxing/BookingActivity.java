package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private BookingAdapter mAdapter;
    public static TextView txtStatus,txtTotal,txtTotalAll,txtvat;
    public static LinearLayout dataBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtStatus = (TextView)findViewById(R.id.txtStatus);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtTotalAll = (TextView)findViewById(R.id.txtTotalAll);
        txtvat = (TextView)findViewById(R.id.txtvat);
        dataBuy = (LinearLayout) findViewById(R.id.dataBuy);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        showData();

    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();

    }
    public void showData(){
        if(MainActivity.booking.size() == 0){
            txtStatus.setText("Booking Empty");
            txtStatus.setVisibility(View.VISIBLE);
            dataBuy.setVisibility(View.GONE);
        }else {
            DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
            String yourFormattedString = formatter.format(TotalBooking.totalBuyItem());
            String yourFormattedString1 = formatter.format(TotalBooking.totalVat());
            String yourFormattedString2 = formatter.format(TotalBooking.totalBuyItem()+TotalBooking.totalVat());
            txtTotal.setText(yourFormattedString+"");
            txtTotalAll.setText(yourFormattedString2+"");
            txtvat.setText(yourFormattedString1+"");
            txtStatus.setVisibility(View.GONE);
            dataBuy.setVisibility(View.VISIBLE);
        }

        List<DataBooking> data=new ArrayList<DataBooking>();
        data.clear();

        for(int count=0; count < MainActivity.booking.size(); count++){
            DataBooking cartData = new DataBooking();

            cartData.bookingNum = MainActivity.booking.get(count).getBookingNum();
            cartData.bookingDuration =  MainActivity.booking.get(count).getBookingDuration();
            cartData.bookingPrice = MainActivity.booking.get(count).getBookingPrice();
            cartData.bookingName = MainActivity.booking.get(count).getBookingName();

            data.add(cartData);

        }


        mRVList = (RecyclerView) findViewById(R.id.bookingList);
        mAdapter = new BookingAdapter(BookingActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(BookingActivity.this));
    }
    public  void payment(View v) {
        //-----------------------------------------------------------------------------------
        Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
        startActivity(intent);
        //-----------------------------------------------------------------------------------

    }


}
