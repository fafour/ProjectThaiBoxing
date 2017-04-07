package fafour.projectthaiboxing;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fafour on 31/1/2560.
 */

public class TabFragmentChooseAddOns extends Fragment {
    final int[] select_1 = {0};
    String itemName;
    int priceBooking;

    final int[] select_2 = {0};
    String itemName1;
    int priceBooking1;

    final int[] select_3 = {0};
    String itemName2;
    int priceBooking2;

    final int[] select_4 = {0};
    String itemName3;
    int priceBooking3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_add_ons, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Choose an option");
        list1.add("1 session");
        list1.add("10 session");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        final TextView txt1 = (TextView) view.findViewById(R.id.txt1);
        final TextView txtPrice1 = (TextView) view.findViewById(R.id.txtPrice1);
        txt1.setVisibility(View.GONE);
        txtPrice1.setVisibility(View.GONE);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_1[0] = 0;
                    txt1.setText("");
                    txtPrice1.setText("");

                    txt1.setVisibility(View.GONE);
                    txtPrice1.setVisibility(View.GONE);

                    itemName = null;


                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 session")) {
                    select_1[0] = 1;
                    txt1.setVisibility(View.VISIBLE);
                    txtPrice1.setVisibility(View.VISIBLE);

                    txt1.setText("1x Muay Thai Private, sessions are 1 hour and includes stretching, warm-up, hand-wrap, and training plan.");
                    txtPrice1.setText("700 THB");

