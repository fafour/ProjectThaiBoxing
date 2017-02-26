package fafour.projectthaiboxing;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YourDetails1Activity extends AppCompatActivity {
    EditText editText1,editText2;
    private int year;
    private int month;
    private int day;
    private int year1;
    private int month1;
    private int day1;
    private static final int DATE_DIALOG_ID = 1;
    private String currentDate = "";
    private String currentDate1 = "";
    private String data = "";
    String time ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_details1);
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
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd");

        editText1.setText( ft.format(dNow));
        editText2.setText( ft.format(dNow));
        currentDate = ft.format(dNow);
        currentDate1 = ft.format(dNow);
    }
    public void payment(View view){
        Intent intent = getIntent();
        String txtname = intent.getStringExtra("Name");
        String txtlastName = intent.getStringExtra("LastName");
        String txtemail = intent.getStringExtra("Email");
        String txtcountry = intent.getStringExtra("Country");
        String txtyear = intent.getStringExtra("Year");
        String txtgender = intent.getStringExtra("Gender");

        Intent intent1 = new Intent(getApplicationContext(), YourDetails2Activity.class);
        intent1.putExtra("Name", txtname);
        intent1.putExtra("LastName",txtlastName);
        intent1.putExtra("Email", txtemail);
        intent1.putExtra("Country",txtcountry);
        intent1.putExtra("Year", txtyear);
        intent1.putExtra("Gender",txtgender);
        intent1.putExtra("ArrivalDate",editText1.getText().toString());
        intent1.putExtra("DepartureDate",editText2.getText().toString());
        startActivity(intent1);
        finish();
    }
    @TargetApi(Build.VERSION_CODES.N)
    public void selectDate(View view){

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        showDialog(DATE_DIALOG_ID);

    }
    @TargetApi(Build.VERSION_CODES.N)
    public void selectDate1(View view){
        Calendar c1 = Calendar.getInstance();
        year1 = c1.get(Calendar.YEAR);
        month1 = c1.get(Calendar.MONTH);
        day1 = c1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(YourDetails1Activity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        editText2.setText( year + "-" + (String.format("%02d", monthOfYear + 1  ))
                                + "-" + String.format("%02d", dayOfMonth  ));
                        currentDate1 = year + "-" + (String.format("%02d", monthOfYear + 1  ))
                                + "-" + String.format("%02d", dayOfMonth  );
                    }
                }, year1, month1, day1);
        dpd.show();
        SimpleDateFormat formatter1 = new SimpleDateFormat ("yyyy-MM-dd");
        try {
            time = currentDate;
            Date date1 = formatter1.parse(time);
            dpd.getDatePicker().setMinDate(date1.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        dpd.getDatePicker().setMaxDate(new Date().getTime());


    }
    private void updateDisplay() {
        currentDate = new StringBuilder().append(year).append("-")
                .append(String.format("%02d", month + 1  )).append("-").append(String.format("%02d", day  )).toString();

        Log.i("DATE", currentDate);

        SimpleDateFormat formatter2 = new SimpleDateFormat ("yyyy-MM-dd");
        try {

            Date date1 = formatter2.parse(currentDate);
            Date date2 = formatter2.parse(currentDate1);

            if(date2.before(date1)){
                editText2.setText(currentDate);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int j, int k) {
            year = i;
            month = j;
            day = k;
            updateDisplay();
            editText1.setText(currentDate);

        }
    };



    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog dialog = new DatePickerDialog(this, myDateSetListener, year, month,
                        day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                return dialog;


        }
        return null;
    }

}
