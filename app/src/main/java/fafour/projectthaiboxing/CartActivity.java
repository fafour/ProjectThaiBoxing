package fafour.projectthaiboxing;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private CartAdapter mAdapter;
    public static  TextView txtStatus;
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

        showData();

    }
    @Override
    protected void onResume() {
        super.onResume();
        showData();

    }

    public void showData(){
        if(MainActivity.listBuy.size() == 0){
            txtStatus.setText("Cart Empty");
        }

        List<DataBuyItem> data=new ArrayList<>();
        data.clear();

        for(int count=0; count < MainActivity.listBuy.size(); count++){
            DataBuyItem cartData = new DataBuyItem();

            cartData.accessoriesName = MainActivity.listBuy.get(count).getAccessoriesName();
            cartData.accessoriesPrice =  MainActivity.listBuy.get(count).getAccessoriesPrice();
            cartData.accessoriesImg = MainActivity.listBuy.get(count).getAccessoriesImg();
            cartData.accessoriesNum = MainActivity.listBuy.get(count).getAccessoriesNum();
            data.add(cartData);

        }


        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.cartList);
        mAdapter = new CartAdapter(CartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(CartActivity.this));
    }

}
