package fafour.projectthaiboxing;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private CartAdapter mAdapter;
    public static  TextView txtStatus,txtTotal;
    public static LinearLayout dataBuy;
    String check = null;
    ProgressDialog progressDialog;

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


    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();

    }

    @Override
    protected void onStart() {
        super.onStart();
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
            cartData.accessoriesStock = MainActivity.listBuy.get(count).getAccessoriesStock();
            cartData.accessoriesSize = MainActivity.listBuy.get(count).getAccessoriesSize();
            data.add(cartData);

        }

        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.cartList);
        mAdapter = new CartAdapter(CartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(CartActivity.this));
    }

    public  void payment(View v){
        progressDialog = ProgressDialog.show(CartActivity.this, "Please wait ...",  "Task in progress ...", true);
        progressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    check_stock();
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
                progressDialog.dismiss();

                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (check != null) {
                            show_ms();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), EmailNameSurNameActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });

            }
        }).start();
    }



    //--------------------------------------------------------------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------------------------------------------------------------
    public void show_ms(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(CartActivity.this);
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
    //--------------------------------------------------------------------------------------------------------------------------------------------------

}
