package fafour.projectthaiboxing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ShowSkillAllActivity extends AppCompatActivity {
    int i=-1;
    ProgressBar mprogressBar;

    private TextView textViewShowTime,timer;
    private CountDownTimer countDownTimer,countDownTimerTotal;
    private long totalTimeCountInMilliseconds,totalTimeCountInMillisecondsTotal;

    int x =0;
    int n =0;
    MediaPlayer mediaPlayer;
    ImageButton stop_play_button,forward_button,previous_button;
    GifImageView gifImageView;
    TextView txtSkillName;

    long time,time1;

    public ShowSkillAllActivity() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_skill_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null) {
                    mediaPlayer.stop();
                }
                stop_play_button.setImageResource(R.drawable.icn_play);
                countDownTimer.cancel();
                countDownTimerTotal.cancel();
                ((GifDrawable)gifImageView.getBackground()).stop();
                finish();
            }
        });


        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);
        timer = (TextView) findViewById(R.id.timer);

        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        mprogressBar.setProgress(0);

        gifImageView = (GifImageView) findViewById(R.id.gifImg);
        txtSkillName = (TextView) findViewById(R.id.skillName);
        stop_play_button = (ImageButton) findViewById(R.id.stop_play_button);
        forward_button = (ImageButton) findViewById(R.id.forward_button);
        previous_button = (ImageButton) findViewById(R.id.previous_button);


        previous_button.setEnabled(false);
        forward_button.setEnabled(true);
        previous_button.setAlpha(.5f);
        forward_button.setAlpha(1f);


        gifImageView.setBackgroundResource(R.drawable.gif_one);
        txtSkillName.setText("Skill one");

        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_one);
        mediaPlayer.start();


        setTimer();
        startTimer(totalTimeCountInMilliseconds);

        setTimerTotal();
        startTimerTotal(totalTimeCountInMillisecondsTotal);


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

    private void setTimerTotal() {

        int count = getIntent().getIntExtra("count",0);

        totalTimeCountInMillisecondsTotal = (60 * count * 1000)+1000;

    }
    //------------------------------------------------------------------------------------------------------------------
    private void startTimerTotal(long times) {
        countDownTimerTotal = new CountDownTimer(times, 500) {


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                time1 = leftTimeInMilliseconds;
                long seconds = leftTimeInMilliseconds / 1000;

                timer.setTextAppearance(getApplicationContext(),
                        R.style.TextAppearance_CastIntroOverlay_Title);


                timer.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));


            }

            @Override
            public void onFinish() {
                finish();
            }

        }.start();

    }
    //------------------------------------------------------------------------------------------------------------------

    private void startTimer(long times) {
        countDownTimer = new CountDownTimer(times, 500) {


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                time = leftTimeInMilliseconds;
                long seconds = leftTimeInMilliseconds / 1000;
                int seconds1 = (int) (leftTimeInMilliseconds / 1000);
                mprogressBar.setProgress(seconds1);


                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));

            }

            @Override
            public void onFinish() {

                if (n == 0 ){
                    previous_button.setEnabled(true);
                    forward_button.setEnabled(true);
                    previous_button.setAlpha(1f);
                    forward_button.setAlpha(1f);

                    gifImageView.setBackgroundResource(R.drawable.gif_two);
                    txtSkillName.setText("Skill two");
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_two);
                    mediaPlayer.start();
                    countDownTimer.cancel();
                    startTimer(totalTimeCountInMilliseconds);

                    n=1;
                }else if(n == 1){
                    previous_button.setEnabled(true);
                    forward_button.setEnabled(true);
                    previous_button.setAlpha(1f);
                    forward_button.setAlpha(1f);

                    gifImageView.setBackgroundResource(R.drawable.gif_three);
                    txtSkillName.setText("Skill three");
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_three);
                    mediaPlayer.start();
                    countDownTimer.cancel();
                    startTimer(totalTimeCountInMilliseconds);

                    n=2;
                }else if(n == 2){
                    previous_button.setEnabled(true);
                    forward_button.setEnabled(false);
                    previous_button.setAlpha(1f);
                    forward_button.setAlpha(.5f);

                    gifImageView.setBackgroundResource(R.drawable.gif_four);
                    txtSkillName.setText("Skill four");
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_four);
                    mediaPlayer.start();
                    countDownTimer.cancel();
                    startTimer(totalTimeCountInMilliseconds);

                    n=3;
                }else if(n == 3){
                    forward_button.setEnabled(true);
                    previous_button.setEnabled(false);
                    previous_button.setAlpha(.5f);
                    forward_button.setAlpha(1f);

                    gifImageView.setBackgroundResource(R.drawable.gif_one);
                    txtSkillName.setText("Skill one");
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_one);
                    mediaPlayer.start();
                    countDownTimer.cancel();
                    startTimer(totalTimeCountInMilliseconds);
                    n=0;

                }

            }

        }.start();

    }

    //------------------------------------------------------------------------------------------------------------------

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
                if(mediaPlayer!=null) {
                    mediaPlayer.stop();
                }
                stop_play_button.setImageResource(R.drawable.icn_play);
                countDownTimer.cancel();
                countDownTimerTotal.cancel();
                ((GifDrawable)gifImageView.getBackground()).stop();
                finish();

                break;
            case R.id.action_music:
                if(n == 0){
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_one);
                    mediaPlayer.start();
                }else  if(n == 1){
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_two);
                    mediaPlayer.start();
                }else  if(n == 2){
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_three);
                    mediaPlayer.start();
                }else  if(n == 3){
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }

                    mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_four);
                    mediaPlayer.start();
                }


                break;
        }
        return true;
    }
    //------------------------------------------------------------------------------------------------------------------

    public void stop_play(View view){
        if(x == 0){
            stop_play_button.setImageResource(R.drawable.icn_play);
            countDownTimer.cancel();
            countDownTimerTotal.cancel();
            x =1;
            ((GifDrawable)gifImageView.getBackground()).stop();

        }
        else{
            stop_play_button.setImageResource(R.drawable.icn_pause);
            startTimer(time);
            startTimerTotal(time1);
            x =0;
            ((GifDrawable)gifImageView.getBackground()).start();
        }


    }
    //------------------------------------------------------------------------------------------------------------------
    public void next(View view){
        ((GifDrawable)gifImageView.getBackground()).start();
        stop_play_button.setImageResource(R.drawable.icn_pause);

        if (n == 0 ){
            previous_button.setEnabled(true);
            forward_button.setEnabled(true);
            previous_button.setAlpha(1f);
            forward_button.setAlpha(1f);

            gifImageView.setBackgroundResource(R.drawable.gif_two);
            txtSkillName.setText("Skill two");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_two);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=1;
        }else if(n == 1){
            previous_button.setEnabled(true);
            forward_button.setEnabled(true);
            previous_button.setAlpha(1f);
            forward_button.setAlpha(1f);

            gifImageView.setBackgroundResource(R.drawable.gif_three);
            txtSkillName.setText("Skill three");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_three);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=2;
        }else if(n == 2){
            previous_button.setEnabled(true);
            forward_button.setEnabled(false);
            previous_button.setAlpha(1f);
            forward_button.setAlpha(.5f);

            gifImageView.setBackgroundResource(R.drawable.gif_four);
            txtSkillName.setText("Skill four");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_four);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=3;
        }else {

        }

    }
    //------------------------------------------------------------------------------------------------------------------
    public void back(View view){
        ((GifDrawable)gifImageView.getBackground()).start();
        stop_play_button.setImageResource(R.drawable.icn_pause);

        if (n == 3 ){
            forward_button.setEnabled(true);
            previous_button.setEnabled(true);
            previous_button.setAlpha(1f);
            forward_button.setAlpha(1f);

            gifImageView.setBackgroundResource(R.drawable.gif_three);
            txtSkillName.setText("Skill three");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_three);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=2;
        }else if(n == 2){
            forward_button.setEnabled(true);
            previous_button.setEnabled(true);
            previous_button.setAlpha(1f);
            forward_button.setAlpha(1f);

            gifImageView.setBackgroundResource(R.drawable.gif_two);
            txtSkillName.setText("Skill two");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_two);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=1;
        }else if(n == 1){
            forward_button.setEnabled(true);
            previous_button.setEnabled(false);
            previous_button.setAlpha(.5f);
            forward_button.setAlpha(1f);

            gifImageView.setBackgroundResource(R.drawable.gif_one);
            txtSkillName.setText("Skill one");
            if(mediaPlayer!=null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext() , R.raw.skill_one);
            mediaPlayer.start();
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

            n=0;
        }else if(n == 0){
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onPause() {
        super.onPause();
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }

        stop_play_button.setImageResource(R.drawable.icn_play);
        countDownTimer.cancel();
        countDownTimerTotal.cancel();
        ((GifDrawable)gifImageView.getBackground()).stop();
        x =1;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void onBackPressed() {
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }

        stop_play_button.setImageResource(R.drawable.icn_play);
        countDownTimer.cancel();
        countDownTimerTotal.cancel();
        ((GifDrawable)gifImageView.getBackground()).stop();


    }
    //------------------------------------------------------------------------------------------------------------------




}
