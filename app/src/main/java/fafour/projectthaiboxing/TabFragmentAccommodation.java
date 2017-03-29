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

public class TabFragmentAccommodation extends Fragment {
    final int[] select_1 = {0};
    final int[] select_2 = {0};
    String itemDuration;
    String itemName;
    int priceBooking;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_accommodation, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Choose an option");
        list1.add("Standard");
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


        final TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        txtPrice.setVisibility(View.GONE);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_1[0] = 0;
                    select_2[0] = 0;

                    txtPrice.setText("");
                    itemName = null;
                    itemDuration = null;

                    txtPrice.setVisibility(View.GONE);


                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Standard")) {
                    select_1[0] = 1;
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Accommodation:Standard";

                    if (select_2[0] == 1) {
                        txtPrice.setText("1,000 THB");
                        priceBooking = 1000;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("6,500 THB");
                        priceBooking = 6500;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("16,000 THB");
                        priceBooking = 16000;
                    }
                    if (select_2[0] == 0) {
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
                    select_1[0] = 0;
                    txtPrice.setText("");

                    txtPrice.setVisibility(View.GONE);

                    itemDuration = null;
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Week")) {
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:1 week";

                    select_2[0] = 1;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("1,000 THB");
                        priceBooking = 1000;
                    }
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Month")) {
                    select_2[0] = 2;
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:1 Month";

                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("6,500 THB");
                        priceBooking = 6500;
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 Month")) {
                    select_2[0] = 3;
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:3 Month";

                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("16,000 THB");
                        priceBooking = 16000;
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
                if (select_2[0] == 0 || select_1[0] == 0){
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
                                    MainActivity.booking.add(new DataBooking(itemName, priceBooking, 1, itemDuration));

                                } else {
                                    for (int i = 0; i < MainActivity.booking.size(); i++) {
                                        if (MainActivity.booking.get(i).getBookingName().equals(itemName)
                                                && MainActivity.booking.get(i).getBookingDuration().equals(itemDuration)) {
                                            MainActivity.booking.get(i).setBookingNum(MainActivity.booking.get(i).getBookingNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if (MainActivity.booking.size() == no) {
                                        MainActivity.booking.add(new DataBooking(itemName, priceBooking, 1, itemDuration));
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
