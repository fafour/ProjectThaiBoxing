package fafour.projectthaiboxing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckProductsActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private CheckProductsAdapter mAdapter;
    public static TextView txtTotal,txtTax,txtShipping,txtTotalAll;
    double txt_tax =0.0;
    double all_pirce =0.0;
    public static final int PAYPAL_REQUEST_CODE = 123;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    String check = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_products);
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

        txtTax = (TextView)findViewById(R.id.txtTax);
        txtShipping = (TextView)findViewById(R.id.txtShipping);
        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtTotalAll = (TextView)findViewById(R.id.txtTotalAll);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        tax();

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        tax();
    }

    @Override
    protected void onStart() {
        super.onStart();
        tax();

    }

    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    public void payment(View view){
        progressDialog = ProgressDialog.show(CheckProductsActivity.this, "Please wait ...",  "Task in progress ...", true);
        progressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    check_stock();
                    Thread.sleep(5000);
                } catch (Exception e) {

                }
                progressDialog.dismiss();

                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (check != null) {
                            show_ms();
                        } else {
                            getPayment();
                            finish();
                        }
                    }

                });

            }
        }).start();

//        finish();


    }
    //---------------------------------------------------------------------------------------------------------------
    public void send_email(){
        String user = getIntent().getStringExtra("user");
        String email = getIntent().getStringExtra("email");

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
        String yourFormattedString = formatter.format(Total.totalBuyItem());
        String yourFormattedString1 = formatter.format(Total.totalBuyItemVat());
        String data_all_price=
                        "                       Total Products :"+yourFormattedString+" USD"+"\n"+
                        "                       Tax 7% :"+yourFormattedString1+" USD"+"\n"+
                        "                       Shipping :"+txt_tax+" USD"+"\n"+
                        "                       Total All : "+formatter.format(all_pirce)+" USD";

        String information="";
        for(int a=0; a < MainActivity.listBuy.size(); a++){
            String name = MainActivity.listBuy.get(a).getAccessoriesName();
            String size = MainActivity.listBuy.get(a).accessoriesSize;
            int num = MainActivity.listBuy.get(a).accessoriesNum;
            double price = MainActivity.listBuy.get(a).accessoriesNum*MainActivity.listBuy.get(a).accessoriesSale;
            if(a==0){
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }
            }else {
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }

            }
        }


        String informations="";
        for(int c=0; c < MainActivity.listBuy.size(); c++){
            String names = MainActivity.listBuy.get(c).getAccessoriesName();
            if(c==0){
                informations = names;
            }else {
                informations = information+" and "+names;
            }
        }

        String data_mail = "Thanks for shopping with us! We are glad to inform you that your order "+informations+" has been finished payment with details as below. We hope you enjoy your purchase on Muay Thai application. "+
                "\n"+"\n"+"Package details : "+"\n"
                +"\n"+information+"\n"
                +"\n"+data_all_price;


        BackgroundMail.newBuilder(this)
                .withUsername("muaythai.mt09@gmail.com")
                .withPassword("fafour09")
                .withMailto(email)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Dear "+user)
                .withBody(data_mail)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {

                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {

                    }
                })
                .send();
    }
    ///--------------------------------------------------------------------------------------------------------------
    public void send_email_1(){
        String user = getIntent().getStringExtra("user");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String description = getIntent().getStringExtra("description");

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
        String yourFormattedString = formatter.format(Total.totalBuyItem());
        String yourFormattedString1 = formatter.format(Total.totalBuyItemVat());
        String data_all_price="Total Products :"+yourFormattedString+" USD"+"\n"+
                "Tax 7% :"+yourFormattedString1+" USD"+"\n"+
                "Shipping :"+txt_tax+" USD"+"\n"+
                "Total All : "+formatter.format(all_pirce)+" USD";

        String information="";
        for(int a=0; a < MainActivity.listBuy.size(); a++){
            String name = MainActivity.listBuy.get(a).getAccessoriesName();
            String size = MainActivity.listBuy.get(a).accessoriesSize;
            int num = MainActivity.listBuy.get(a).accessoriesNum;
            double price = MainActivity.listBuy.get(a).accessoriesNum*MainActivity.listBuy.get(a).accessoriesSale;
            if(a==0){
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }
            }else {
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }

            }
        }

        String dataMail="name : "+user+"\n"
                +"\n"+"E-mail : "+email+"\n"
                +"\n"+"Address :"+"\n"+address+"\n"
                +"\n"+"Description : "+"\n"+description+"\n"
                +"\n"+"Information : "+"\n"+information+"\n"
                +"\n"+data_all_price;


        BackgroundMail.newBuilder(this)

                .withUsername("muaythai.mt09@gmail.com")
                .withPassword("fafour09")
                .withMailto("fafour.se09@gmail.com")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Accessories By "+email)
                .withBody(dataMail)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {

                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {

                    }
                })
                .send();
    }
    //---------------------------------------------------------------------------------------------------------------

    private void getPayment() {

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(0.5)), "USD", "Accessoies",
                PayPalPayment.PAYMENT_INTENT_SALE);


