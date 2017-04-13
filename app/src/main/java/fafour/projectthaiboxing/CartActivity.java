package fafour.projectthaiboxing;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
   ArrayList<Integer> num_stock = new ArrayList<>();

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
        check_stock();
//        check_data();


    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();
        check_stock();
//        check_data();

    }

    @Override
    protected void onStart() {
        super.onStart();
        showData();
        check_stock();
//        check_data();

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
        if(MainActivity.listBuy.size() != 0) {
            check_stock();
            check_data();

            if (check != null) {
                show_ms();
            } else {
                Intent intent = new Intent(getApplicationContext(), EmailNameSurNameActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------
    public void check_stock(){
        num_stock.clear();
        for (int i = 0; i < MainActivity.listBuy.size(); i++) {
            if (MainActivity.listBuy.get(i).getType() == 1) {
                String now = "check_stock_1.php";
                String stock_name = "stock";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("8 oz")) {
                String now = "check_stock_2.php";
                String stock_name = "stock_8oz";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("10 oz")) {
                String now = "check_stock_2.php";
                String stock_name = "stock_10oz";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("12 oz")) {
                String now = "check_stock_2.php";
                String stock_name = "stock_12oz";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("14 oz")) {
                String now = "check_stock_2.php";
                String stock_name = "stock_14oz";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("16 oz")) {
                String now = "check_stock_2.php";
                String stock_name = "stock_16oz";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XS")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_xs";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_s";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_m";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_l";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_xl";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XXL")) {
                String now = "check_stock_3.php";
                String stock_name = "stock_xxl";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String now = "check_stock_4.php";
                String stock_name = "stock_s";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);


            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String now = "check_stock_4.php";
                String stock_name = "stock_m";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String now = "check_stock_4.php";
                String stock_name = "stock_l";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String now = "check_stock_4.php";
                String stock_name = "stock_xl";
                String name = MainActivity.listBuy.get(i).getAccessoriesName();

                stock(now,name,stock_name);
            }

        }

    }
    public  void  check_data(){
        for (int i = 0; i < MainActivity.listBuy.size(); i++) {
            if (MainActivity.listBuy.get(i).getType() == 1) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("8 oz")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("10 oz")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("12 oz")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("14 oz")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("16 oz")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XS")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XXL")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }
            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                if(num_stock.get(i)-MainActivity.listBuy.get(i).getAccessoriesNum() < 0 ){
                    check = "not";
                }

            }

        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    public void show_ms(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(CartActivity.this);
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
    public void stock(String now,final String name, final String name_stock){
        String Url = connect_web_service.url+now;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            JSONObject jsonResponse = jsonarray.getJSONObject(0);
                            num_stock.add(jsonResponse.getInt(name_stock));
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                params.put("name_item",name);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
