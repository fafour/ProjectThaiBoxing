package fafour.projectthaiboxing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Fafour on 15/1/2560.
 */

public class SkillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataSkill> data= Collections.emptyList();
    DataSkill current;
    int currentPos=0;
    private CallbackInterface mCallback;

    public SkillAdapter(Context context, List<DataSkill> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;

        try{
            mCallback = (CallbackInterface) context;
        }catch(ClassCastException ex){
            //.. should log the error or throw and exception
            Log.e("SkillAdapter","Must implement the CallbackInterface in the Activity", ex);
        }

    }
    public interface CallbackInterface{

        /**
         * Callback invoked when clicked
         * @param position - the position
         * @param textName - the text to pass back
         * @param img - tthe position
         */
        void onHandleSelection(int position,String textName ,int img );
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_skill, parent,false);
        SkillAdapter.MyHolder holder=new SkillAdapter.MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        SkillAdapter.MyHolder myHolder= (SkillAdapter.MyHolder) holder;
        final DataSkill current=data.get(position);
        myHolder.textVieoName.setText(current.skillName);

        Glide.with(context)
                .load(current.skillImg)
                .into(myHolder.iconVideo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    mCallback.onHandleSelection(position,current.skillName,current.skillImg);
                }
            }
        });


        Glide.with(context)
                .load(current.imgLock)
                .into(myHolder.imageView);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView textVieoName;
        CircleImageView iconVideo;
        LinearLayout Tittle;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.lock);
            Tittle= (LinearLayout) itemView.findViewById(R.id.titleId);
            textVieoName= (TextView) itemView.findViewById(R.id.textVieoName);
            iconVideo= (CircleImageView) itemView.findViewById(R.id.iconVideo);
        }

    }



}