//        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(all_pirce)), "USD", "Accessoies",
//                PayPalPayment.PAYMENT_INTENT_SALE);

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
                    //-------------------paypal confirm--------------------------------------------------------

                    check_user();
                    add_purchase();
                    buy_item();
                    send_email();
                    send_email_1();

                    MainActivity.listBuy.clear();

                    //-------------------paypal confirm--------------------------------------------------------

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------------
    public void add_purchase(){
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
        String yourFormattedString = formatter.format(Total.totalBuyItem());
        String yourFormattedString1 = formatter.format(Total.totalBuyItemVat());
        String data_all_price="Total Products :"+yourFormattedString+" USD"+"\n"+
                "Tax 7% :"+yourFormattedString1+" USD"+"\n"+
                "Shipping :"+txt_tax+" USD"+"\n"+
                "Total All : "+formatter.format(all_pirce)+" USD";

        String information="";
        for(int a=0; a < MainActivity.listBuy.size(); a++){
            String name = MainActivity.listBuy.get(a).getAccessoriesName();
            String size = MainActivity.listBuy.get(a).accessoriesSize;
            int num = MainActivity.listBuy.get(a).accessoriesNum;
            double price = MainActivity.listBuy.get(a).accessoriesNum*MainActivity.listBuy.get(a).accessoriesSale;
            if(a==0){
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+data;
                }
            }else {
                String data="";
                if(!size.equals("")) {
                    data = (a+1)+". "+name+"  "+size+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }else {
                    data =  (a+1)+". "+name+
                            "\n"+"  Qty: "+num+"           "+price+"  USD";
                    information = information+"\n"+data;
                }

            }
        }

        String Url = connect_web_service.url+"app_purchase.php";

        final String finalInformation = information;
        final String finalData_all_price = data_all_price;
        StringRequest stringRequest4 = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws com.android.volley.AuthFailureError{
                String email = getIntent().getStringExtra("email");
                Map<String,String> params = new HashMap<String, String>();
                params.put("purchase_description", finalInformation);
                params.put("email",email);
                params.put("all_price",finalData_all_price);
                return params;
            }

        };
        RequestQueue requestQueue4 = Volley.newRequestQueue(getApplicationContext());
        requestQueue4.add(stringRequest4);

    }
    //---------------------------------------------------------------------------------------------------------------
    public void update_user(){
        String Url = connect_web_service.url+"update_user.php";

        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws com.android.volley.AuthFailureError{
                String user = getIntent().getStringExtra("user");
                String email = getIntent().getStringExtra("email");
                String address = getIntent().getStringExtra("address");
                String description = getIntent().getStringExtra("description");
                Map<String,String> params = new HashMap<String, String>();
                params.put("name_buyer",user);
                params.put("email",email);
                params.put("description",description);
                params.put("address",address);
                return params;
            }

        };
        RequestQueue requestQueue3 = Volley.newRequestQueue(getApplicationContext());
        requestQueue3.add(stringRequest3);

    }
    //---------------------------------------------------------------------------------------------------------------
    public void check_user(){
        String Url = connect_web_service.url+"show_user.php";

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.equals("no rows")) {
                            try {
                                JSONArray jsonarray = new JSONArray(response);
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject jsonResponse = jsonarray.getJSONObject(i);
                                    String email = getIntent().getStringExtra("email");
                                    if (email.equals(jsonResponse.getString("email"))) {
                                        update_user();
                                    } else {
                                        add_user();
                                    }
                                }
                            } catch (JSONException e) {
                            }
                        }else{
                            add_user();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws com.android.volley.AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        requestQueue2.add(stringRequest2);
    }
    //---------------------------------------------------------------------------------------------------------------
    public void add_user(){
        String Url = connect_web_service.url+"add_user.php";

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws com.android.volley.AuthFailureError{
                String user = getIntent().getStringExtra("user");
                String email = getIntent().getStringExtra("email");
                String address = getIntent().getStringExtra("address");
                String description = getIntent().getStringExtra("description");
                Map<String,String> params = new HashMap<String, String>();
                params.put("name_buyer",user);
                params.put("email",email);
                params.put("description",description);
                params.put("address",address);
                return params;
            }

        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);

    }
    //---------------------------------------------------------------------------------------------------------------
    public void tax(){
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
            cartData.accessoriesStock = MainActivity.listBuy.get(count).getAccessoriesStock();
            cartData.accessoriesSize = MainActivity.listBuy.get(count).getAccessoriesSize();
            data.add(cartData);

        }

        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.cartList);
        mAdapter = new CheckProductsAdapter(CheckProductsActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(CheckProductsActivity.this));

        String Url = connect_web_service.url+"tax_shipping.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++) {
                                JSONObject jsonResponse = jsonarray.getJSONObject(i);
                                String item_country = getIntent().getStringExtra("item_country");
                                if (item_country.equals(jsonResponse.getString("country"))){
                                    int num =0;
                                    for(int count1=0; count1 < MainActivity.listBuy.size(); count1++){
                                        num = num + (MainActivity.listBuy.get(count1).getAccessoriesNum());
                                    }

                                    for(int counts=0; counts < num; counts++){
                                        if ( counts == 0){
                                            txt_tax =0;
                                            txt_tax = txt_tax+jsonResponse.getDouble("tax_1");
                                        }else {
                                            txt_tax = txt_tax+jsonResponse.getDouble("tax_2");
                                        }
                                    }

                                }

                            }
                            DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
                            String yourFormattedString = formatter.format(Total.totalBuyItem());
                            String yourFormattedString1 = formatter.format(Total.totalBuyItemVat());
                            String yourFormattedString2 = formatter.format(txt_tax);
                            String yourFormattedString3 = formatter.format(Total.totalBuyItem() + Total.totalBuyItemVat() +txt_tax);
                            txtTotal.setText(yourFormattedString+"");
                            txtTax.setText(yourFormattedString1+"");
                            txtShipping.setText(yourFormattedString2+"");
                            txtTotalAll.setText(yourFormattedString3+"");
                            all_pirce = Total.totalBuyItem() + Total.totalBuyItemVat() +txt_tax;

                        } catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws com.android.volley.AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    //---------------------------------------------------------------------------------------------------------------
    public void buy_item(){
        for (int i = 0; i < MainActivity.listBuy.size(); i++) {
            if (MainActivity.listBuy.get(i).getType() == 1) {
                String update = "update_stock_1.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_1.php";
                String name_stock ="stock";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("8 oz")) {
                String update = "update_stock_2_1.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_2.php";
                String name_stock ="stock_8oz";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("10 oz")) {
                String update = "update_stock_2_2.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_2.php";
                String name_stock ="stock_10oz";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("12 oz")) {
                String update = "update_stock_2_3.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_2.php";
                String name_stock ="stock_12oz";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("14 oz")) {
                String update = "update_stock_2_4.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_2.php";
                String name_stock ="stock_14oz";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("16 oz")) {
                String update = "update_stock_2_5.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_2.php";
                String name_stock ="stock_16oz";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XS")) {
                String update = "update_stock_3_1.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_xs";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String update = "update_stock_3_2.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_s";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String update = "update_stock_3_3.php";
                int update_stock = MainActivity.listBuy.get(i).getAccessoriesStock() - MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_m";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String update = "update_stock_3_4.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_l";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String update = "update_stock_3_5.php";
                int update_stock = MainActivity.listBuy.get(i).getAccessoriesStock() - MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_xl";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XXL")) {
                String update = "update_stock_3_6.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_3.php";
                String name_stock ="stock_xxl";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String update = "update_stock_4_1.php";
                int update_stock = MainActivity.listBuy.get(i).getAccessoriesStock() - MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_4.php";
                String name_stock ="stock_s";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String update = "update_stock_4_2.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_4.php";
                String name_stock ="stock_m";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String update = "update_stock_4_3.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_4.php";
                String name_stock ="stock_l";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String update = "update_stock_4_4.php";
                int update_stock =  MainActivity.listBuy.get(i).getAccessoriesNum();
                String check_stock ="check_stock_4.php";
                String name_stock ="stock_xl";
                update_stock_all(update_stock,MainActivity.listBuy.get(i).getAccessoriesName(),update,check_stock,name_stock);
            }

        }

    }

    public void update_stock_all(final int stock_1 , final String name_item, final String name_update,final String check_stock,final String name_stock){
        String Url = connect_web_service.url+check_stock;
        StringRequest stringRequest15 = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            JSONObject jsonResponse = jsonarray.getJSONObject(0);

                            final int getStock = jsonResponse.getInt(name_stock)-stock_1;

                            String Url1 = connect_web_service.url+name_update;
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url1,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    }){
                                @Override
                                protected Map<String,String> getParams(){

                                    Map<String,String> params = new HashMap<String, String>();
                                    params.put("stock", String.valueOf(getStock));
                                    params.put("name_item",name_item);
                                    return params;
                                }

                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(stringRequest);


                        } catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String, String>();
                params.put("name_item",name_item);
                return params;
            }

        };
        RequestQueue requestQueue15 = Volley.newRequestQueue(this);
        requestQueue15.add(stringRequest15);


    }
    //---------------------------------------------------------------------------------------------------------------
    public void check_stock(){
        int socketTimeout = 100;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        for (int i = 0; i < MainActivity.listBuy.size(); i++) {
            if (MainActivity.listBuy.get(i).getType() == 1) {
                String Url = connect_web_service.url+"check_stock_1.php";
                final int finalI = i;
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue1 = Volley.newRequestQueue(this);
                stringRequest1.setRetryPolicy(policy);
                requestQueue1.add(stringRequest1);


            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("8 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_8oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue2 = Volley.newRequestQueue(this);
                stringRequest2.setRetryPolicy(policy);
                requestQueue2.add(stringRequest2);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("10 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest3 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_10oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue3 = Volley.newRequestQueue(this);
                stringRequest3.setRetryPolicy(policy);
                requestQueue3.add(stringRequest3);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("12 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest4 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_12oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue4 = Volley.newRequestQueue(this);
                stringRequest4.setRetryPolicy(policy);
                requestQueue4.add(stringRequest4);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("14 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest5 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_14oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue5 = Volley.newRequestQueue(this);
                stringRequest5.setRetryPolicy(policy);
                requestQueue5.add(stringRequest5);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("16 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_16oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue6 = Volley.newRequestQueue(this);
                stringRequest6.setRetryPolicy(policy);
                requestQueue6.add(stringRequest6);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XS")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xs") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue7 = Volley.newRequestQueue(this);
                stringRequest7.setRetryPolicy(policy);
                requestQueue7.add(stringRequest7);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest8 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_s") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue8 = Volley.newRequestQueue(this);
                stringRequest8.setRetryPolicy(policy);
                requestQueue8.add(stringRequest8);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest9 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_m") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue9 = Volley.newRequestQueue(this);
                stringRequest9.setRetryPolicy(policy);
                requestQueue9.add(stringRequest9);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest10 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_l") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue10 = Volley.newRequestQueue(this);
                stringRequest10.setRetryPolicy(policy);
                requestQueue10.add(stringRequest10);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest11 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue11 = Volley.newRequestQueue(this);
                stringRequest11.setRetryPolicy(policy);
                requestQueue11.add(stringRequest11);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XXL")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest12 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xxl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue12 = Volley.newRequestQueue(this);
                stringRequest12.setRetryPolicy(policy);
                requestQueue12.add(stringRequest12);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest13 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_s") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue13 = Volley.newRequestQueue(this);
                stringRequest13.setRetryPolicy(policy);
                requestQueue13.add(stringRequest13);


            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest14 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_m") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue14 = Volley.newRequestQueue(this);
                stringRequest14.setRetryPolicy(policy);
                requestQueue14.add(stringRequest14);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest15 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_l") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue15 = Volley.newRequestQueue(this);
                stringRequest15.setRetryPolicy(policy);
                requestQueue15.add(stringRequest15);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest16 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue16 = Volley.newRequestQueue(this);
                stringRequest16.setRetryPolicy(policy);
                requestQueue16.add(stringRequest16);

            }

        }

    }
    //---------------------------------------------------------------------------------------------------------------
    public void show_ms(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(CheckProductsActivity.this);
        dialog.setTitle("Products Have Changed...");
        dialog.setMessage("Please Select A New Product...");
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.listBuy.clear();
                finish();
            }
        });

        dialog.show();

    }
    //------------------------------------------------------------------------------------------------
}