                    itemName = "Muay Thai Privates:1 session";
                    priceBooking = 700;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("10 session")) {
                    select_1[0] = 1;
                    txt1.setVisibility(View.VISIBLE);
                    txtPrice1.setVisibility(View.VISIBLE);

                    txt1.setText("10x Muay Thai Privates, sessions are 1 hour and includes stretching, warm-up, hand-wrap, and training plan.");
                    txtPrice1.setText("6,500 THB");

                    itemName = "Muay Thai Privates:10 session";
                    priceBooking = 6500;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button booking1 = (android.widget.Button) view.findViewById(R.id.booking1);
        booking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( select_1[0] == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Investigate Booking ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    dialog.show();
                }else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Booking Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int no = 0;
                            try {
                                if (MainActivity.booking.size() == 0) {
                                    MainActivity.booking.add(new DataBooking(itemName, priceBooking, 1));

                                } else {
                                    for (int i = 0; i < MainActivity.booking.size(); i++) {
                                        if (MainActivity.booking.get(i).getBookingName().equals(itemName)) {
                                            MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if (MainActivity.booking.size() == no) {
                                        MainActivity.booking.add(new DataBooking(itemName, priceBooking, 1));
                                    }
                                }
                                ScrollingTigerMuayThaiActivity.tv_cart.setText(MainActivity.booking.size() + "");
                            } catch (Exception x) {

                            }
                        }
                    });

                    dialog.show();
                }

            }
        });



        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        List<String> list2 = new ArrayList<String>();
        list2.add("Choose an option");
        list2.add("1-2 People");
        list2.add("3 People");
        list2.add("4-6 People");
        list2.add("7-10 People");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        final TextView txt2 = (TextView) view.findViewById(R.id.txt2);
        final TextView txtPrice2 = (TextView) view.findViewById(R.id.txtPrice2);
        txt2.setVisibility(View.GONE);
        txtPrice2.setVisibility(View.GONE);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_2[0] = 0;
                    txt2.setText("");
                    txtPrice2.setText("");

                    txt2.setVisibility(View.GONE);
                    txtPrice2.setVisibility(View.GONE);

                    itemName1 = null;


                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1-2 People")) {
                    select_2[0] = 1;
                    txt2.setVisibility(View.VISIBLE);
                    txtPrice2.setVisibility(View.VISIBLE);

                    txt2.setText("Airport pick-up for 1-2 persons, price difference is due to different size vehicles being required.");
                    txtPrice2.setText("850 THB");

                    itemName1 = "Airport Pick-up:1-2 People";
                    priceBooking1 = 850;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 People")) {
                    select_2[0] = 1;
                    txt2.setVisibility(View.VISIBLE);
                    txtPrice2.setVisibility(View.VISIBLE);

                    txt2.setText("Airport pick-up for 3 persons, price difference is due to different size vehicles being required.");
                    txtPrice2.setText("950 THB");

                    itemName1 = "Airport Pick-up:3 People";
                    priceBooking1 = 950;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("4-6 People")) {
                    select_2[0] = 1;
                    txt2.setVisibility(View.VISIBLE);
                    txtPrice2.setVisibility(View.VISIBLE);

                    txt2.setText("Airport pick-up for 4-6 persons, price difference is due to different size vehicles being required.");
                    txtPrice2.setText("1,250 THB");

                    itemName1 = "Airport Pick-up:4-6 People";
                    priceBooking1 = 1250;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("7-10 People")) {
                    select_2[0] = 1;
                    txt2.setVisibility(View.VISIBLE);
                    txtPrice2.setVisibility(View.VISIBLE);

                    txt2.setText("Airport pick-up for 7-10 persons, price difference is due to different size vehicles being required.");
                    txtPrice2.setText("1,400 THB");

                    itemName1 = "Airport Pick-up:7-10 People";
                    priceBooking1 = 1400;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button booking2 = (android.widget.Button) view.findViewById(R.id.booking2);
        booking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( select_2[0] == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Investigate Booking ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    dialog.show();
                }else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Booking Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int no = 0;
                            try {
                                if (MainActivity.booking.size() == 0) {
                                    MainActivity.booking.add(new DataBooking(itemName1, priceBooking1, 1));

                                } else {
                                    for (int i = 0; i < MainActivity.booking.size(); i++) {
                                        if (MainActivity.booking.get(i).getBookingName().equals(itemName1)) {
                                            MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if (MainActivity.booking.size() == no) {
                                        MainActivity.booking.add(new DataBooking(itemName1, priceBooking1, 1));
                                    }
                                }
                                ScrollingTigerMuayThaiActivity.tv_cart.setText(MainActivity.booking.size() + "");
                            } catch (Exception x) {

                            }
                        }
                    });

                    dialog.show();
                }

            }
        });


        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        List<String> list3 = new ArrayList<String>();
        list3.add("Choose an option");
        list3.add("MMA");
        list3.add("Muay Thai");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);

        final TextView txt3 = (TextView) view.findViewById(R.id.txt3);
        final TextView txtPrice3 = (TextView) view.findViewById(R.id.txtPrice3);
        txt3.setVisibility(View.GONE);
        txtPrice3.setVisibility(View.GONE);


        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_3[0] = 0;
                    txt3.setText("");
                    txtPrice3.setText("");

                    txt3.setVisibility(View.GONE);
                    txtPrice3.setVisibility(View.GONE);

                    itemName2 = null;


                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("MMA")) {
                    select_3[0] = 1;
                    txt3.setVisibility(View.VISIBLE);
                    txtPrice3.setVisibility(View.VISIBLE);

                    txt3.setText("Contains: 16 oz Gloves, MMA Gloves, Shin-Pads, Short-sleeved Rashguard, 2x Handwraps, 2x T-Shirts or Tank-Tops, 2x MMA shorts, Mouthguard and a small Sweat Towel.");
                    txtPrice3.setText("10,500 THB");

                    itemName2 = "Gear Package:MMA";
                    priceBooking2 = 10500;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Muay Thai")) {
                    select_3[0] = 1;
                    txt3.setVisibility(View.VISIBLE);
                    txtPrice3.setVisibility(View.VISIBLE);

                    txt3.setText("Contains: 16 oz Gloves, Shin-Pads, 2x Handwraps, 2x T-Shirts or Tank-Tops, 2x Muay Thai shorts, a Mouthguard and a small Sweat Towel..");
                    txtPrice3.setText("8,000 THB");

                    itemName2 = "Gear Package:Muay Thai";
                    priceBooking2 = 8000;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button booking3 = (android.widget.Button) view.findViewById(R.id.booking3);
        booking3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( select_3[0] == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Investigate Booking ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    dialog.show();
                }else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Booking Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int no = 0;
                            try {
                                if (MainActivity.booking.size() == 0) {
                                    MainActivity.booking.add(new DataBooking(itemName2, priceBooking2, 1));

                                } else {
                                    for (int i = 0; i < MainActivity.booking.size(); i++) {
                                        if (MainActivity.booking.get(i).getBookingName().equals(itemName2)) {
                                            MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if (MainActivity.booking.size() == no) {
                                        MainActivity.booking.add(new DataBooking(itemName2, priceBooking2, 1));
                                    }
                                }
                                ScrollingTigerMuayThaiActivity.tv_cart.setText(MainActivity.booking.size() + "");
                            } catch (Exception x) {

                            }
                        }
                    });

                    dialog.show();
                }

            }
        });



        Spinner spinner4 = (Spinner) view.findViewById(R.id.spinner4);
        List<String> list4 = new ArrayList<String>();
        list4.add("Choose an option");
        list4.add("1 Week");
        list4.add("1 Month");
        list4.add("3 Month");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(dataAdapter4);

        final TextView txt4 = (TextView) view.findViewById(R.id.txt4);
        final TextView txtPrice4 = (TextView) view.findViewById(R.id.txtPrice4);
        txt4.setVisibility(View.GONE);
        txtPrice4.setVisibility(View.GONE);


        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_4[0] = 0;
                    txt4.setText("");
                    txtPrice4.setText("");

                    txt4.setVisibility(View.GONE);
                    txtPrice4.setVisibility(View.GONE);

                    itemName3 = null;


                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Week")) {
                    select_4[0] = 1;
                    txt4.setVisibility(View.VISIBLE);
                    txtPrice4.setVisibility(View.VISIBLE);

                    txt4.setText("Convenient prepaid meal cards for 1 week. Be in control of your spending limit during your stay at TMT.");
                    txtPrice4.setText("3,000 THB");

                    itemName3 = "Prepaid Meal Cards:1 Week";
                    priceBooking3 = 3000;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Month")) {
                    select_4[0] = 1;
                    txt4.setVisibility(View.VISIBLE);
                    txtPrice4.setVisibility(View.VISIBLE);

                    txt4.setText("Convenient prepaid meal cards for 1 month. Be in control of your spending limit during your stay at TMT.");
                    txtPrice4.setText("12,000 THB");

                    itemName3 = "Prepaid Meal Cards:1 Month";
                    priceBooking3 = 12000;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 Month")) {
                    select_4[0] = 1;
                    txt4.setVisibility(View.VISIBLE);
                    txtPrice4.setVisibility(View.VISIBLE);

                    txt4.setText("Convenient prepaid meal cards for 3 month. Be in control of your spending limit during your stay at TMT.");
                    txtPrice4.setText("36,000 THB");

                    itemName3 = "Prepaid Meal Cards:3 Month";
                    priceBooking3 = 36000;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button booking4 = (android.widget.Button) view.findViewById(R.id.booking4);
        booking4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( select_4[0] == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Investigate Booking ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    dialog.show();
                }else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Booking Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int no = 0;
                            try {
                                if (MainActivity.booking.size() == 0) {
                                    MainActivity.booking.add(new DataBooking(itemName3, priceBooking3, 1));

                                } else {
                                    for (int i = 0; i < MainActivity.booking.size(); i++) {
                                        if (MainActivity.booking.get(i).getBookingName().equals(itemName3)) {
                                            MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if (MainActivity.booking.size() == no) {
                                        MainActivity.booking.add(new DataBooking(itemName3, priceBooking3, 1));
                                    }
                                }
                                ScrollingTigerMuayThaiActivity.tv_cart.setText(MainActivity.booking.size() + "");
                            } catch (Exception x) {

                            }
                        }
                    });

                    dialog.show();
                }

            }
        });



        return view;
    }
}
