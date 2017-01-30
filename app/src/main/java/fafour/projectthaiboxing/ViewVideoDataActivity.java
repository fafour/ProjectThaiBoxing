package fafour.projectthaiboxing;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class ViewVideoDataActivity extends AppCompatActivity  {
    VideoView videoView;
    private int stopPosition = 0;
    private int position = 0;

    int i=-1;
    RoundCornerProgressBar mProgressBar;

    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video_data);
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

        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);
        mProgressBar = (RoundCornerProgressBar) findViewById(R.id.progress_1 );
        mProgressBar.setProgressColor(Color.parseColor("#ed3b27"));
        mProgressBar.setProgressBackgroundColor(Color.parseColor("#808080"));
        mProgressBar.setMax(60);
//        mProgressBar.setProgress(0);

        setTimer();
        startTimer();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        int rawVideo = getIntent().getIntExtra("idRaw",0);
        videoView = (VideoView) findViewById(R.id.video_view);


        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + rawVideo));
        videoView.start();
        videoView.requestFocus();

        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoView.setLayoutParams(params);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                       //mediaController.setAnchorView(videoView);
                    }
                });



            }

        });


    }
    // When you change direction of phone, this method will be called.
    // It store the state of video (Current position)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }


    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }
    @Override
    public void onPause() {
        super.onPause();
        stopPosition = videoView.getCurrentPosition();
        videoView.pause();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        videoView.seekTo(stopPosition);
        videoView.pause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_end, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_end:
                finish();
                break;
        }
        return true;
    }

    private void setTimer() {

        totalTimeCountInMilliseconds = 60 * 1 * 1000;

        timeBlinkInMilliseconds = 30 * 1000;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                //i++;
                //Setting the Progress Bar to decrease wih the timer
                mProgressBar.setProgress((int) (leftTimeInMilliseconds / 1000));
                textViewShowTime.setTextAppearance(getApplicationContext(),
                        R.style.TextAppearance_CastIntroOverlay_Title);


                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    textViewShowTime.setTextAppearance(getApplicationContext(),
                            R.style.TextAppearance_CastIntroOverlay_Title);
                    // change the style of the textview .. giving a red
                    // alert style

                    if (blink) {
                        textViewShowTime.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        textViewShowTime.setVisibility(View.INVISIBLE);
                    }

                    blink = !blink; // toggle the value of blink
                }

                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format

            }

            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished
                textViewShowTime.setText("00.00");
                textViewShowTime.setVisibility(View.VISIBLE);
            }

        }.start();

    }

}
