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

public class ReviewImageActivity extends AppCompatActivity {
    RelativeLayout notificationCount1;
    TextView tv_cart;
    Button button1,button2,button3,button4,button5,button6;
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
            final Context context = ReviewImageActivity.this;
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
        setContentView(R.layout.activity_review_image);
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

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        button6.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        int price = getIntent().getIntExtra("price",0);
        String name = getIntent().getStringExtra("name");
        int sale = getIntent().getIntExtra("sale",0);
        int saleData = getIntent().getIntExtra("saleData",0);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);

        final CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        final TextView tvTextName = (TextView) findViewById(R.id.textName);
        final TextView tvTextPrice = (TextView) findViewById(R.id.textPrice);
        final TextView txtSale = (TextView) findViewById(R.id.txtSale);
        final TextView txtSaleData = (TextView) findViewById(R.id.txtSaleData);

        tvTextName.setText(name);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");
        String yourFormattedString = formatter.format(sale);
        tvTextPrice.setText(yourFormattedString +"  BTH");
        String yourFormattedString1 = formatter.format(price);
        txtSale.setText(yourFormattedString1 +"  BTH");
        txtSaleData.setText(saleData +"%");
        txtSale.setPaintFlags(txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

        final Button addCart = (Button) findViewById(R.id.addCart);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size == ""){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageActivity.this);
                    dialog.setTitle("Please select size..");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                }else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewImageActivity.this);
                    dialog.setTitle("Add Cart Success ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int img = getIntent().getIntExtra("img",0);
                            String name = getIntent().getStringExtra("name")+" Size :"+size;
                            int price = getIntent().getIntExtra("price",0);
                            int sale = getIntent().getIntExtra("sale",0);
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
    public void xs(View view){
        size = "xs";
        button1.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.colorText);
        button6.setBackgroundResource(R.color.buttonBg);

    }
    public void xxl(View view){
        size = "XXL";
        button1.setBackgroundResource(R.color.buttonBg);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button6.setBackgroundResource(R.color.colorText);

    }
    public void s(View view){
        size = "S";
        button1.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button6.setBackgroundResource(R.color.buttonBg);

    }
    public void m(View view){
        size = "M";
        button2.setBackgroundResource(R.color.colorText);
        button1.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button6.setBackgroundResource(R.color.buttonBg);

    }
    public void l(View view){
        size = "L";
        button3.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button4.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button6.setBackgroundResource(R.color.buttonBg);

    }
    public void xl(View view){
        size = "XL";
        button4.setBackgroundResource(R.color.colorText);
        button2.setBackgroundResource(R.color.buttonBg);
        button3.setBackgroundResource(R.color.buttonBg);
        button1.setBackgroundResource(R.color.buttonBg);
        button5.setBackgroundResource(R.color.buttonBg);
        button6.setBackgroundResource(R.color.buttonBg);

    }


}
