package fafour.projectthaiboxing;

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

import java.util.ArrayList;
import java.util.List;

public class ScrollingUpperPartActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private SkillAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_upper_part);
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

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        collapsingToolbar.setTitle("View Video");
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        List<DataSkill> data=new ArrayList<>();

        String[] SkillName = {
                "FRONT HOOK",
                "JAB ",
                "UPPERCUT",
                "CROSS"
        };
        int [] SkillImg  = {
                R.drawable.img_upper_one,
                R.drawable.img_upper_two,
                R.drawable.img_upper_three,
                R.drawable.img_upper_four
        };


        int [] SkillGif  = {
                R.drawable.gif_one,
                R.drawable.gif_two,
                R.drawable.gif_three,
                R.drawable.gif_four

        };

        int [] SkillMp3  = {
                R.raw.skill_one,
                R.raw.skill_two,
                R.raw.skill_three,
                R.raw.skill_four
        };



        for(int count=0; count < SkillName.length; count++){
            DataSkill skillData = new DataSkill();

            skillData.skillName = SkillName[count];
            skillData.skillImg = SkillImg[count];
//            videoData.videoImage = imgVideoFile[count];
            skillData.skillGif = SkillGif[count];

            skillData.skillMp3 = SkillMp3[count];

            skillData.imgAll = R.drawable.img_upper;

            if(count == 0){
                skillData.sportequipmentName.add("Muay Thai Shorts");
                skillData.sportequipmentName.add("Shoes");
                skillData.sportequipmentName.add("Gloves");

                skillData.sportequipmentImg.add(R.drawable.accessories_two);
                skillData.sportequipmentImg.add(R.drawable.accessories_seven);
                skillData.sportequipmentImg.add(R.drawable.accessories_twelve);

            }else  if(count==1){
                skillData.sportequipmentName.add("Muay Thai Shorts");
                skillData.sportequipmentName.add("Shoes");
                skillData.sportequipmentName.add("Gloves");

                skillData.sportequipmentImg.add(R.drawable.accessories_two);
                skillData.sportequipmentImg.add(R.drawable.accessories_seven);
                skillData.sportequipmentImg.add(R.drawable.accessories_twelve);

            }else  if(count == 2){

                skillData.sportequipmentName.add("Muay Thai Shorts");
                skillData.sportequipmentName.add("Shoes");
                skillData.sportequipmentName.add("Gloves");
                skillData.sportequipmentName.add("Ankle");

                skillData.sportequipmentImg.add(R.drawable.accessories_two);
                skillData.sportequipmentImg.add(R.drawable.accessories_seven);
                skillData.sportequipmentImg.add(R.drawable.accessories_twelve);
                skillData.sportequipmentImg.add(R.drawable.accessories_ten);

            }
            else  if(count == 3){

                skillData.sportequipmentName.add("Muay Thai Shorts");
                skillData.sportequipmentName.add("Shoes");
                skillData.sportequipmentName.add("Gloves");
                skillData.sportequipmentName.add("Ankle");

                skillData.sportequipmentImg.add(R.drawable.accessories_two);
                skillData.sportequipmentImg.add(R.drawable.accessories_seven);
                skillData.sportequipmentImg.add(R.drawable.accessories_twelve);
                skillData.sportequipmentImg.add(R.drawable.accessories_ten);

            }

            data.add(skillData);

        }


        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.VideoList);
        mAdapter = new SkillAdapter(ScrollingUpperPartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(ScrollingUpperPartActivity.this));

    }
}
