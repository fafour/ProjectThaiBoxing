package fafour.projectthaiboxing;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
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
    int txtincrement = 1;
    int txtdecrement = -1;
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

;
        Glide.with(context)
                .load(current.accessoriesImg)
                .into(myHolder.iconAccessories);

        myHolder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
                if( data.size() == 0){
                    CartActivity.txtStatus.setText("Cart Empty");
                    CartActivity.txtStatus.setVisibility(View.VISIBLE);
                    CartActivity.dataBuy.setVisibility(View.GONE);
                }
                DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
                String yourFormattedString = formatter.format(Total.totalBuyItem());
                CartActivity.txtTotal.setText(yourFormattedString+"");

            }
        });

        myHolder.display.setText(current.accessoriesNum+"");


        myHolder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -
                if(current.accessoriesNum == 1){

                }else {
                    int a = current.accessoriesNum +txtdecrement;
                    current.accessoriesNum =a;

                    MainActivity.listBuy.get(position).setAccessoriesNum(MainActivity.listBuy.get(position).getAccessoriesNum() -1);

                    myHolder.display.setText(current.accessoriesNum+"");
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
                    String yourFormattedString = formatter.format(Total.totalBuyItem());
                    CartActivity.txtTotal.setText(yourFormattedString+"");
                }

            }
        });


        myHolder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // +
                if(current.accessoriesNum < current.accessoriesStock) {
                    int a = current.accessoriesNum + txtincrement;
                    current.accessoriesNum = a;

                    MainActivity.listBuy.get(position).setAccessoriesNum(MainActivity.listBuy.get(position).getAccessoriesNum() +1);

                    myHolder.display.setText(current.accessoriesNum + "");
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###.##");
                    String yourFormattedString = formatter.format(Total.totalBuyItem());
                    CartActivity.txtTotal.setText(yourFormattedString + "");
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
        TextView nameAccessories,priceAccessories,display;
        ImageView iconAccessories;
        Button bin,increment,decrement;
        TextView txtSale,txtSaleData;
        public MyHolder(View itemView) {
            super(itemView);
            txtSale = (TextView) itemView.findViewById(R.id.txtSale);
            txtSaleData  = (TextView) itemView.findViewById(R.id.txtSaleData);
            increment = (Button) itemView.findViewById(R.id.increment);
            decrement = (Button) itemView.findViewById(R.id.decrement);
            bin = (Button) itemView.findViewById(R.id.bin);
            display= (TextView) itemView.findViewById(R.id.display);
            nameAccessories= (TextView) itemView.findViewById(R.id.nameAccessories);
            priceAccessories= (TextView) itemView.findViewById(R.id.priceAccessories);
            iconAccessories= (ImageView) itemView.findViewById(R.id.iconAccessories);
        }

    }
}
