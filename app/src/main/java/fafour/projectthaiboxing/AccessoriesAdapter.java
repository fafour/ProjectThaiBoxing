package fafour.projectthaiboxing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Fafour on 28/1/2560.
 */

public class AccessoriesAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    List<DataAccessories> data= Collections.emptyList();
    DataAccessories current;
    int currentPos=0;

    public AccessoriesAdapter(Context context, List<DataAccessories> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_accessories, parent,false);
        AccessoriesAdapter.MyHolder holder=new AccessoriesAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        AccessoriesAdapter.MyHolder myHolder= (AccessoriesAdapter.MyHolder) holder;
        final DataAccessories current=data.get(position);
        myHolder.nameAccessories.setText(current.accessoriesName);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
        String yourFormattedString = formatter.format(current.accessoriesSale);
        myHolder.priceAccessories.setText(yourFormattedString +" BTH");

        myHolder.txtSale.setPaintFlags(myHolder.txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        myHolder.txtSale.setText(current.accessoriesPrice +" BTH" );
        myHolder.txtSaleData.setText(current.accessoriesSaleData +"%");

//        new DownloadImageFromInternet(myHolder.iconVideo)
//                .execute(current.videoImage);
        Glide.with(context)
                .load(current.accessoriesImg)
                .into(myHolder.iconAccessories);

        myHolder.cvAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewImageActivity.class);
                intent.putExtra("img",current.accessoriesImg);
                intent.putExtra("img1",current.accessoriesImg1);
                intent.putExtra("img2",current.accessoriesImg2);
                intent.putExtra("img3",current.accessoriesImg3);
                intent.putExtra("img4",current.accessoriesImg4);

                intent.putExtra("name",current.accessoriesName);
                intent.putExtra("price",current.accessoriesPrice);

                intent.putExtra("sale",current.accessoriesSale);
                intent.putExtra("saleData",current.accessoriesSaleData);

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
