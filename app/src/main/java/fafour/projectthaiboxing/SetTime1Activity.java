package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SetTime1Activity extends AppCompatActivity {
    TextView txtcount;
    Button button1,button2;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time1);
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

        txtcount = (TextView) findViewById(R.id.txtcount);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setEnabled(false);
        button2.setEnabled(true);
        button1.setAlpha(.5f);
        button2.setAlpha(1f);



    }
    public void click(View view){
        Intent intent = new Intent(getApplicationContext(), ShowSkillAll1Activity.class);
        intent.putExtra("count",i);
        startActivity(intent);
        finish();
    }
    public void click1(View view){
        if(i == 1){
            button1.setEnabled(false);
            button2.setEnabled(true);
            button1.setAlpha(.5f);
            button2.setAlpha(1f);

        }else {
            button2.setEnabled(true);
            button1.setEnabled(true);
            button2.setAlpha(1f);
            button1.setAlpha(1f);

            i--;
            txtcount.setText(String.valueOf(i));
            if(i == 1){
                button1.setEnabled(false);
                button2.setEnabled(true);
                button1.setAlpha(.5f);
                button2.setAlpha(1f);

            }
        }

    }
    public void click2(View view){
        if(i==60){
            button2.setEnabled(false);
            button1.setEnabled(true);
            button2.setAlpha(.5f);
            button1.setAlpha(1f);
        }else{
            button2.setEnabled(true);
            button1.setEnabled(true);
            button2.setAlpha(1f);
            button1.setAlpha(1f);

            i++;
            txtcount.setText(String.valueOf(i));
            if(i==60){
                button2.setEnabled(false);
                button1.setEnabled(true);
                button2.setAlpha(.5f);
                button1.setAlpha(1f);
            }

        }

    }

}
