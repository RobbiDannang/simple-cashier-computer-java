package com.roaddeum.simplecashiercomputer;

import android.app.FragmentTransaction;

import android.app.FragmentManager;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements Bills.OnViewSelectedListener, CoinRolls.OnViewSelectedListener, LooseCoins.OnViewSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    static float[] sum = {0, 0, 0};
    TextView viewTotal;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewTotal = (TextView) findViewById(R.id.overallTotal);

        android.app.FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        android.app.Fragment bills = new android.app.Fragment();
        android.app.Fragment coinRolls = new android.app.Fragment();
        android.app.Fragment looseCoins = new android.app.Fragment();


        fragmentTransaction.attach(bills);
        fragmentTransaction.attach(coinRolls);
        fragmentTransaction.attach(looseCoins);

        fragmentTransaction.commit();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewSelected(int position, float total) {
        DecimalFormat form = new DecimalFormat("0.00");

        switch(position){
            case 0:
                sum[0] = total;
                viewTotal.setText("$ " + form.format(sum[0]+sum[1]+sum[2]));
                return;
            case 1:
                sum[1] = total;
                viewTotal.setText("$ " + form.format(sum[0]+sum[1]+sum[2]));
                return;
            case 2:
                sum[2] = total;
                viewTotal.setText("$ " + form.format(sum[0]+sum[1]+sum[2]));
                return;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.

            switch(position){
                case 0:
                    return Bills.newInstance();
                case 1:
                    return CoinRolls.newInstance();
                case 2:
                    return LooseCoins.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Bills";
                case 1:
                    return "Coin Rolls";
                case 2:
                    return "Loose Coins";
            }
            return null;
        }
    }
}
