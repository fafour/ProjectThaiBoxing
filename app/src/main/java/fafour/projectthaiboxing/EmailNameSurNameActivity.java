package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.lamudi.phonefield.PhoneInputLayout;

public class EmailNameSurNameActivity extends AppCompatActivity {
    PhoneInputLayout phoneInputLayout;
    final String PREFNAME = "DetailPreferences";
    final String  firstname = "Firstname";
    final String  surname = "surname";
    final String txtemail = "email";
    final String txtvat = "vat";
    final String txtcompany_name = "company_name";
    final String tel = "tel";

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText email;
    EditText name;
    EditText sname;
    EditText company_name;
    EditText vat;

    TextInputLayout input_layout_name,input_layout_sname,input_layout_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_name_sur_name);
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

        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_sname = (TextInputLayout) findViewById(R.id.input_layout_sname);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);

        sp = getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        name = (EditText)findViewById(R.id.name);
        name.setText(sp.getString(firstname, ""));
        name.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(firstname, s.toString());
                editor.commit();
            }
        });

        name.requestFocus();


        sname = (EditText)findViewById(R.id.sname);
        sname.setText(sp.getString(surname, ""));
        sname.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(surname, s.toString());
                editor.commit();
            }
        });

        email = (EditText)findViewById(R.id.email);
        email.setText(sp.getString(txtemail, ""));
        email.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtemail, s.toString());
                editor.commit();
            }
        });


        phoneInputLayout = (PhoneInputLayout) findViewById(R.id.phone_input_layout);
        phoneInputLayout.setHint(R.string.phone_hint);
        phoneInputLayout.setDefaultCountry("TH");
        



        company_name = (EditText)findViewById(R.id.company_name);
        company_name.setText(sp.getString(txtcompany_name, ""));
        company_name.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtcompany_name, s.toString());
                editor.commit();
            }
        });

        vat = (EditText)findViewById(R.id.vat);
        vat.setText(sp.getString(txtvat, ""));
        vat.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtvat, s.toString());
                editor.commit();
            }
        });




    }
    public  void  click(View view){
        String txt_email = email.getText().toString();
        String txt_username = name.getText().toString();
        String txt_surname = sname.getText().toString();
        String txt_vat = vat.getText().toString();
        String txt_company_name = company_name.getText().toString();
        String phoneNumber = phoneInputLayout.getPhoneNumber();
        String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(txt_email.matches(emailRegex) && phoneInputLayout.isValid() && !txt_username.isEmpty() && !txt_surname.isEmpty()){
            phoneInputLayout.setError(null);
            Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
            intent.putExtra("name",txt_username);
            intent.putExtra("surname",txt_surname);
            intent.putExtra("email",txt_email);
            intent.putExtra("vat",txt_vat);
            intent.putExtra("company_name",txt_company_name);
            intent.putExtra("phoneNumber",phoneNumber);
            startActivity(intent);


        }else{
            if(!phoneInputLayout.isValid()) {
                phoneInputLayout.setError("Invalid Phone Number");
            }else {
                phoneInputLayout.setError(null);
            }

            if(txt_username.isEmpty()){
                input_layout_name.setError("Invalid Name");
            }else {
                input_layout_name.setErrorEnabled(false);
            }

            if(txt_surname.isEmpty()){
                input_layout_sname.setError("Invalid Surname");
            }else {
                input_layout_sname.setErrorEnabled(false);
            }


            if (!txt_email.matches(emailRegex)) {
                input_layout_email.setError("Invalid Email");
            }else {
                input_layout_email.setErrorEnabled(false);
            }


        }

    }

}
