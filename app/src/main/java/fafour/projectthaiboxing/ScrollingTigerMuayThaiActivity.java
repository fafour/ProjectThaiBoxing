
package fafour.projectthaiboxing;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScrollingTigerMuayThaiActivity extends AppCompatActivity {
    RelativeLayout notificationCount1;
    TextView tv_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_tiger_muay_thai);
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

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("Tiger Muay Thai");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("All-Package"));
        tabLayout.addTab(tabLayout.newTab().setText("Training"));
        tabLayout.addTab(tabLayout.newTab().setText("Accommodation"));
        tabLayout.addTab(tabLayout.newTab().setText("Add-ons"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_booking, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.booking_item);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv_cart  = (TextView)
                notificationCount1.findViewById(R.id.cart_count);


        Button button = (Button) notificationCount1.findViewById(R.id.btn_cart);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}
