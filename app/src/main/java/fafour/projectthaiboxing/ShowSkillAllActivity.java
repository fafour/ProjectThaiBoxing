package fafour.projectthaiboxing;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ShowSkillAllActivity extends AppCompatActivity {
    int i=-1;
    ProgressBar mprogressBar;

    private TextView textViewShowTime,timer;
    private CountDownTimer countDownTimer,countDownTimerTotal,countDownTimer1;
    private long totalTimeCountInMilliseconds,totalTimeCountInMillisecondsTotal;

    int x =0;
    int n =0;
    MediaPlayer mediaPlayer,mediaPlayer1;
    CircleImageView stop_play_button,forward_button,previous_button;
    GifImageView gifImageView;
    TextView txtSkillName;

    long time,time1;

    MenuItem favButton;
    int begin = 1;
    int end = 1;

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
                endSound();
                stopSound();
                finish();
            }
        });


        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);
        timer = (TextView) findViewById(R.id.timer);

        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        mprogressBar.setProgress(0);

        gifImageView = (GifImageView) findViewById(R.id.gifImg);
        txtSkillName = (TextView) findViewById(R.id.skillName);
        stop_play_button = (CircleImageView) findViewById(R.id.stop_play_button);
        forward_button = (CircleImageView) findViewById(R.id.forward_button);
        previous_button = (CircleImageView) findViewById(R.id.previous_button);

        statusButton_3();

        gifImageView.setBackgroundResource(R.drawable.gif_sa_lap_fun_pla);
        txtSkillName.setText("Sa Lap Fun Pla");

        mediaPlayer = MediaPlayer.create(this, R.raw.salaufunpla_thai);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (begin == 1){
                    begin = 0;
                    favButton.setEnabled(true);
                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.begin);
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

        totalTimeCountInMilliseconds = (30 * 1 * 1000)+1000;

    }

    private void setTimerTotal() {

        int count = getIntent().getIntExtra("count",0);

        totalTimeCountInMillisecondsTotal = (60 * count * 1000)+1000;

    }
    //------------------------------------------------------------------------------------------------------------------
    private void startTimerTotal(long times) {
        countDownTimerTotal = new CountDownTimer(times, 1000) {


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                time1 = leftTimeInMilliseconds;
                long seconds = leftTimeInMilliseconds / 1000;

                timer.setTextAppearance(getApplicationContext(),
                        R.style.TextAppearance_CastIntroOverlay_Title);


                timer.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));

                if(seconds==3.00){
                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                    }
                    end=0;
                    mediaPlayer1 = MediaPlayer.create(getBaseContext() , R.raw.countdown);
                    mediaPlayer1.start();

                    favButton.setEnabled(false);
                }


            }

            @Override
            public void onFinish() {
                timer.setText("00:00");

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
    //------------------------------------------------------------------------------------------------------------------

    private void startTimer(long times) {
        countDownTimer = new CountDownTimer(times, 1000) {


            @Override
            public void onTick(long leftTimeInMilliseconds) {
                time = leftTimeInMilliseconds;
                long seconds = leftTimeInMilliseconds / 1000;
                int seconds1 = (int) (leftTimeInMilliseconds / 1000);
                mprogressBar.setProgress(seconds1);


                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));

                if(seconds==8.00){
                    if(mediaPlayer1 == null) {

                        if (n == 0 ){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.chawasadhok_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);
                        }else if(n == 1){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.tadmala_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);
                        }else if(n == 2){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.paklooktoy_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);
                        }else if(n == 3){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.hugnungaiyara_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 4){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }
                            //Pak Sa Weg Rang
                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.paksawekrang_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 5){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.dubchalawa_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 6){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.kunyakjabling_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 7){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.montonangtan_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 8){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.salaufunpla_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }
                    }
                }

            }

            @Override
            public void onFinish() {
                textViewShowTime.setText("00:00");
                begin = 1;
                favButton.setEnabled(false);

                if (n == 0 ){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_cha_wa_sad_hok,"Cha-Wa Sad Hok",R.raw.chawasadhok_thai);
                    n=1;
                }else if(n == 1){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_tad_ma_la,"Tad Ma-la",R.raw.tadmala_thai);
                    n=2;
                }else if(n == 2){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_pak_look_toy,"Pak Look-Toy",R.raw.paklooktoy_thai);
                    n=3;
                }else if(n == 3){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_hak_ngong_ai_ra,"Hak Ngong Ai-ra",R.raw.hugnungaiyara_thai);
                    n=4;
                }else if(n == 4){
                    statusButton_2();
                    //Pak Sa Weg Rang
                    onFinishItem(R.drawable.gif_pak_sa_weg_rang,"Pak Sa Weg Rang",R.raw.paksawekrang_thai);
                    n=5;
                }else if(n == 5){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_dub_cha_la_wa,"Dub Cha-la-wa",R.raw.dubchalawa_thai);
                    n=6;

                }else if(n == 6){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_kun_yak_jab_ling,"Kun Yak Jab Ling",R.raw.kunyakjabling_thai);
                    n=7;

                }else if(n == 7){
                    statusButton_1();
                    onFinishItem(R.drawable.gif_mon_to_nang_tan,"Mon-to Nang Tan",R.raw.montonangtan_thai);
                    n=8;

                }else if(n == 8){
                    statusButton_3();
                    onFinishItem(R.drawable.gif_sa_lap_fun_pla,"Sa Lap Fun Pla",R.raw.salaufunpla_thai);
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
        favButton = (MenuItem) menu.findItem(R.id.action_music);
        favButton.setEnabled(false);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_end:
                endSound();
                stopSound();
                finish();

                break;
            case R.id.action_music:
                if(n == 0){
                    playSoundMusic( R.raw.salaufunpla_thai);
                }else  if(n == 1){
                    playSoundMusic( R.raw.chawasadhok_thai);
                }else  if(n == 2){
                    playSoundMusic( R.raw.tadmala_thai);
                }else  if(n == 3){
                    playSoundMusic( R.raw.paklooktoy_thai);
                }else  if(n == 4){
                    playSoundMusic( R.raw.hugnungaiyara_thai);
                }else  if(n == 5){
                    //Pak Sa Weg Rang
                    playSoundMusic( R.raw.paksawekrang_thai);
                }else  if(n == 6){
                    playSoundMusic( R.raw.dubchalawa_thai);
                }else  if(n == 7){
                    playSoundMusic( R.raw.kunyakjabling_thai);
                }else  if(n == 8){
                    playSoundMusic( R.raw.montonangtan_thai);
                }

                break;
        }
        return true;
    }
    //------------------------------------------------------------------------------------------------------------------

    public void stop_play(View view){
        if(x == 0){
            stopSound();
            if(end == 0){
                mediaPlayer1.start();
            }
        }
        else{
            stop_play_button.setImageResource(R.drawable.icn_pause);
            startTimer(time);
            startTimerTotal(time1);
            x =0;
            ((GifDrawable)gifImageView.getBackground()).start();
            mediaPlayer.start();

        }

    }
    //------------------------------------------------------------------------------------------------------------------
    public void next(View view){
        ((GifDrawable)gifImageView.getBackground()).start();
        stop_play_button.setImageResource(R.drawable.icn_pause);
        begin = 1;
        favButton.setEnabled(false);

        if (n == 0 ){
            statusButton_2();
            playSound(R.raw.chawasadhok_thai);
            nextBackgif(R.drawable.gif_cha_wa_sad_hok,"Cha-Wa Sad Hok");
            n=1;
        }else if(n == 1){
            statusButton_2();
            playSound(R.raw.tadmala_thai);
            nextBackgif(R.drawable.gif_tad_ma_la,"Tad Ma-la");
            n=2;
        }else if(n == 2){
            statusButton_2();
            playSound(R.raw.paklooktoy_thai);
            nextBackgif(R.drawable.gif_pak_look_toy,"Pak Look-Toy");
            n=3;
        }else if(n == 3){
            statusButton_2();
            playSound(R.raw.hugnungaiyara_thai);
            nextBackgif(R.drawable.gif_hak_ngong_ai_ra,"Hak Ngong Ai-ra");
            n=4;
        }else if(n == 4){
            statusButton_2();
            //Pak Sa Weg Rang
            playSound(R.raw.paksawekrang_thai);
            nextBackgif(R.drawable.gif_pak_sa_weg_rang,"Pak Sa Weg Rang");
            n=5;
        }else if(n == 5){
            statusButton_2();
            playSound(R.raw.dubchalawa_thai);
            nextBackgif(R.drawable.gif_dub_cha_la_wa,"Dub Cha-la-wa");
            n=6;
        }else if(n == 6){
            statusButton_2();
            playSound(R.raw.kunyakjabling_thai);
            nextBackgif(R.drawable.gif_kun_yak_jab_ling,"Kun Yak Jab Ling");
            n=7;
        }else if(n == 7){
            statusButton_1();
            playSound(R.raw.montonangtan_thai);
            nextBackgif(R.drawable.gif_mon_to_nang_tan,"Mon-to Nang Tan");
            n=8;
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    public void back(View view){
        ((GifDrawable)gifImageView.getBackground()).start();
        stop_play_button.setImageResource(R.drawable.icn_pause);
        begin = 1;
        favButton.setEnabled(false);

        if (n == 8){
            statusButton_2();
            playSound(R.raw.kunyakjabling_thai);
            nextBackgif(R.drawable.gif_kun_yak_jab_ling,"Kun Yak Jab Ling");
            n=7;
        }else if (n == 7){
            statusButton_2();
            playSound(R.raw.dubchalawa_thai);
            nextBackgif(R.drawable.gif_dub_cha_la_wa,"Dub Cha-la-wa");
            n=6;
        }else if (n == 6){
            statusButton_2();
            //Pak Sa Weg Rang
            playSound(R.raw.paksawekrang_thai);
            nextBackgif(R.drawable.gif_pak_sa_weg_rang,"Pak Sa Weg Rang");
            n=5;
        }else if (n == 5){
            statusButton_2();
            playSound(R.raw.hugnungaiyara_thai);
            nextBackgif(R.drawable.gif_hak_ngong_ai_ra,"Hak Ngong Ai-ra");
            n=4;
        }else if(n == 4){
            statusButton_2();
            playSound(R.raw.paklooktoy_thai);
            nextBackgif(R.drawable.gif_pak_look_toy,"Pak Look-Toy");
            n=3;
        }else if(n == 3){
            statusButton_2();
            playSound(R.raw.tadmala_thai);
            nextBackgif(R.drawable.gif_tad_ma_la,"Tad Ma-la");
            n=2;
        }else if(n == 2){
            statusButton_2();
            playSound(R.raw.chawasadhok_thai);
            nextBackgif(R.drawable.gif_cha_wa_sad_hok,"Cha-Wa Sad Hok");
            n=1;
        }else if(n == 1){
            statusButton_3();
            playSound(R.raw.salaufunpla_thai);
            nextBackgif(R.drawable.gif_sa_lap_fun_pla,"Sa Lap Fun Pla");
            n=0;
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onPause() {
        super.onPause();
        stopSound();
    }
    //------------------------------------------------------------------------------------------------------------------
    public void onBackPressed() {
        endSound();
        stop_play_button.setImageResource(R.drawable.icn_play);
        countDownTimer.cancel();
        countDownTimerTotal.cancel();
        ((GifDrawable)gifImageView.getBackground()).stop();
        finish();

    }
    //------------------------------------------------------------------------------------------------------------------
    public void playSound(int sound){
        if(mediaPlayer1==null) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext(), sound);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    if (begin == 1) {
                        begin = 0;
                        favButton.setEnabled(true);
                        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.begin);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                if (mediaPlayer != null) {
                                    mediaPlayer.stop();
                                }
                            }
                        });
                    }
                }
            });
        }

    }
    public void playSoundMusic(int sound){
        if(mediaPlayer1==null) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            mediaPlayer = MediaPlayer.create(getBaseContext(), sound);
            mediaPlayer.start();
        }

    }

    public void stopSound(){
        if (end == 0){
            if(mediaPlayer1.isPlaying()) {
                mediaPlayer1.pause();
            }
        }
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
        countDownTimerTotal.cancel();
        ((GifDrawable)gifImageView.getBackground()).stop();
        x =1;

    }
    public void endSound(){
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }

    }
    //----------------------------------------------------------------------------------------------------------------------
    public void statusButton_1(){
        previous_button.setEnabled(true);
        forward_button.setEnabled(false);
        previous_button.setAlpha(1f);
        forward_button.setAlpha(.5f);

    }
    public void statusButton_2(){
        forward_button.setEnabled(true);
        previous_button.setEnabled(true);
        previous_button.setAlpha(1f);
        forward_button.setAlpha(1f);

    }
    public void statusButton_3(){
        forward_button.setEnabled(true);
        previous_button.setEnabled(false);
        previous_button.setAlpha(.5f);
        forward_button.setAlpha(1f);

    }
    //----------------------------------------------------------------------------------------------------------------------
    public void nextBackgif(int gif,String Skill){
        gifImageView.setBackgroundResource(gif);
        txtSkillName.setText(Skill);
        countDownTimer.cancel();
        startTimer(totalTimeCountInMilliseconds);

    }
    public void onFinishItem(int gif,String Skill,int raw) {
        if (mediaPlayer1 == null) {
            gifImageView.setBackgroundResource(gif);
            txtSkillName.setText(Skill);
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = MediaPlayer.create(getBaseContext(), raw);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    if (begin == 1) {
                        begin = 0;
                        favButton.setEnabled(true);
                        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.begin);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                if (mediaPlayer != null) {
                                    mediaPlayer.stop();
                                }
                            }
                        });
                    }
                }
            });
            countDownTimer.cancel();
            startTimer(totalTimeCountInMilliseconds);

        }
    }


}
