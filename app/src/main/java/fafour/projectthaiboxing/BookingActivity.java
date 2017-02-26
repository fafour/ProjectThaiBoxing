package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private BookingAdapter mAdapter;
    public static TextView txtStatus,txtTotal;
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
            txtTotal.setText(yourFormattedString+"");
            txtStatus.setVisibility(View.GONE);
            dataBuy.setVisibility(View.VISIBLE);
        }

        List<DataBooking> data=new ArrayList<>();
        data.clear();

        for(int count=0; count < MainActivity.booking.size(); count++){
            DataBooking cartData = new DataBooking();

            cartData.bookingNum = MainActivity.booking.get(count).getBookingNum();
            cartData.bookingDuration =  MainActivity.booking.get(count).getBookingDuration();
            cartData.bookingPrice = MainActivity.booking.get(count).getBookingPrice();
            cartData.bookingName = MainActivity.booking.get(count).getBookingName();

            data.add(cartData);

        }


        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.bookingList);
        mAdapter = new BookingAdapter(BookingActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(BookingActivity.this));
    }
    public  void payment(View v) {
//        BackgroundMail.newBuilder(this)
//                .withUsername("fafour.se09@gmail.com")
//                .withPassword("fafour09")
//                .withMailto("fafour.liv09@gmail.com")
//                .withSubject("this is the subject")
//                .withBody("this is the body")
//                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
//                    @Override
//                    public void onSuccess() {
//                        //do some magic
//                    }
//                })
//                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
//                    @Override
//                    public void onFail() {
//                        //do some magic
//                    }
//                })
//                .send();

        Intent intent = new Intent(getApplicationContext(), YourDetailsActivity.class);
        startActivity(intent);
        finish();
    }


}
