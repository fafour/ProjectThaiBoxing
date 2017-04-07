package fafour.projectthaiboxing;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class TabFragmentTraining extends Fragment {
    final int[] select_1 = {0};
    final int[] select_2 = {0};
    String itemDuration;
    String itemName;
    int priceBooking;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_training, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Choose an option");
        list1.add("Grappling and BJJ");
        list1.add("Muay Thai Training");
        list1.add("Muay Thai and MMA Trainng");
        list1.add("Finess Training");
        list1.add("Krabi Krabong and Muay Boran");
        list1.add("All-Inclusive Training");
        list1.add("DELUX Traning");
        list1.add("VIP Traning");
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

                    itemName = null;
                    itemDuration = null;

                    itemName = null;

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Grappling and BJJ")) {
                    select_1[0] = 1;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:Grappling & BJJ";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to all our BJJ classes and the Tuesday and Thursdays MMA Wrestling only classes + weight room access.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("3,500 THB");
                        priceBooking = 3500;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("11,000 THB");
                        priceBooking = 11000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("30,000 THB");
                        priceBooking = 30000;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("Muay Thai Training")) {
                    select_1[0] = 2;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:Muay Thai Training";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to all our Muay Thai, Western Boxing, Krabi Krabong and Muay Boran classes + weight room access.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("3,300 THB");
                        priceBooking = 3300;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("11,000 THB");
                        priceBooking = 11000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("29,500 THB");
                        priceBooking = 29500;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("Muay Thai and MMA Trainng")) {
                    select_1[0] = 3;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:Muay Thai & MMA Training";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to all Muay Thai, MMA, BJJ Kickboxing, Western Boxing, Muay Boran, Krabi Krabong and Yoga classes + weight room access.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("3,900 THB");
                        priceBooking = 3900;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("13,000 THB");
                        priceBooking = 13000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("33,500 THB");
                        priceBooking = 33500;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("Finess Training")) {
                    select_1[0] = 4;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:Fitness Training";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to all our Fitness classes, Off-Site Boot-camps training sessions and Yoga classes + weight room access.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("3,900 THB");
                        priceBooking = 3900;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("13,000 THB");
                        priceBooking = 13000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("33,500 THB");
                        priceBooking = 33500;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("Krabi Krabong and Muay Boran")) {
                    select_1[0] = 5;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:Krabi Krabong & Muay Boran";

                    if (select_2[0] != 0) {
                        txt.setText("This package includes access to all our Krabi Krabong & Muay Boran classes + weight room access.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("3,000 THB");
                        priceBooking = 3000;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("10,000 THB");
                        priceBooking = 10000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("26,500 THB");
                        priceBooking = 26500;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("All-Inclusive Training")) {
                    select_1[0] = 6;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:All-Inclusive Training";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to ALL our classes and the weight-room.");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("4,400 THB");
                        priceBooking = 4400;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("14,000 THB");
                        priceBooking = 14000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("38,000 THB");
                        priceBooking = 38000;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("DELUX Traning")) {
                    select_1[0] = 7;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:DELUX Traning";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("8,550 THB");
                        priceBooking = 8550;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("28,250 THB");
                        priceBooking = 28250;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("82,750 THB");
                        priceBooking = 82750;
                    }
                    if (select_2[0] == 0) {
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }

                }

                if (parent.getItemAtPosition(position).toString()
                        .equals("VIP Traning")) {
                    select_1[0] = 8;
                    txt.setVisibility(View.VISIBLE);
                    txtPrice.setVisibility(View.VISIBLE);

                    itemName = "Training:VIP Traning";

                    if (select_2[0] != 0) {
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_2[0] == 1) {
                        txtPrice.setText("11,700 THB");
                        priceBooking = 11700;
                    }
                    if (select_2[0] == 2) {
                        txtPrice.setText("44,000 THB");
                        priceBooking = 44000;
                    }
                    if (select_2[0] == 3) {
                        txtPrice.setText("128,000 THB");
                        priceBooking = 128000;
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

                    itemName = null;
                    itemDuration = null;

                    itemDuration = null;
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Week")) {
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:1 week";

                    select_2[0] = 1;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("3,500 THB");
                        priceBooking = 3500;
                        txt.setText("This option gives you access to all our BJJ classes and the Tuesday and Thursdays MMA Wrestling only classes + weight room access.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("3,300 THB");
                        priceBooking = 3300;
                        txt.setText("This option gives you access to all our Muay Thai, Western Boxing, Krabi Krabong and Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("3,900 THB");
                        priceBooking = 3900;
                        txt.setText("This option gives you access to all Muay Thai, MMA, BJJ Kickboxing, Western Boxing, Muay Boran, Krabi Krabong and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("3,900 THB");
                        priceBooking = 3900;
                        txt.setText("This option gives you access to all our Fitness classes, Off-Site Boot-camps training sessions and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 5) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("3,000 THB");
                        priceBooking = 3000;
                        txt.setText("This package includes access to all our Krabi Krabong & Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 6) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("4,400 THB");;
                        priceBooking = 4400;
                        txt.setText("This option gives you access to ALL our classes and the weight-room.");
                    }
                    if (select_1[0] == 7) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("8,550 THB");
                        priceBooking = 8550;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 8) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("11,700 THB");
                        priceBooking = 11700;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Month")) {
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:1 month";

                    select_2[0] = 2;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("11,000 THB");
                        priceBooking = 11000;
                        txt.setText("This option gives you access to all our BJJ classes and the Tuesday and Thursdays MMA Wrestling only classes + weight room access.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("11,000 THB");
                        priceBooking = 11000;
                        txt.setText("This option gives you access to all our Muay Thai, Western Boxing, Krabi Krabong and Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("13,000 THB");;
                        priceBooking = 13000;
                        txt.setText("This option gives you access to all Muay Thai, MMA, BJJ Kickboxing, Western Boxing, Muay Boran, Krabi Krabong and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("13,000 THB");
                        priceBooking = 13000;
                        txt.setText("This option gives you access to all our Fitness classes, Off-Site Boot-camps training sessions and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 5) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("10,000 THB");
                        priceBooking = 10000;
                        txt.setText("This package includes access to all our Krabi Krabong & Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 6) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("14,000 THB");
                        priceBooking = 14000;
                        txt.setText("This option gives you access to ALL our classes and the weight-room.");
                    }
                    if (select_1[0] == 7) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("28,250 THB");
                        priceBooking = 28250;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 8) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("44,000 THB");
                        priceBooking = 44000;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 Month")) {
                    txtPrice.setVisibility(View.VISIBLE);

                    itemDuration = "Duration:3 month";

                    select_2[0] = 3;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                        txt.setVisibility(View.GONE);
                        txtPrice.setVisibility(View.GONE);
                    }
                    if (select_1[0] == 1) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("30,000 THB");
                        priceBooking = 30000;
                        txt.setText("This option gives you access to all our BJJ classes and the Tuesday and Thursdays MMA Wrestling only classes + weight room access.");
                    }
                    if (select_1[0] == 2) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("29,500 THB");
                        priceBooking = 29500;
                        txt.setText("This option gives you access to all our Muay Thai, Western Boxing, Krabi Krabong and Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 3) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("33,500 THB");;
                        priceBooking = 33500;
                        txt.setText("This option gives you access to all Muay Thai, MMA, BJJ Kickboxing, Western Boxing, Muay Boran, Krabi Krabong and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 4) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("33,500 THB");
                        priceBooking = 33500;
                        txt.setText("This option gives you access to all our Fitness classes, Off-Site Boot-camps training sessions and Yoga classes + weight room access.");
                    }
                    if (select_1[0] == 5) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("26,500 THB");
                        priceBooking = 26500;
                        txt.setText("This package includes access to all our Krabi Krabong & Muay Boran classes + weight room access.");
                    }
                    if (select_1[0] == 6) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("38,000 THB");
                        priceBooking = 38000;
                        txt.setText("This option gives you access to ALL our classes and the weight-room.");
                    }
                    if (select_1[0] == 7) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("82,750 THB");
                        priceBooking = 82750;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 8) {
                        txt.setVisibility(View.VISIBLE);
                        txtPrice.setText("128,000 THB");
                        priceBooking = 128000;
                        txt.setText("This option gives you access to ALL our classes and the weight-room + the addition of 50 Private Muay Thai sessions a month (12 per week).");
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
