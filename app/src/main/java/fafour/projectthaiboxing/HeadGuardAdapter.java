package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fafour on 4/4/2560.
 */

public class HeadGuardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    List<DataAccessories> data= Collections.emptyList();
    DataAccessories current;
    int currentPos=0;


    public HeadGuardAdapter(Context context, List<DataAccessories> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_accessories, parent,false);
        HeadGuardAdapter.MyHolder holder=new HeadGuardAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        HeadGuardAdapter.MyHolder myHolder= (HeadGuardAdapter.MyHolder) holder;
        final DataAccessories current=data.get(position);
        myHolder.nameAccessories.setText(current.accessoriesName);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
        String yourFormattedString = formatter.format(current.accessoriesSale);
        myHolder.priceAccessories.setText(yourFormattedString +" USD");

        myHolder.txtSale.setPaintFlags(myHolder.txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        myHolder.txtSale.setText(current.accessoriesPrice +" USD" );
        myHolder.txtSaleData.setText(current.accessoriesSaleData +"%");


        Glide.with(context)
                .load(current.accessoriesImg)
                .into(myHolder.iconAccessories);

        myHolder.cvAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewImageItemActivity.class);
                intent.putExtra("img", current.accessoriesImg);
                intent.putExtra("img1", current.accessoriesImg1);
                intent.putExtra("img2", current.accessoriesImg2);
                intent.putExtra("detail", current.accessoriesDetail);
                intent.putExtra("name", current.accessoriesName);
                intent.putExtra("price", current.accessoriesPrice);

                intent.putExtra("stock_1", current.accessoriesstock1);

                intent.putExtra("sale", current.accessoriesSale);
                intent.putExtra("saleData", current.accessoriesSaleData);

                context.startActivity(intent);
            }
        });


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView nameAccessories,priceAccessories;
        ImageView iconAccessories;
        CardView cvAccessories;

        TextView txtSale,txtSaleData;

        public MyHolder(View itemView) {
            super(itemView);
            txtSale = (TextView) itemView.findViewById(R.id.txtSale);
            txtSaleData  = (TextView) itemView.findViewById(R.id.txtSaleData);
            cvAccessories= (CardView) itemView.findViewById(R.id.cvAccessories);
            nameAccessories= (TextView) itemView.findViewById(R.id.nameAccessories);
            priceAccessories= (TextView) itemView.findViewById(R.id.priceAccessories);
            iconAccessories= (ImageView) itemView.findViewById(R.id.iconAccessories);
        }

    }

}
