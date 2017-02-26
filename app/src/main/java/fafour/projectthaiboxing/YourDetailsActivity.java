package fafour.projectthaiboxing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import java.util.ArrayList;
import java.util.List;

public class YourDetailsActivity extends AppCompatActivity  {
    String country,year,gender;
    EditText fName,lName,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_details);
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

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        email = (EditText) findViewById(R.id.email);


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Select a country");
        list1.add("Thailand");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item,list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                country = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list2 = new ArrayList<String>();
        list2.add("Select option");
        list2.add("2000");
        list2.add("1999");
        list2.add("1998");
        list2.add("1997");
        list2.add("1996");
        list2.add("1995");
        list2.add("1994");
        list2.add("1993");
        list2.add("1992");
        list2.add("1991");
        list2.add("1990");
        list2.add("1989");
        list2.add("1988");
        list2.add("1987");
        list2.add("1986");
        list2.add("1985");
        list2.add("1984");
        list2.add("1983");
        list2.add("1982");
        list2.add("1981");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item,list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                year = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        List<String> list3 = new ArrayList<String>();
        list3.add("Select option");
        list3.add("Male");
        list3.add("Female");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item,list3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                gender = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }
    public void payment(View view){
        String txtname = fName.getText().toString();
        String txtLname = lName.getText().toString();
        String txtemail = email.getText().toString();

        String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (!txtname.isEmpty() &&
                !txtLname.isEmpty() &&
                txtemail.matches(emailRegex) &&
                !country.equals("Select a country") &&
                !year.equals("Select option") &&
                !gender.equals("Select option")
                ){
            Intent intent = new Intent(getApplicationContext(), YourDetails1Activity.class);
            intent.putExtra("Name", txtname);
            intent.putExtra("LastName",txtLname);
            intent.putExtra("Email", txtemail);
            intent.putExtra("Country",country);
            intent.putExtra("Year", year);
            intent.putExtra("Gender",gender);
            startActivity(intent);
            finish();
        }else {
            if (txtname.isEmpty()) {
                fName.setError("First Name is Empty");
            }else {
                fName.setError(null);
            }


            if (txtLname.isEmpty()) {
                lName.setError("Last Name is Empty");
            }else {
                lName.setError(null);
            }


            if (!txtemail.matches(emailRegex)) {
                email.setError("Email is Wrong");
            }else {
                email.setError(null);
            }

            if(country.equals("Select a country") || year.equals("Select option") || gender.equals("Select option")){
                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(YourDetailsActivity.this);
                dialog1.setTitle("Select Item");
                dialog1.setCancelable(true);
                dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog1.show();

            }

        }




//        BackgroundMail.newBuilder(this)
//                .withUsername("fafour.se09@gmail.com")
//                .withPassword("fafour09")
//                .withMailto(txtemail)
//                .withSubject("Tiger Muay Thai")
//                .withBody(txtname+"  "+"  "+txtLname+"  "+country+"   "+year+"   "+gender)
//                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
//                    @Override
//                    public void onSuccess() {
//                        //do some magic
//                    }
//                })
//                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
//                    @Override
//                    public void onFail() {
//                        //do some magic
//                    }
//                })
//                .send();
//
//        finish();


    }

}
