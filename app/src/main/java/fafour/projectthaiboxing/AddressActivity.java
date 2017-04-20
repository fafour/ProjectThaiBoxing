package fafour.projectthaiboxing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressActivity extends AppCompatActivity {

    EditText address_line_1,address_line_2,city,state,zip;
    TextInputLayout input_layout_address_line_1,input_layout_address_line_2,input_layout_city, input_layout_state,input_layout_zip;
    Spinner spinner1;
    final String PREFNAME = "DetailPreferences";
    final String  txtaddress_1 = "address_1";
    final String  txtaddress_2 = "address_2";
    final String txtcity = "city";
    final String txtstate = "state";
    final String txtzip = "zip";
    final String txtcountry = "country";

    String item_country;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String check = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
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

        input_layout_address_line_1 = (TextInputLayout) findViewById(R.id.input_layout_address_line_1);
        input_layout_address_line_2 = (TextInputLayout) findViewById(R.id.input_layout_address_line_2);
        input_layout_city = (TextInputLayout) findViewById(R.id.input_layout_city);
        input_layout_state = (TextInputLayout) findViewById(R.id.input_layout_state);
        input_layout_zip = (TextInputLayout) findViewById(R.id.input_layout_zip);

        sp = getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        address_line_1 = (EditText)findViewById(R.id.address_line_1);
        address_line_1.setText(sp.getString(txtaddress_1, ""));
        address_line_1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtaddress_1, s.toString());
                editor.commit();
            }
        });

        address_line_1.requestFocus();


        address_line_2 = (EditText)findViewById(R.id.address_line_2);
        address_line_2.setText(sp.getString(txtaddress_2, ""));
        address_line_2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtaddress_2, s.toString());
                editor.commit();
            }
        });

        city = (EditText)findViewById(R.id.city);
        city.setText(sp.getString(txtcity, ""));
        city.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtcity, s.toString());
                editor.commit();
            }
        });

        state = (EditText)findViewById(R.id.state);
        state.setText(sp.getString(txtstate, ""));
        state.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtstate, s.toString());
                editor.commit();
            }
        });

        zip = (EditText)findViewById(R.id.zip);
        zip.setText(sp.getString(txtzip, ""));
        zip.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString(txtzip, s.toString());
                editor.commit();
            }
        });

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("COUNTRY:");
        list1.add("AFGHANISTAN");
        list1.add("ALBANIA");
        list1.add("ALGERIA");
        list1.add("ANGOLA");
        list1.add("ANGUILLA");
        list1.add("ANTIGUA");
        list1.add("ARGENTINA");
        list1.add("ARMENIA");
        list1.add("ASCENSION");
        list1.add("AUSTRALIA");
        list1.add("AUSTRIA");
        list1.add("AZERBAIJAN");
        list1.add("BAHAMAS");
        list1.add("BAHRAIN");
        list1.add("BALERIC IS.");
        list1.add("BANGLADESH");
        list1.add("BARBADOS");
        list1.add("BELARUS");
        list1.add("BELGIUM");
        list1.add("BELIZE");
        list1.add("BENIN");
        list1.add("BERMUDA");
        list1.add("BHUTAN");
        list1.add("BOLIVIA");
        list1.add("BOSNIA & HERZEGOVINA");
        list1.add("BOTSWANA");
        list1.add("BRAZIL");
        list1.add("BRITISH INDIAN OCEAN TERRITORY (U.K.)");
        list1.add("BRITISH VIRGIN IS. (U.K.)");
        list1.add("BRUNEI");
        list1.add("BULGARIA");
        list1.add("BURKINA FASO");
        list1.add("BURUNDI");
        list1.add("CAMBODIA");
        list1.add("CAMAROON");
        list1.add("CANADA");
        list1.add("CANARY IS.");
        list1.add("CAPE VERDE");
        list1.add("CAROLINE IS.");
        list1.add("CAYMEN IS.");
        list1.add("CENTRAL AFRICAN REP.");
        list1.add("CHAD");
        list1.add("CHILE");
        list1.add("CHINA");
        list1.add("COLOMBIA");
        list1.add("COMOROS");
        list1.add("CONGO REP.");
        list1.add("CONGO DEM");
        list1.add("COOK IS.");
        list1.add("COSTA RICA");
        list1.add("COTE D'IVORE");
        list1.add("CROATIA");
        list1.add("CUBA");
        list1.add("CYPRUS");
        list1.add("CZECH");
        list1.add("DENMARK");
        list1.add("DJIBOUTI");
        list1.add("DOMINICA");
        list1.add("DOMINICAN");
        list1.add("EAST TIMOR");
        list1.add("ECUADOR");
        list1.add("EGYPT");
        list1.add("EL SALVADOR");
        list1.add("EQUATORIAL GUINEA");
        list1.add("ERITREA");
        list1.add("ESTONIA");
        list1.add("ETHIOPIA");
        list1.add("FALKLAND IS.");
        list1.add("FAROE IS.");
        list1.add("FIJI");
        list1.add("FINLAND");
        list1.add("FRANCE");
        list1.add("FRENCH GUIANA");
        list1.add("FRENCH POLYNESIA");
        list1.add("GABON");
        list1.add("GAMBIA");
        list1.add("GEORGIA");
        list1.add("GERMANY");
        list1.add("GHANA");
        list1.add("GIBRALTAR");
        list1.add("GREAT BRITAIN & NORTHERN IRELANDS");
        list1.add("GREECE");
        list1.add("GREENLAND");
        list1.add("GRENADA");
        list1.add("GUADELOUPE");
        list1.add("GUAM");
        list1.add("GUATEMALA");
        list1.add("GUINEA");
        list1.add("GUINEA-BISSAU");
        list1.add("GUYANA");
        list1.add("HAITI");
        list1.add("HONDURUS");
        list1.add("HONG KONG");
        list1.add("HUNGARY");
        list1.add("ICELAND");
        list1.add("INDEA");
        list1.add("INDONESIA");
        list1.add("IRAN");
        list1.add("IRAQ");
        list1.add("IRELAND");
        list1.add("ISRAEL");
        list1.add("ITALY");
        list1.add("JAMAICA");
        list1.add("JAPAN");
        list1.add("JORDAN");
        list1.add("KAZAKHSTAN");
        list1.add("KENYA");
        list1.add("KIRIBATI");
        list1.add("KOREA");
        list1.add("KOREA (REP. OF)");
        list1.add("UWAKIT");
        list1.add("KYRGYZSTAN");
        list1.add("LAOS");
        list1.add("LATVIA");
        list1.add("LEBANON");
        list1.add("LESOTHO");
        list1.add("LIBERIA");
        list1.add("LIBYA");
        list1.add("LEICHTENSTEIN");
        list1.add("LITHUANIA");
        list1.add("LUXEMBOURG");
        list1.add("MACAO");
        list1.add("MACEDONIA");
        list1.add("MADEIRA");
        list1.add("MALAWI");
        list1.add("MALAYSIA");
        list1.add("MALDIVES");
        list1.add("MALI");
        list1.add("MALTA");
        list1.add("MARIANA IS.");
        list1.add("MARSHALL IS.");
        list1.add("MARTINIQUE");
        list1.add("MAURITANIA");
        list1.add("MAURITIUS");
        list1.add("MEXICO");
        list1.add("MOLDOVA");
        list1.add("MONACO");
        list1.add("MONGOLIA");
        list1.add("MONSERRAT");
        list1.add("MOROCCO");
        list1.add("MOZAMBIQUE");
        list1.add("MYANMAR");
        list1.add("NAMIBIA");
        list1.add("NAURU");
        list1.add("NETHERLANDS");;
        list1.add("NEW CALEDONIA");
        list1.add("NEW ZEALAND");
        list1.add("NICARAGUA");
        list1.add("NIGER");
        list1.add("NIGERIA");
        list1.add("NIEU");
        list1.add("NORFOLK IS.");
        list1.add("NORWAY");
        list1.add("OMAN");
        list1.add("PAKISTAN");
        list1.add("PANAMA");
        list1.add("PAPUA NEW GUINEA");
        list1.add("PARAGUAY");
        list1.add("PERU");
        list1.add("PHILIPPINES");
        list1.add("PITCAIRN IS.");
        list1.add("POLAND");
        list1.add("PORTUGAL");
        list1.add("PUERTO RICO");
        list1.add("QATAR");
        list1.add("REUNION");
        list1.add("ROMANIA");
        list1.add("RUSSIA");
        list1.add("RWANDA");
        list1.add("ST.CHRISTOPHER (KITTS) & NEVIS");
        list1.add("ST.HELENA");
        list1.add("ST.LUCIA");
        list1.add("ST.PIERRE & MIQUELON");
        list1.add("ST.VINCENT AND THE GRENADINES");
        list1.add("SAMOA");
        list1.add("SAOTOME & PRINCIPE");
        list1.add("SAUDI ARABIA");
        list1.add("SENEGAL");
        list1.add("SEYCHELLES");
        list1.add("SIERRA LEONE");
        list1.add("SINGAPORE");
        list1.add("SLOVAKIA");
        list1.add("SLOVENIA");
        list1.add("SOLOMON IS.");
        list1.add("SOMALIA");
        list1.add("SOUTH AFRICA");
        list1.add("SPAIN");
        list1.add("SRI LANKA");
        list1.add("SUDAN");
        list1.add("SURINAME");
        list1.add("SWAZILAND");
        list1.add("SWEDEN");
        list1.add("SWITZERLAND");
        list1.add("SYRIA");
        list1.add("TAIWAN");
        list1.add("TAJIKISTAN");
        list1.add("TANZANIA");
        list1.add("THAILAND");
        list1.add("TOGO");
        list1.add("TOKELAU");
        list1.add("TONGA");
        list1.add("TRINIDAD & TOBAGO");
        list1.add("TRISTAN DA CUNHA");
        list1.add("TUNISIA");
        list1.add("TURKEY");
        list1.add("TURKMENISTAN");
        list1.add("TURKS & CAICOS");
        list1.add("TUVALU");
        list1.add("UGANDA");
        list1.add("UKRAIN");
        list1.add("UNITED ARAB EMIRATES");
        list1.add("UNITED STATES OF AMERICA");
        list1.add("URUGUAY");
        list1.add("UZBEKISTAN");
        list1.add("VANUATU");
        list1.add("VATICAN");
        list1.add("VENEZUELA");
        list1.add("VIET NAM");
        list1.add("YEMEN");
        list1.add("YUGOSLAVIA");
        list1.add("ZAMBIA");
        list1.add("ZIMBABWE");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        spinner1.setSelection(dataAdapter1.getPosition(sp.getString(txtcountry, "")));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                item_country = parent.getItemAtPosition(position).toString();
                editor.putString(txtcountry, parent.getItemAtPosition(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void click(View view){
        progressDialog = ProgressDialog.show(AddressActivity.this, "Please wait ...",  "Task in progress ...", true);
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
        String txt_address_line_1 = address_line_1.getText().toString();
        String txt_address_line_2 = address_line_2.getText().toString();
        String txt_city = city.getText().toString();
        String txt_state = state.getText().toString();
        String txt_zip = zip.getText().toString();

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String email = getIntent().getStringExtra("email");
        String vat = getIntent().getStringExtra("vat");
        String company_name = getIntent().getStringExtra("company_name");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");


        String description ="VAT/SST : "+vat+"\n"+
                "Company Name : "+company_name+"\n"+
                "Phone Number : "+phoneNumber;
        String address ="";
        if(txt_address_line_2.isEmpty()){
            address =txt_address_line_1+"\n"+
                    "City : "+txt_city+"\n"+
                    "State/Province/Region : "+txt_state+"\n"+
                    "Zip : "+txt_zip+"\n"+
                    "Conntry : "+item_country;
        }else {
            address =txt_address_line_1+"  "+txt_address_line_2+"\n"+
                    "City : "+txt_city+"\n"+
                    "State/Province/Region : "+txt_state+"\n"+
                    "Zip : "+txt_zip+"\n"+
                    "Conntry : "+item_country;
        }
        String User = name+"  "+surname;



        if(!txt_address_line_1.isEmpty() && !txt_city.isEmpty() && !txt_state.isEmpty() && !txt_zip.isEmpty() && !item_country.equals("COUNTRY:") ){
            input_layout_address_line_1.setErrorEnabled(false);
            input_layout_address_line_1.setError(null);
            input_layout_city.setErrorEnabled(false);
            input_layout_city.setError(null);
            input_layout_state.setErrorEnabled(false);
            input_layout_state.setError(null);
            input_layout_zip.setErrorEnabled(false);
            input_layout_zip.setError(null);

            Intent intent = new Intent(getApplicationContext(), CheckProductsActivity.class);
            intent.putExtra("user",User);
            intent.putExtra("email",email);
            intent.putExtra("address",address);
            intent.putExtra("description",description);
            intent.putExtra("item_country",item_country);
            startActivity(intent);
            finish();



        }else{
            if(txt_address_line_1.isEmpty()){
                input_layout_address_line_1.setError("Invalid Address");
            }else {
                input_layout_address_line_1.setErrorEnabled(false);
                input_layout_address_line_1.setError(null);
            }

            if(txt_city.isEmpty()){
                input_layout_city.setError("Invalid City");
            }else {
                input_layout_city.setErrorEnabled(false);
                input_layout_city.setError(null);
            }

            if(txt_state.isEmpty()){
                input_layout_state.setError("Invalid State");
            }else {
                input_layout_state.setErrorEnabled(false);
                input_layout_state.setError(null);
            }

            if(txt_zip.isEmpty()){
                input_layout_zip.setError("Invalid Zip");
            }else {
                input_layout_zip.setErrorEnabled(false);
                input_layout_zip.setError(null);
            }

            if(item_country.equals("COUNTRY:")){
                final AlertDialog.Builder dialog = new AlertDialog.Builder(AddressActivity.this);
                dialog.setTitle("Country Is Null...");
                dialog.setMessage("Please Select Country...");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.show();

            }

        }
    }

    //------------------------------------------------------------------------------------------------
    public void show_ms(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddressActivity.this);
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
