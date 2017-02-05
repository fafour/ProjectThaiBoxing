package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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

    public SkillAdapter(Context context, List<DataSkill> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_skill, parent,false);
        SkillAdapter.MyHolder holder=new SkillAdapter.MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        SkillAdapter.MyHolder myHolder= (SkillAdapter.MyHolder) holder;
        final DataSkill current=data.get(position);
        myHolder.textVieoName.setText(current.skillName);

//        new DownloadImageFromInternet(myHolder.iconVideo)
//                .execute(current.videoImage);
        Glide.with(context)
                .load(current.skillImg)
                .into(myHolder.iconVideo);

        myHolder.Tittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScrollingSportEquipmentActivity.class);
                intent.putExtra("img",current.imgAll);
                intent.putExtra("name",current.skillName);
                intent.putExtra("gif",current.skillGif);
                intent.putExtra("raw",current.skilloRaw);
                intent.putStringArrayListExtra("itemName", current.sportequipmentName);
                intent.putIntegerArrayListExtra("itemImg" , current.sportequipmentImg);
                context.startActivity(intent);
            }
        });

        if(position == 3){
            Glide.with(context)
                    .load(R.drawable.icn_pass)
                    .into(myHolder.imageView);
        }

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

//    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
//        ImageView imageView;
//
//        public DownloadImageFromInternet(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String imageURL = urls[0];
//            Bitmap bimage = null;
//            try {
//                InputStream in = new java.net.URL(imageURL).openStream();
//                bimage = BitmapFactory.decodeStream(in);
//
//            } catch (Exception e) {
//                Log.e("Error Message", e.getMessage());
//                e.printStackTrace();
//            }
//            return bimage;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);
//        }
//    }


}
