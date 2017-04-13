package fafour.projectthaiboxing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.DecimalFormat;

public class ReviewImage1Activity extends AppCompatActivity {
    RelativeLayout notificationCount1;
    TextView tv_cart,cart_count,cart_count1,cart_count2,cart_count3;
    Button button2,button3,button1,button4;
    String size ="";

    private class ImagePagerAdapter extends PagerAdapter {
        String img = getIntent().getStringExtra("img");
        String img1 = getIntent().getStringExtra("img1");
        private final String[] mImages = new String[] {
                img,
                img1
        };

        @Override
        public void destroyItem(final ViewGroup container, final int position, final Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

        @Override
        public int getCount() {
            return this.mImages.length;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            final Context context = ReviewImage1Activity.this;
            final ImageView imageView = new ImageView(context);
            final int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Glide.with(context)
                    .load(this.mImages[position])
                    .into(imageView);

            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(final View view, final Object object) {
            return view == ((ImageView) object);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_image1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1 = (Button) findViewById(R.id.button1);
        button4 = (Button) findViewById(R.id.button4);

        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        double sale = getIntent().getDoubleExtra("sale",0.0);
        double price = getIntent().getDoubleExtra("price",0.0);
        String name = getIntent().getStringExtra("name");
        int saleData = getIntent().getIntExtra("saleData",0);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);

        final CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        cart_count = (TextView) findViewById(R.id.cart_count);
        cart_count1 = (TextView) findViewById(R.id.cart_count1);
        cart_count2 = (TextView) findViewById(R.id.cart_count2);
        cart_count3 = (TextView) findViewById(R.id.cart_count3);

        int stock = getIntent().getIntExtra("stock1",0);
        int stock1 = getIntent().getIntExtra("stock2",0);
        int stock2 = getIntent().getIntExtra("stock3",0);
        int stock3 = getIntent().getIntExtra("stock4",0);

        cart_count.setText(stock+"");
        cart_count1.setText(stock1+"");
        cart_count2.setText(stock2+"");
        cart_count3.setText(stock3+"");

        final TextView tvTextName = (TextView) findViewById(R.id.textName);
        final TextView tvTextPrice = (TextView) findViewById(R.id.textPrice);
        final TextView txtSale = (TextView) findViewById(R.id.txtSale);
        final TextView txtSaleData = (TextView) findViewById(R.id.txtSaleData);
        final TextView textTittle = (TextView) findViewById(R.id.textTittle);

        String detail = getIntent().getStringExtra("detail");
        textTittle.setText(detail);

        tvTextName.setText(name);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###.##");
        String yourFormattedString = formatter.format(sale);
        tvTextPrice.setText(yourFormattedString +"  USD");
        String yourFormattedString1 = formatter.format(price);
        txtSale.setText(yourFormattedString1 +"  USD");
        txtSaleData.setText(saleData +"%");
        txtSale.setPaintFlags(txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

        final Button addCart = (Button) findViewById(R.id.addCart);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stock = getIntent().getIntExtra("stock1",0);
                int stock1 = getIntent().getIntExtra("stock2",0);
                int stock2 = getIntent().getIntExtra("stock3",0);
                int stock3 = getIntent().getIntExtra("stock4",0);
                String img = getIntent().getStringExtra("img");
                String name = getIntent().getStringExtra("name");
                double sale = getIntent().getDoubleExtra("sale",0.0);
                double price = getIntent().getDoubleExtra("price",0.0);
                int saleData = getIntent().getIntExtra("saleData",0);
                if (size == ""){
                    show_ms("Please select size..");
                }else if (size == "S" && stock == 0){
                    show_ms("Out Of Stock..");
                }else if (size == "M" && stock1 == 0){
                    show_ms("Out Of Stock..");
                }else if (size == "L" && stock2 == 0){
                    show_ms("Out Of Stock..");
                } else if (size == "XL" && stock3 == 0){
                    show_ms("Out Of Stock..");
                }else if(size == "S" && stock != 0){
                    add_item(name,size,price ,img ,sale , saleData , stock );
                }else if(size == "M" && stock1 != 0){
                    add_item(name,size,price ,img ,sale , saleData , stock1 );
                }else if(size == "L" && stock2 != 0){
                    add_item(name,size,price ,img ,sale , saleData , stock2 );
                }else if(size == "XL" && stock3 != 0){
                    add_item(name,size,price ,img ,sale , saleData , stock3 );
                }

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.cart_item);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv_cart  = (TextView)
                notificationCount1.findViewById(R.id.cart_count);

        try{
            tv_cart.setText(MainActivity.listBuy.size() +"");
        }catch (Exception x){

        }


        Button button = (Button) notificationCount1.findViewById(R.id.btn_cart);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            tv_cart.setText(MainActivity.listBuy.size());
        }catch (Exception x){

        }

    }
    public void m(View view){
        size = "M";
        button2.setBackgroundResource(R.color.colorText);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);

    }
    public void l(View view){
        size = "L";
        button3.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);

    }
    public void s(View view){
        size = "S";
        button3.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.colorText);
        button4.setBackgroundResource(R.color.buttonBg);

    }

    public void xl(View view){
        size = "XL";
        button3.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.colorText);
        button1.setBackgroundResource(R.color.buttonBg);


    }
    public void show_ms(String mg){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImage1Activity.this);
        dialog.setTitle(mg);
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }

    public void add_item(String name, String size,double price ,String img ,double sale , int saleData ,int stock ){
        int no = 0;
        try {
            if (MainActivity.listBuy.size() == 0) {
                show_ms("Add Cart Success...");
                MainActivity.listBuy.add(new DataBuyItem(4,name,size,price, img, 1, sale, saleData,stock));

            } else {
                for (int i = 0; i < MainActivity.listBuy.size(); i++) {
                    if (MainActivity.listBuy.get(i).getAccessoriesName().equals(name) && MainActivity.listBuy.get(i).getAccessoriesSize().equals(size) ) {
                        if(MainActivity.listBuy.get(i).getAccessoriesNum()< stock){
                            MainActivity.listBuy.get(i).setAccessoriesNum(MainActivity.listBuy.get(i).getAccessoriesNum() + 1);
                            show_ms("Add Cart Success...");
                            continue;
                        }else {
                            show_ms("Its Not Enough...");
                            continue;

                        }

                    } else {
                        no++;
                    }
                }
                if (MainActivity.listBuy.size() == no) {
                    MainActivity.listBuy.add(new DataBuyItem(4,name,size, price, img, 1, sale, saleData,stock));
                    show_ms("Add Cart Success...");
                }
            }
            tv_cart.setText(MainActivity.listBuy.size() + "");
        } catch (Exception x) {

        }
    }



}
