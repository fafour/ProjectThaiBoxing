package fafour.projectthaiboxing;

/**
 * Created by Fafour on 30/1/2560.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAllPackage extends Fragment {
    final int[] select_1 = {0};
    final int[] select_2 = {0};
    String nameBooking;
    int priceBooking;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_all_package, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Choose an option");
        list1.add("Basic");
        list1.add("Delux");
        list1.add("VIP");
        list1.add("Weight-Loss");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        List<String> list2 = new ArrayList<String>();
        list2.add("Choose an option");
        list2.add("1 Week");
        list2.add("1 Month");
        list2.add("3 Month");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);


        final TextView txt = (TextView) view.findViewById(R.id.txt);
        final TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        txt.setVisibility(View.GONE);
        txtPrice.setVisibility(View.GONE);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_1[0] = 0;
                    txt.setText("");
                    txtPrice.setText("");

                    txt.setVisibility(View.GONE);
                    txtPrice.setVisibility(View.GONE);

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Basic")) {
                    select_1[0] = 1;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_2[0] != 0) {
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("13,400 THB");
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("40,000 THB");
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("118,000 THB");
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Delux")) {
                    select_1[0] = 2;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_2[0] != 0) {
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("17,950 THB");
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("56,250 THB");
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("166,750 THB");
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("VIP")) {
                    select_1[0] = 3;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_2[0] != 0) {
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("21,200 THB");
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("72,500 THB");
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("215,500 THB");
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Weight-Loss")) {
                    select_1[0] = 4;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_2[0] != 0) {
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("23,100 THB");
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("60,600 THB");
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("177,400 THB");
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_2[0] = 0;
                    txt.setText("");
                    txtPrice.setText("");

                    txt.setVisibility(View.GONE);
                    txtPrice.setVisibility(View.GONE);
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Week")) {
                    txtPrice.setVisibility(View.VISIBLE);

                    select_2[0] = 1;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("13,400 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("17,950 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("21,200 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("23,100 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Month")) {
                    select_2[0] = 2;
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("40,000 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("56,250 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("72,500 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("60,600 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 Month")) {
                    select_2[0] = 3;
                    txtPrice.setVisibility(View.VISIBLE);

                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("118,000 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("166,750 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("215,500 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("177,400 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button booking = (Button) view.findViewById(R.id.booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollingTigerMuayThaiActivity.tv_cart.setText(MainActivity.booking.size() +"");
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Add Cart Success ");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        int no = 0;
                        try{
                            if( MainActivity.booking.size() == 0){
                                MainActivity.booking.add(new DataBooking(nameBooking,priceBooking,1));

                            }else {
                                for (int i = 0; i < MainActivity.booking.size(); i++) {
                                    if (MainActivity.booking.get(i).getBookingName().equals(nameBooking)) {
                                        MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                        continue;
                                    } else {
                                        no++;
                                    }
                                }
                                if ( MainActivity.booking.size() == no){
                                    MainActivity.booking.add(new DataBooking(nameBooking,priceBooking,1));
                                }
                            }
                        }catch (Exception x){

                        }
                    }
                });

                dialog.show();

            }
        });



        return view;
    }
}
