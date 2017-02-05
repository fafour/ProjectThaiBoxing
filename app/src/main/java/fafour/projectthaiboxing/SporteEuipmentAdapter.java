package fafour.projectthaiboxing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Fafour on 31/1/2560.
 */

public class SporteEuipmentAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataSportEquipment> data= Collections.emptyList();
    DataSkill current;
    int currentPos=0;

    public SporteEuipmentAdapter(Context context, List<DataSportEquipment> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_sport, parent,false);
        SporteEuipmentAdapter.MyHolder holder=new SporteEuipmentAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        SporteEuipmentAdapter.MyHolder myHolder= (SporteEuipmentAdapter.MyHolder) holder;
        final DataSportEquipment current=data.get(position);
        myHolder.textVieoName.setText(current.sportEquipmentName);


        Glide.with(context)
                .load(current.sportEquipmentImg)
                .into(myHolder.iconVideo);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView textVieoName;
        CircleImageView iconVideo;

        public MyHolder(View itemView) {
            super(itemView);
            textVieoName= (TextView) itemView.findViewById(R.id.textVieoName);
            iconVideo= (CircleImageView) itemView.findViewById(R.id.iconVideo);
        }

    }


}
