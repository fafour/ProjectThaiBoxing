package fafour.projectthaiboxing;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ScrollingViewVideoActivity extends AppCompatActivity {
    private RecyclerView mRVList;
    private VideoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_view_video);
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


        List<DataVideo> data=new ArrayList<>();

        String[] videoFileList = {
                "cha-wa-sad-hok",
                "kun-yak-jab-ling",
                "ja-ra-kay-fad-hang",
                "dap-cha-la-wa",
                "ta-kay-kum-fak",
                "na-ka-bid-hang",
                "pak-sa-waek-rang",
                "pad-look-toy",
                "mon-yan-lak",
                "yok-kao-pra-su-main",
                "vi-roon-hok-kap",
                "sa-lap-fun-pa",
                "hak-kor-era-wan",
                "hak-ngong-ai-yra",
                "ai-nhao-tang-kit"
        };
        int [] videoList  = {
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai,
                R.raw.muay_thai
        };

        int [] imgVidio  = {
                R.drawable.skill_one,
                R.drawable.skill_two,
                R.drawable.skill_three,
                R.drawable.skill_four,
                R.drawable.skill_five,
                R.drawable.skill_six,
                R.drawable.skill_seven,
                R.drawable.skill_eight,
                R.drawable.skill_nine,
                R.drawable.skill_ten,
                R.drawable.skill_eleven,
                R.drawable.skill_twele,
                R.drawable.skill_thirteen,
                R.drawable.skill_fourteen,
                R.drawable.skill_fifteen

        };

        for(int count=0; count < videoFileList.length; count++){
            DataVideo videoData = new DataVideo();

            videoData.videoName = videoFileList[count];
            videoData.videoRaw = videoList[count];
//            videoData.videoImage = imgVideoFile[count];
            videoData.videoImg = imgVidio[count];

            data.add(videoData);

        }

        // Setup and Handover data to recyclerview
        mRVList = (RecyclerView) findViewById(R.id.VideoList);
        mAdapter = new VideoAdapter(ScrollingViewVideoActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.setLayoutManager(new LinearLayoutManager(ScrollingViewVideoActivity.this));
    }
}
