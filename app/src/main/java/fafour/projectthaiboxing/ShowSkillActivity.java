package fafour.projectthaiboxing;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class ShowSkillActivity extends AppCompatActivity  {
    int i=-1;
    ProgressBar mprogressBar;

    private TextView textViewShowTime;
    private CountDownTimer countDownTimer,countDownTimer1;
    private long totalTimeCountInMilliseconds;

    int x =0;
    MediaPlayer mediaPlayer;
    ImageView stop_play_button;
    GifImageView gifImageView;

    MenuItem favButton;

    long time;
    int begin = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_skill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
                endSound();
                finish();
            }
        });

        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);

        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        mprogressBar.setProgress(0);

        gifImageView = (GifImageView) findViewById(R.id.gifImg);
        TextView txtSkillName = (TextView) findViewById(R.id.skillName);
        stop_play_button = (ImageView) findViewById(R.id.stop_play_button);


        String skillName_1 = getIntent().getStringExtra("name");;
        int skillGif_1 = getIntent().getIntExtra("gif",0);
        int skillMp3_1 = getIntent().getIntExtra("mp3",0);

        gifImageView.setBackgroundResource(skillGif_1);

        txtSkillName.setText(skillName_1);

        mediaPlayer =  MediaPlayer.create(getBaseContext(),skillMp3_1);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (begin == 1){
                    begin = 0;
                    favButton.setEnabled(true);
                    mediaPlayer =  MediaPlayer.create(getBaseContext(),R.raw.begin);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            if(mediaPlayer!=null) {
                                mediaPlayer.stop();
                            }
                        }
                    });
                }
            }
        });


        setTimer();
        startTimer(totalTimeCountInMilliseconds);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

    }

    private void setTimer() {

        totalTimeCountInMilliseconds = (60 * 1 * 1000)+1000;

    }

    private void startTimer(final long times) {
            countDownTimer = new CountDownTimer(times, 1000) {

                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    Resources res = getResources();
                    time = leftTimeInMilliseconds;
                    long seconds = leftTimeInMilliseconds / 1000;
                    int seconds1 = (int) (leftTimeInMilliseconds / 1000);
                    mprogressBar.setProgress(seconds1);


                    textViewShowTime.setText(String.format("%02d", seconds / 60)
                            + ":" + String.format("%02d", seconds % 60));
                    if(seconds==3.00){
                        playSound(R.raw.countdown);
                        favButton.setEnabled(false);
                    }

                }

                @Override
                public void onFinish() {
                    textViewShowTime.setText("00:00");
                    countDownTimer1 = new CountDownTimer(1000, 1000) {

                        @Override
                        public void onTick(long leftTimeInMilliseconds) {

                        }

                        @Override
                        public void onFinish() {
                            stopSound();
                            endSound();
                            finish();
                        }

                    }.start();
                }

            }.start();





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_end, menu);
        favButton = (MenuItem) menu.findItem(R.id.action_music);
        favButton.setEnabled(false);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_end:
                stopSound();
                endSound();
                finish();
                break;
            case R.id.action_music:
                int skillMp3_1 = getIntent().getIntExtra("mp3",0);
                playSound(skillMp3_1);
                break;
        }
        return true;
    }


    public void stop_play(View view){

        if(x == 0){
            stopSound();

        }
        else{
            stop_play_button.setImageResource(R.drawable.icn_pause);
            startTimer(time);
            x =0;
            ((GifDrawable)gifImageView.getBackground()).start();
            mediaPlayer.start();

        }

    }
    public void video(View view){
        stopSound();
        int skillGif_1 = getIntent().getIntExtra("gif",0);
        Intent intent = new Intent(getApplicationContext(), ViewVideoActivity.class);
        intent.putExtra("gif",skillGif_1);
        startActivity(intent);

    }
    @Override
    public void onPause() {
        super.onPause();
        stopSound();
    }
    public void onBackPressed() {
        stopSound();
        endSound();
        finish();

    }
    public void playSound(int sound){
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }
        mediaPlayer =  MediaPlayer.create(getBaseContext(),sound);
        mediaPlayer.start();

    }
    public void stopSound(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        if (begin == 0) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    if (mediaPlayer != null) {
                            mediaPlayer.stop();
                    }
                }
            });
        }
        stop_play_button.setImageResource(R.drawable.icn_play);
        countDownTimer.cancel();
        ((GifDrawable)gifImageView.getBackground()).stop();
        x =1;

    }
    public void endSound(){
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }

    }


}
