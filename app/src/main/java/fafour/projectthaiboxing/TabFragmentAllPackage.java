package fafour.projectthaiboxing;

/**
 * Created by Fafour on 30/1/2560.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAllPackage extends Fragment {
    final int[] select_1 = {0};
    final int[] select_2 = {0};
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


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (parent.getItemAtPosition(position).toString()
                        .equals("Choose an option")) {
                    select_1[0] = 0;
                    txt.setText("");
                    txtPrice.setText("");

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Basic")) {
                    select_1[0] = 1;

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

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Delux")) {
                    select_1[0] = 2;
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
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("VIP")) {
                    select_1[0] = 3;
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
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("Weight-Loss")) {
                    select_1[0] = 4;
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
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Week")) {
                    select_2[0] = 1;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("13,400 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txtPrice.setText("17,950 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txtPrice.setText("21,200 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txtPrice.setText("23,100 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("1 Month")) {
                    select_2[0] = 2;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("40,000 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txtPrice.setText("56,250 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txtPrice.setText("72,500 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txtPrice.setText("60,600 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }
                }
                if (parent.getItemAtPosition(position).toString()
                        .equals("3 Month")) {
                    select_2[0] = 3;
                    if (select_1[0] == 0) {
                        txtPrice.setText("");
                    }
                    if (select_1[0] == 1) {
                        txtPrice.setText("118,000 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice.");
                    }
                    if (select_1[0] == 2) {
                        txtPrice.setText("166,750 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 25 Private Muay Thai sessions a month (7 per week).");
                    }
                    if (select_1[0] == 3) {
                        txtPrice.setText("215,500 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice + the addition of 50 Private Muay Thai sessions a month (12 per week).");
                    }
                    if (select_1[0] == 4) {
                        txtPrice.setText("177,400 THB");
                        txt.setText("This package gives you access to all our classes, accommodation in one of our standard accommodations and meal coupons for the duration of your choice. It also includes a supplement pack, a 1-on-1 consultation and 12 Private Fitness sessions a month (3 per week).");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;
    }
}
