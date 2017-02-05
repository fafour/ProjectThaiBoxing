package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private AccessoriesAdapter mAdapter;
    RelativeLayout notificationCount1;
    TextView tv_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);
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
        collapsingToolbar.setTitle("Accessories");

        List<DataAccessories> data=new ArrayList<>();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        String[] accessoriesName = {
                "Shin guard ankle",
                "Muay Thai Shorts",
                "Sweatpants",
                "Shorts",
                "T-shirt",
                "Muay Thai Mongkol",
                "Shoes",
                "Open patella knee sleeve",
                "Head guard",
                "Ankle",
                "Jacket",
                "Gloves"
        };

        int [] accessoriesPrice = {
                200,
                300,
                1000,
                3000,
                400,
                500,
                2000,
                1500,
                2500,
                300,
                4000,
                3200
        };

        int [] accessoriesImg  = {
                R.drawable.accessories_one,
                R.drawable.accessories_two,
                R.drawable.accessories_three,
                R.drawable.accessories_four,
                R.drawable.accessories_five,
                R.drawable.accessories_six,
                R.drawable.accessories_seven,
                R.drawable.accessories_eight,
                R.drawable.accessories_nine,
                R.drawable.accessories_ten,
                R.drawable.accessories_eleven,
                R.drawable.accessories_twelve

        };
        int [] accessoriesSaleDataPrice = {
                10,
                30,
                10,
                30,
                45,
                50,
                21,
                10,
                25,
                34,
                40,
                32
        };


        for(int count=0; count < accessoriesName.length; count++){
            DataAccessories accessoriesData = new DataAccessories();

            accessoriesData.accessoriesName = accessoriesName[count];
            accessoriesData.accessoriesPrice = accessoriesPrice[count];
//            videoData.videoImage = imgVideoFile[count];
            accessoriesData.accessoriesImg = accessoriesImg[count];

            accessoriesData.accessoriesImg1 = accessoriesImg[count];
            accessoriesData.accessoriesImg2 = accessoriesImg[count];
            accessoriesData.accessoriesImg3 = accessoriesImg[count];
            accessoriesData.accessoriesImg4 = accessoriesImg[count];

            int sum = accessoriesPrice[count]-(accessoriesPrice[count]*accessoriesSaleDataPrice[count]/100);

            accessoriesData.accessoriesSale = sum;;
            accessoriesData.accessoriesSaleData = accessoriesSaleDataPrice[count];;


            data.add(accessoriesData);

        }


        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.accessoriesList);
        mAdapter = new AccessoriesAdapter(AccessoriesActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(AccessoriesActivity.this));

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
    @Override
    protected void onResume() {
        super.onResume();
        try{
            tv_cart.setText(MainActivity.listBuy.size() +"");
        }catch (Exception x){

        }


    }


}
