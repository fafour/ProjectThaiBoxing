package fafour.projectthaiboxing;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class TabFragmentTraining extends Fragment {
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



        return view;
    }
}
