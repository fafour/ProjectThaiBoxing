package fafour.projectthaiboxing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmailNameSurNameActivity extends AppCompatActivity {
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
    EditText phone_number;
    private CircleImageView imgProfilePic;
    TextInputLayout input_layout_name,input_layout_sname,input_layout_email,input_layout_phone_number;
    String check = null;
    ProgressDialog progressDialog;

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
        imgProfilePic = (CircleImageView ) findViewById(R.id.imageView);
        new EmailNameSurNameActivity.DownloadImage((CircleImageView) findViewById(R.id.imageView)).execute(MainActivity.img);

        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_sname = (TextInputLayout) findViewById(R.id.input_layout_sname);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_phone_number = (TextInputLayout) findViewById(R.id.input_layout_phone_number);

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


        phone_number = (EditText)findViewById(R.id.phone_number);
        phone_number.setText(sp.getString(tel, ""));
        phone_number.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(tel, s.toString());
                editor.commit();
            }
        });

    }
    public  void  click(View view){
        progressDialog = ProgressDialog.show(EmailNameSurNameActivity.this, "Please wait ...",  "Task in progress ...", true);
        progressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    check_stock();
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
                progressDialog.dismiss();

                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (check != null) {
                            show_ms();
                        } else {
                            next_page();
                        }
                    }

                });

            }
        }).start();


    }
    //------------------------------------------------------------------------------------------------
    public void next_page(){
        String txt_email = email.getText().toString();
        String txt_username = name.getText().toString();
        String txt_surname = sname.getText().toString();
        String txt_vat = vat.getText().toString();
        String txt_company_name = company_name.getText().toString();
        String txt_pnone = phone_number.getText().toString();
        String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(txt_email.matches(emailRegex) && !txt_pnone.isEmpty() && !txt_username.isEmpty() && !txt_surname.isEmpty()){
            input_layout_phone_number.setErrorEnabled(false);
            input_layout_phone_number.setError(null);
            input_layout_name.setErrorEnabled(false);
            input_layout_name.setError(null);
            input_layout_sname.setErrorEnabled(false);
            input_layout_sname.setError(null);
            input_layout_email.setErrorEnabled(false);
            input_layout_email.setError(null);

            Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
            intent.putExtra("name",txt_username);
            intent.putExtra("surname",txt_surname);
            intent.putExtra("email",txt_email);
            intent.putExtra("vat",txt_vat);
            intent.putExtra("company_name",txt_company_name);
            intent.putExtra("phoneNumber",txt_pnone);
            startActivity(intent);
            finish();



        }else{
            if(txt_pnone.isEmpty()){
                input_layout_phone_number.setError("Invalid Phone Number");
            }else {
                input_layout_phone_number.setErrorEnabled(false);
                input_layout_phone_number.setError(null);
            }

            if(txt_username.isEmpty()){
                input_layout_name.setError("Invalid Name");
            }else {
                input_layout_name.setErrorEnabled(false);
                input_layout_name.setError(null);
            }

            if(txt_surname.isEmpty()){
                input_layout_sname.setError("Invalid Surname");
            }else {
                input_layout_sname.setErrorEnabled(false);
                input_layout_sname.setError(null);
            }


            if (!txt_email.matches(emailRegex)) {
                input_layout_email.setError("Invalid Email");
            }else {
                input_layout_email.setErrorEnabled(false);
                input_layout_email.setError(null);
            }


        }
    }

    //------------------------------------------------------------------------------------------------
    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    //------------------------------------------------------------------------------------------------
    public void show_ms(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EmailNameSurNameActivity.this);
        dialog.setTitle("Products Have Changed...");
        dialog.setMessage("Please Select A New Product...");
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.listBuy.clear();
                finish();
            }
        });

        dialog.show();

    }
    //------------------------------------------------------------------------------------------------
    public void check_stock(){
        int socketTimeout = 100;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        for (int i = 0; i < MainActivity.listBuy.size(); i++) {
            if (MainActivity.listBuy.get(i).getType() == 1) {
                String Url = connect_web_service.url+"check_stock_1.php";
                final int finalI = i;
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue1 = Volley.newRequestQueue(this);
                stringRequest1.setRetryPolicy(policy);
                requestQueue1.add(stringRequest1);


            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("8 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_8oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue2 = Volley.newRequestQueue(this);
                stringRequest2.setRetryPolicy(policy);
                requestQueue2.add(stringRequest2);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("10 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest3 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_10oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue3 = Volley.newRequestQueue(this);
                stringRequest3.setRetryPolicy(policy);
                requestQueue3.add(stringRequest3);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("12 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest4 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_12oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue4 = Volley.newRequestQueue(this);
                stringRequest4.setRetryPolicy(policy);
                requestQueue4.add(stringRequest4);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("14 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest5 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_14oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue5 = Volley.newRequestQueue(this);
                stringRequest5.setRetryPolicy(policy);
                requestQueue5.add(stringRequest5);

            }
            else if (MainActivity.listBuy.get(i).getType() == 2 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("16 oz")) {
                String Url = connect_web_service.url+"check_stock_2.php";
                final int finalI = i;
                StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_16oz") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue6 = Volley.newRequestQueue(this);
                stringRequest6.setRetryPolicy(policy);
                requestQueue6.add(stringRequest6);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XS")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xs") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue7 = Volley.newRequestQueue(this);
                stringRequest7.setRetryPolicy(policy);
                requestQueue7.add(stringRequest7);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest8 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_s") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue8 = Volley.newRequestQueue(this);
                stringRequest8.setRetryPolicy(policy);
                requestQueue8.add(stringRequest8);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest9 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_m") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue9 = Volley.newRequestQueue(this);
                stringRequest9.setRetryPolicy(policy);
                requestQueue9.add(stringRequest9);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest10 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_l") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue10 = Volley.newRequestQueue(this);
                stringRequest10.setRetryPolicy(policy);
                requestQueue10.add(stringRequest10);
            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest11 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue11 = Volley.newRequestQueue(this);
                stringRequest11.setRetryPolicy(policy);
                requestQueue11.add(stringRequest11);

            }
            else if (MainActivity.listBuy.get(i).getType() == 3 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XXL")) {
                String Url = connect_web_service.url+"check_stock_3.php";
                final int finalI = i;
                StringRequest stringRequest12 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xxl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue12 = Volley.newRequestQueue(this);
                stringRequest12.setRetryPolicy(policy);
                requestQueue12.add(stringRequest12);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("S")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest13 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_s") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue13 = Volley.newRequestQueue(this);
                stringRequest13.setRetryPolicy(policy);
                requestQueue13.add(stringRequest13);


            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("M")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest14 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_m") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue14 = Volley.newRequestQueue(this);
                stringRequest14.setRetryPolicy(policy);
                requestQueue14.add(stringRequest14);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("L")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest15 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_l") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue15 = Volley.newRequestQueue(this);
                stringRequest15.setRetryPolicy(policy);
                requestQueue15.add(stringRequest15);

            }
            else if (MainActivity.listBuy.get(i).getType() == 4 &&
                    MainActivity.listBuy.get(i).getAccessoriesSize().equals("XL")) {
                String Url = connect_web_service.url+"check_stock_4.php";
                final int finalI = i;
                StringRequest stringRequest16 = new StringRequest(Request.Method.POST, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    JSONObject jsonResponse = jsonarray.getJSONObject(0);
                                    if (jsonResponse.getInt("stock_xl") - MainActivity.listBuy.get(finalI).getAccessoriesNum() <0){
                                        check = "NOT_OK";
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name_item", MainActivity.listBuy.get(finalI).getAccessoriesName());
                        return params;
                    }

                };
                RequestQueue requestQueue16 = Volley.newRequestQueue(this);
                stringRequest16.setRetryPolicy(policy);
                requestQueue16.add(stringRequest16);

            }

        }


    }


}
