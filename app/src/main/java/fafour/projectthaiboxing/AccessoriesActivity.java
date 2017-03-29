package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
                "Tiger Muay Thai Head Guard",
                "Tiger Muay Thai White Boxing Gloves",
                "Tiger Muay Thai Black Boxing Gloves",
                "Muay Thai Shorts Black& Blue Scratch",
                "Muay Thai Shorts White& Pink",
                "Muay Thai Shorts White & Blue",
                "Muay Thai Shorts White & Orange",
                "Muay Thai Shorts Black & Pink",
                "Muay Thai Shorts Black& Blue",
                "Muay Thai Shorts Black &Orange",
                "Muay Thai Shorts Gray Camo",
                "Tank Top White (Man)",
                "Tank Top Black (Man)",
                "Tank Top White & Pink (Woman)",
                "Tank Top Black& Pink (Woman)",
                "Rashguard  Shortsleeve  Black & White Edition",
                "Rashguard  Shortsleeve  Black & Orange Edition"
        };

        String[] accessoriesDetail = {
                "Tiger Muay Thai head guards are a top-quality product, ensuring safe and productive training sessions.",
                "Specifically designed to improve the quality of your Muay Thai training and to reduce the chance of injury.",
                "Specifically designed to improve the quality of your Muay Thai training and to reduce the chance of injury.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "High quality satin Muaythai shorts from Tiger Muay Thai Special finished with a very high standard of embroidery and craftsmanship.",
                "The classic Tiger Muay Thai Tank Top in white Drifit Material with the big circular TMT logo on the front.",
                "The classic Tiger Muay Thai Tank Top in black Drifit Material with the big circular TMT logo on the front.",
                "Woman Cut Strength is Beautiful Tank Top in White & Pink",
                "Female Cut Strength is Beautiful Tank Top in Black & Pink",
                "The short sleeve version of the Tiger Muay Thai Black & White Edition rashguard.",
                "The short sleeve version of the Tiger Muay Thai Black & Orange Edition rashguard."
        };

        int [] accessoriesPrice = {
                3600,
                3000,
                3000,
                2800,
                2800,
                2800,
                2800,
                2800,
                2800,
                2800,
                2800,
                1100,
                1100,
                1100,
                1100,
                2400,
                2400
        };

        int [] accessoriesImg  = {
                R.drawable.head_guard_one,
                R.drawable.white_boxing_gloves_one,
                R.drawable.black_boxing_gloves_one,
                R.drawable.black_blue_scratch_one,
                R.drawable.white_pink_one,
                R.drawable.white_blue_one,
                R.drawable.white_orange_one,
                R.drawable.black_pink_one,
                R.drawable.black_blue_one,
                R.drawable.black_orange_one,
                R.drawable.gray_camo_one

        };
        int [] accessoriesSaleDataPrice = {
                20,
                10,
                10,
                5,
                5,
                5,
                5,
                5,
                5,
                5,
                5,
                10,
                10,
                10,
                10,
                10,
                10

        };
        int [] accessoriesImgReview_1  = {
                R.drawable.head_guard_two,
                R.drawable.white_boxing_gloves_two,
                R.drawable.black_boxing_gloves_two,
                R.drawable.black_blue_scratch_two,
                R.drawable.white_pink_two,
                R.drawable.white_blue_two,
                R.drawable.white_orange_two,
                R.drawable.black_pink_two,
                R.drawable.black_blue_two,
                R.drawable.black_orange_two,
                R.drawable.gray_camo_two
        };
        int [] accessoriesImgReview_2  = {
                R.drawable.head_guard_three,
                R.drawable.white_boxing_gloves_one,
                R.drawable.black_boxing_gloves_one,
                R.drawable.black_blue_scratch_one,
                R.drawable.white_pink_one,
                R.drawable.white_blue_one,
                R.drawable.white_orange_one,
                R.drawable.black_pink_one,
                R.drawable.black_blue_one,
                R.drawable.black_orange_one,
                R.drawable.gray_camo_one
        };



        for(int count=0; count < accessoriesName.length; count++){
            DataAccessories accessoriesData = new DataAccessories();

            accessoriesData.accessoriesName = accessoriesName[count];
            accessoriesData.accessoriesPrice = accessoriesPrice[count];

            accessoriesData.accessoriesImg = accessoriesImg[count];
            accessoriesData.accessoriesDetail = accessoriesDetail[count];

            accessoriesData.accessoriesImg1 = accessoriesImgReview_1[count];
            accessoriesData.accessoriesImg2 = accessoriesImgReview_2[count];


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
