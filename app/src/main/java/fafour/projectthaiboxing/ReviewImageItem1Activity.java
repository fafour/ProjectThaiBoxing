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

import com.viewpagerindicator.CirclePageIndicator;

import java.text.DecimalFormat;

public class ReviewImageItem1Activity extends AppCompatActivity {
    RelativeLayout notificationCount1;
    TextView tv_cart,cart_count,cart_count1,cart_count2,cart_count3,cart_count4;
    Button button1,button2,button3,button4,button5;
    String size ="";

    private class ImagePagerAdapter extends PagerAdapter {
        int img = getIntent().getIntExtra("img",0);
        int img1 = getIntent().getIntExtra("img1",0);
        private final int[] mImages = new int[] {
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
            final Context context = ReviewImageItem1Activity.this;
            final ImageView imageView = new ImageView(context);
            final int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(this.mImages[position]);
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
        setContentView(R.layout.activity_review_image_item1);
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

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button5.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);


        double sale = getIntent().getDoubleExtra("sale",0.0);
        double price = getIntent().getDoubleExtra("price",0.0);
        String name = getIntent().getStringExtra("name");
        int saleData = getIntent().getIntExtra("saleData",0);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final ReviewImageItem1Activity.ImagePagerAdapter adapter = new ReviewImageItem1Activity.ImagePagerAdapter();
        viewPager.setAdapter(adapter);

        final CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);


        cart_count = (TextView) findViewById(R.id.cart_count);
        cart_count1 = (TextView) findViewById(R.id.cart_count1);
        cart_count2 = (TextView) findViewById(R.id.cart_count2);
        cart_count3 = (TextView) findViewById(R.id.cart_count3);
        cart_count4 = (TextView) findViewById(R.id.cart_count4);

        int stock = getIntent().getIntExtra("stock1",0);
        int stock1 = getIntent().getIntExtra("stock2",0);
        int stock2 = getIntent().getIntExtra("stock3",0);
        int stock3 = getIntent().getIntExtra("stock4",0);
        int stock4 = getIntent().getIntExtra("stock5",0);

        cart_count.setText(stock+"");
        cart_count1.setText(stock1+"");
        cart_count2.setText(stock2+"");
        cart_count3.setText(stock3+"");
        cart_count4.setText(stock4+"");


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
                int stock4 = getIntent().getIntExtra("stock5",0);
                if (size == ""){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Please select size..");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else if (size == "8 oz" && stock == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Out Of Stock");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else if (size == "10 oz" && stock1 == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Out Of Stock");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else if (size == "12 oz" && stock2 == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Out Of Stock");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else if (size == "14 oz" && stock3 == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Out Of Stock");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else if (size == "16 oz" && stock4 == 0){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Out Of Stock");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }
                else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageItem1Activity.this);
                    dialog.setTitle("Add Cart Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int img = getIntent().getIntExtra("img",0);
                            String name = getIntent().getStringExtra("name")+" Size :"+size;
                            double sale = getIntent().getDoubleExtra("sale",0.0);
                            double price = getIntent().getDoubleExtra("price",0.0);
                            int saleData = getIntent().getIntExtra("saleData",0);

                            int no = 0;
                            try{
                                if( MainActivity.listBuy.size() == 0){
                                    MainActivity.listBuy.add(new DataBuyItem(name, price, img, 1,sale,saleData));

                                }else {
                                    for (int i = 0; i < MainActivity.listBuy.size(); i++) {
                                        if (MainActivity.listBuy.get(i).getAccessoriesName().equals(name)) {
                                            MainActivity.listBuy.get(i).setAccessoriesNum(MainActivity.listBuy.get(i).getAccessoriesNum() + 1);
                                            continue;
                                        } else {
                                            no++;
                                        }
                                    }
                                    if ( MainActivity.listBuy.size() == no){
                                        MainActivity.listBuy.add(new DataBuyItem(name, price, img, 1,sale,saleData));
                                    }
                                }
                                tv_cart.setText(MainActivity.listBuy.size() +"");
                            }catch (Exception x){

                            }
                        }
                    });

                    dialog.show();

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
    public void oz_one(View view){
        size = "8 oz";
        button1.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);

    }
    public void oz_two(View view){
        size = "10 oz";
        button2.setBackgroundResource(R.color.colorText);
        button1.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);

    }
    public void oz_three(View view){
        size = "12 oz";
        button3.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);

    }
    public void oz_four(View view){
        size = "14 oz";
        button4.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);

    }
    public void oz_five(View view){
        size = "16 oz";
        button5.setBackgroundResource(R.color.colorText);
        button4.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);

    }



}
