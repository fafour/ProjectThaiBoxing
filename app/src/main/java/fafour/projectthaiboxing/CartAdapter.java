package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fafour on 29/1/2560.
 */

public  class CartAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    List<DataBuyItem> data= Collections.emptyList();
    DataBuyItem current;

    int currentPos=0;

    public CartAdapter(Context context, List<DataBuyItem> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.cart_data, parent,false);
        CartAdapter.MyHolder holder=new CartAdapter.MyHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final CartAdapter.MyHolder myHolder= (CartAdapter.MyHolder) holder;
        DataBuyItem current=data.get(position);
        myHolder.nameAccessories.setText(current.accessoriesName);
        myHolder.priceAccessories.setText(current.accessoriesPrice +" Bath");

//        new DownloadImageFromInternet(myHolder.iconVideo)
//                .execute(current.videoImage);
        Glide.with(context)
                .load(current.accessoriesImg)
                .into(myHolder.iconAccessories);

        myHolder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
                if( data.size() == 0){
                   CartActivity.txtStatus.setText("Cart Empty");
                }

            }
        });

    }

    public void removeAt(int position) {
        MainActivity.listBuy.remove(position);
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView nameAccessories,priceAccessories;
        ImageView iconAccessories;
        Button bin;

        public MyHolder(View itemView) {
            super(itemView);
            bin = (Button) itemView.findViewById(R.id.bin);
            nameAccessories= (TextView) itemView.findViewById(R.id.nameAccessories);
            priceAccessories= (TextView) itemView.findViewById(R.id.priceAccessories);
            iconAccessories= (ImageView) itemView.findViewById(R.id.iconAccessories);

        }

    }
}
