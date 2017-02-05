package fafour.projectthaiboxing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fafour on 31/1/2560.
 */

public class TabFragmentChooseAddOns extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_add_ons, container, false);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        List<String> list1 = new ArrayList<String>();
        list1.add("Choose an option");
        list1.add("1 session");
        list1.add("10 session");
        list1.add("Muay Thai Training");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

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

        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        List<String> list3 = new ArrayList<String>();
        list3.add("Choose an option");
        list3.add("MMA");
        list3.add("Muay Thai");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);

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



        return view;
    }
}
