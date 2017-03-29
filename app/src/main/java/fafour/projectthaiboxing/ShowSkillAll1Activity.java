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

public class ShowSkillAll1Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_show_skill_all1);
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

        //Bid Hnang Na-ka
        gifImageView.setBackgroundResource(R.drawable.gif_bid_hnang_na_ka);
        txtSkillName.setText("Bid Hnang Na-ka");

        mediaPlayer = MediaPlayer.create(this, R.raw.bidhangnaka_thai);
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
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.ainaotankkrich_thai);
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
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.yokkaoprasumain_thai);
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
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.monyanlak_thai);
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
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.jerakeyfadhang_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 4){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.viroonhogkab_thai);
                                    mediaPlayer.start();
                                }
                            });

                            favButton.setEnabled(false);

                        }else if(n == 5){
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                            }
                            //Bid Hnang Na-ka

                            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.next_to);
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.bidhangnaka_thai);
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
                    onFinishItem(R.drawable.gif_ai_nhao_tank_krit,"Ai-Nhao Tank Krit",R.raw.ainaotankkrich_thai);
                    n=1;
                }else if(n == 1){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_yok_kao_pa_su_main,"Yok Kao Pa-su-main",R.raw.yokkaoprasumain_thai);
                    n=2;
                }else if(n == 2){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_mon_yan_lhak,"Mon Yan Lhak",R.raw.monyanlak_thai);
                    n=3;
                }else if(n == 3){
                    statusButton_2();
                    onFinishItem(R.drawable.gif_jara_kay_fad_hnag,"Jara-kay Fad-hnag",R.raw.jerakeyfadhang_thai);
                    n=4;
                }else if(n == 4){
                    statusButton_1();
                    onFinishItem(R.drawable.gif_wi_run_hok_kap,"Wi-run Hok Kap",R.raw.viroonhogkab_thai);
                    n=5;
                }else if(n == 5){
                    statusButton_3();
                    //Bid Hnang Na-ka
                    onFinishItem(R.drawable.gif_bid_hnang_na_ka,"Bid Hnang Na-ka",R.raw.bidhangnaka_thai);
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
                    //Bid Hnang Na-ka
                    playSoundMusic( R.raw.bidhangnaka_thai);
                }else  if(n == 1){
                    playSoundMusic( R.raw.ainaotankkrich_thai);
                }else  if(n == 2){
                    playSoundMusic( R.raw.yokkaoprasumain_thai);
                }else  if(n == 3){
                    playSoundMusic( R.raw.monyanlak_thai);
                }else  if(n == 4){
                    playSoundMusic( R.raw.jerakeyfadhang_thai);
                }else  if(n == 5){
                    playSoundMusic( R.raw.viroonhogkab_thai);
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
            playSound(R.raw.ainaotankkrich_thai);
            nextBackgif(R.drawable.gif_ai_nhao_tank_krit,"Ai-Nhao Tank Krit");
            n=1;
        }else if(n == 1){
            statusButton_2();
            playSound(R.raw.yokkaoprasumain_thai);
            nextBackgif(R.drawable.gif_yok_kao_pa_su_main,"Yok Kao Pa-su-main");
            n=2;
        }else if(n == 2){
            statusButton_2();
            playSound(R.raw.monyanlak_thai);
            nextBackgif(R.drawable.gif_mon_yan_lhak,"Mon Yan Lhak");
            n=3;
        }else if(n == 3){
            statusButton_2();
            playSound(R.raw.jerakeyfadhang_thai);
            nextBackgif(R.drawable.gif_jara_kay_fad_hnag,"Jara-kay Fad-hnag");
            n=4;
        }
        else if(n == 4){
            statusButton_1();
            playSound(R.raw.viroonhogkab_thai);
            nextBackgif(R.drawable.gif_wi_run_hok_kap,"Wi-run Hok Kap");
            n=5;
        }


    }
    //------------------------------------------------------------------------------------------------------------------
    public void back(View view){
        ((GifDrawable)gifImageView.getBackground()).start();
        stop_play_button.setImageResource(R.drawable.icn_pause);
        begin = 1;
        favButton.setEnabled(false);

        if (n == 5 ){
            statusButton_2();
            playSound(R.raw.jerakeyfadhang_thai);
            nextBackgif(R.drawable.gif_jara_kay_fad_hnag,"Jara-kay Fad-hnag");
            n=4;
        }else if(n == 4){
            statusButton_2();
            playSound(R.raw.monyanlak_thai);
            nextBackgif(R.drawable.gif_mon_yan_lhak,"Mon Yan Lhak");
            n=3;
        }else if(n == 3){
            statusButton_2();
            playSound(R.raw.yokkaoprasumain_thai);
            nextBackgif(R.drawable.gif_yok_kao_pa_su_main,"Yok Kao Pa-su-main");
            n=2;
        }else if(n == 2){
            statusButton_2();
            playSound(R.raw.ainaotankkrich_thai);
            nextBackgif(R.drawable.gif_ai_nhao_tank_krit,"Ai-Nhao Tank Krit");
            n=1;
        }else if(n == 1){
            statusButton_3();
            //Bid Hnang Na-ka
            playSound(R.raw.bidhangnaka_thai);
            nextBackgif(R.drawable.gif_bid_hnang_na_ka,"Bid Hnang Na-ka");
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
