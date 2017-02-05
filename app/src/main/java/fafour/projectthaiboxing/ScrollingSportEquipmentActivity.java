package fafour.projectthaiboxing;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ScrollingSportEquipmentActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private SporteEuipmentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_sport_equipment);
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

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        String skillName = getIntent().getStringExtra("name");
        int sImg = getIntent().getIntExtra("img",0);
        int skillGif = getIntent().getIntExtra("gif",0);
        int skillRaw = getIntent().getIntExtra("raw",0);


        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> pic = new ArrayList<>();

        name =  getIntent().getStringArrayListExtra("itemName");
        pic =  getIntent().getIntegerArrayListExtra("itemImg");


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(sImg);

        TextView textView = (TextView) findViewById(R.id.txtTittle);
        textView.setText(skillName);

        List<DataSportEquipment> data=new ArrayList<>();

        for(int count=0; count < name.size(); count++){
            DataSportEquipment sportEquipment = new DataSportEquipment();

            sportEquipment.sportEquipmentImg = pic.get(count);
            sportEquipment.sportEquipmentName = name.get(count);


            data.add(sportEquipment);

        }

        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.VideoList);
        mAdapter = new SporteEuipmentAdapter(ScrollingSportEquipmentActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(ScrollingSportEquipmentActivity.this));


    }
    public void click(View v){
        Intent intent = new Intent(getApplicationContext(), SetTimeActivity.class);
        startActivity(intent);

    }
    public void next(View v){
        Intent intent = new Intent(getApplicationContext(), AccessoriesActivity.class);
        startActivity(intent);

    }
}
