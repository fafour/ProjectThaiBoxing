package fafour.projectthaiboxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private CartAdapter mAdapter;
    public static  TextView txtStatus,txtTotal;
    public static LinearLayout dataBuy;

    public static final int PAYPAL_REQUEST_CODE = 123;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
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

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();

    }

    public void showData(){
        if(MainActivity.listBuy.size() == 0){
            txtStatus.setText("Cart Empty");
            txtStatus.setVisibility(View.VISIBLE);
            dataBuy.setVisibility(View.GONE);
        }else {
            DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
            String yourFormattedString = formatter.format(Total.totalBuyItem());
            txtTotal.setText(yourFormattedString+"");
            txtStatus.setVisibility(View.GONE);
            dataBuy.setVisibility(View.VISIBLE);
        }

        List<DataBuyItem> data=new ArrayList<DataBuyItem>();
        data.clear();

        for(int count=0; count < MainActivity.listBuy.size(); count++){
            DataBuyItem cartData = new DataBuyItem();

            cartData.accessoriesName = MainActivity.listBuy.get(count).getAccessoriesName();
            cartData.accessoriesPrice =  MainActivity.listBuy.get(count).getAccessoriesPrice();
            cartData.accessoriesImg = MainActivity.listBuy.get(count).getAccessoriesImg();
            cartData.accessoriesNum = MainActivity.listBuy.get(count).getAccessoriesNum();

            cartData.accessoriesSale = MainActivity.listBuy.get(count).getAccessoriesSale();
            cartData.accessoriesSaleData = MainActivity.listBuy.get(count).getAccessoriesSaleData();

            data.add(cartData);

        }


        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.cartList);
        mAdapter = new CartAdapter(CartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(CartActivity.this));
    }
    public  void payment(View v){
        getPayment();
        finish();
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void getPayment() {

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(Total.totalBuyItem())), "USD", "Accessoies",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", Total.totalBuyItem()));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

}
