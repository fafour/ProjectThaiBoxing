package fafour.projectthaiboxing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TshirtActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv_cart;
    RelativeLayout notificationCount1;
    private RecyclerView mRVList;
    private TshirtAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirt);
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

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("T-shirt");


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!isNetworkConnected() && !isWifiConnected() ) {

            new AlertDialog.Builder(this)
                    .setTitle("Oops! Connection Timeout")
                    .setMessage("Please check your Internet connection")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
        }
        showdata();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.cart_item);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv_cart  = (TextView)
                notificationCount1.findViewById(R.id.cart_count);
        try{
            tv_cart.setText(MainActivity.listBuy.size() +"");
        }catch (Exception x){

        }

        Button button = (Button) notificationCount1.findViewById(R.id.btn_cart);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_head_guard) {
            Intent intent = new Intent(getApplicationContext(), HeadGuardActivity.class);
            startActivity(intent);
            item.setCheckable(false);
            finish();
        } else if (id == R.id.nav_boxing_gloves) {
            Intent intent = new Intent(getApplicationContext(), BoxingGlovesActivity.class);
            startActivity(intent);
            item.setCheckable(false);
            finish();
        } else if (id == R.id.nav_shorts) {
            Intent intent = new Intent(getApplicationContext(), ShortsActivity.class);
            startActivity(intent);
            item.setCheckable(false);
            finish();
        } else if (id == R.id.nav_t_shirt) {
            item.setCheckable(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }
    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) && networkInfo.isConnected();
    }
    @Override
    protected void onResume() {
        super.onResume();
        showdata();
        try{
            tv_cart.setText(MainActivity.listBuy.size() +"");
        }catch (Exception x){

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        showdata();
    }
    public  void showdata(){

        String Url = connect_web_service.url+"stock_4.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            List<String> accessories_name_item=new ArrayList<String>();
                            List<String> accessories_detail=new ArrayList<String>();
                            List<Integer> accessories_stock1=new ArrayList<Integer>();
                            List<Integer> accessories_stock2=new ArrayList<Integer>();
                            List<Integer> accessories_stock3=new ArrayList<Integer>();
                            List<Integer> accessories_stock4=new ArrayList<Integer>();
                            List<Integer> accessoriesSaleDataPrice=new ArrayList<Integer>();
                            List<Double> accessoriesPrice=new ArrayList<Double>();
                            List<String> accessories_img_1=new ArrayList<String>();
                            List<String> accessories_img_2=new ArrayList<String>();

                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0;i<jsonarray.length();i++) {
                                JSONObject jsonResponse = jsonarray.getJSONObject(i);
                                accessories_stock1.add(jsonResponse.getInt("stock_s"));
                                accessories_stock2.add(jsonResponse.getInt("stock_m"));
                                accessories_stock3.add(jsonResponse.getInt("stock_l"));
                                accessories_stock4.add(jsonResponse.getInt("stock_xl"));
                                accessoriesPrice.add(jsonResponse.getDouble("price"));
                                accessoriesSaleDataPrice.add(jsonResponse.getInt("sale_percent"));
                                accessories_name_item.add(jsonResponse.getString("name_item"));
                                accessories_detail.add(jsonResponse.getString("detail"));

                                accessories_img_1.add(jsonResponse.getString("img_1"));
                                accessories_img_2.add(jsonResponse.getString("img_2"));
                            }


                            List<DataAccessories> data1=new ArrayList<>();


                            for(int count=0; count < accessories_name_item.size(); count++){
                                DataAccessories accessoriesData = new DataAccessories();

                                accessoriesData.accessoriesName = accessories_name_item.get(count);
                                accessoriesData.accessoriesPrice = accessoriesPrice.get(count);

                                accessoriesData.accessoriesImg = accessories_img_1.get(count);
                                accessoriesData.accessoriesDetail = accessories_detail.get(count);

                                accessoriesData.accessoriesImg1 = accessories_img_2.get(count);

                                Double sum = accessoriesPrice.get(count)-(accessoriesPrice.get(count)*accessoriesSaleDataPrice.get(count)/100);

                                accessoriesData.accessoriesSale = sum;;
                                accessoriesData.accessoriesSaleData = accessoriesSaleDataPrice.get(count);

                                accessoriesData.accessoriesstock1 = accessories_stock1.get(count);
                                accessoriesData.accessoriesstock2 = accessories_stock2.get(count);
                                accessoriesData.accessoriesstock3 = accessories_stock3.get(count);
                                accessoriesData.accessoriesstock4 = accessories_stock4.get(count);

                                data1.add(accessoriesData);

                            }

                            // Setup and Handover data to recyclerview
                            mRVList = (RecyclerView) findViewById(R.id.accessoriesList);
                            mAdapter = new TshirtAdapter(TshirtActivity.this, data1);
                            mRVList.setAdapter(mAdapter);
                            mRVList.setLayoutManager(new LinearLayoutManager(TshirtActivity.this));


                        } catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ;
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

}
