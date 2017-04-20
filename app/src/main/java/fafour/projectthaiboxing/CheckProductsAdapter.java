package fafour.projectthaiboxing;

import android.content.Context;
import android.graphics.Paint;
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
 * Created by Fafour on 13/4/2560.
 */

public class CheckProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    List<DataBuyItem> data= Collections.emptyList();
    DataBuyItem current;

    public CheckProductsAdapter(Context context, List<DataBuyItem> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.check_products_data, parent,false);
        CheckProductsAdapter.MyHolder holder=new CheckProductsAdapter.MyHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final CheckProductsAdapter.MyHolder myHolder= (CheckProductsAdapter.MyHolder) holder;
        final DataBuyItem current=data.get(position);
        if(!current.accessoriesSize.equals("")) {
            myHolder.nameAccessories.setText(current.accessoriesName + "   Size:" + current.accessoriesSize);
        }else {
            myHolder.nameAccessories.setText(current.accessoriesName +  current.accessoriesSize);
        }
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###.##");
        String yourFormattedString = formatter.format(current.accessoriesSale);
        myHolder.priceAccessories.setText(yourFormattedString +" USD");

        myHolder.txtSale.setPaintFlags(myHolder.txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        String yourFormattedString1 = formatter.format(current.accessoriesPrice);
        myHolder.txtSale.setText(yourFormattedString1 +" USD");

        myHolder.txtSaleData.setText(current.accessoriesSaleData+"%");


        Glide.with(context)
                .load(current.accessoriesImg)
                .into(myHolder.iconAccessories);


        myHolder.display.setText(current.accessoriesNum+"");



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
        TextView nameAccessories,priceAccessories,display;
        ImageView iconAccessories;
        TextView txtSale,txtSaleData;
        public MyHolder(View itemView) {
            super(itemView);
            txtSale = (TextView) itemView.findViewById(R.id.txtSale);
            txtSaleData  = (TextView) itemView.findViewById(R.id.txtSaleData);
            display= (TextView) itemView.findViewById(R.id.display);
            nameAccessories= (TextView) itemView.findViewById(R.id.nameAccessories);
            priceAccessories= (TextView) itemView.findViewById(R.id.priceAccessories);
            iconAccessories= (ImageView) itemView.findViewById(R.id.iconAccessories);
        }

    }
}
