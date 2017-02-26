package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

public class YourDetails2Activity extends AppCompatActivity {
    EditText textMultiLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_details2);
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
        textMultiLine = (EditText) findViewById(R.id.textMultiLine);


    }
    public void payment(View view){
        String txtMultiLine = textMultiLine.getText().toString();

        Intent intent = getIntent();
        String txtname = intent.getStringExtra("Name");
        String txtlastName = intent.getStringExtra("LastName");
        String txtemail = intent.getStringExtra("Email");
        String txtcountry = intent.getStringExtra("Country");
        String txtyear = intent.getStringExtra("Year");
        String txtgender = intent.getStringExtra("Gender");
        String txtArrivalDate = intent.getStringExtra("ArrivalDate");
        String txtDepartureDate = intent.getStringExtra("DepartureDate");

        BackgroundMail.newBuilder(this)
                .withUsername("fafour.se09@gmail.com")
                .withPassword("fafour09")
                .withMailto(txtemail)
                .withSubject("Tiger Muay Thai")
                .withBody("Hello")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        finish();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                .send();

    }

}
