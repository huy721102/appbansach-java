package com.example.appbansachnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appbansachnew.Adapter.ViewPageAdapter;
import com.example.appbansachnew.Database.SQLBill;
import com.example.appbansachnew.Model.Bill;
import com.example.appbansachnew.Model.Book;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private int key = 0;
    private ArrayList<Bill> billArrayList;
    private SQLBill sqlBill;
    public static int idBill;
    public static String userName;
    public static String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("userName");
             idBill = idBillSQL(name);
            userName = bundle.getString("userName");
            passWord  = bundle.getString("passWord");
        }
        setUpViewPager();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (key == 1) {
                    viewPager.setCurrentItem(1);
                } else {
                    switch (item.getItemId()) {
                        case R.id.menuHome:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.menuCart:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.menuHistoryPayment:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.menuAccount:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (key == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.menuCart).setChecked(true);
                } else {
                    switch (position) {
                        case 0:
                            bottomNavigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
                            break;
                        case 1:
                            bottomNavigationView.getMenu().findItem(R.id.menuCart).setChecked(true);
                            break;
                        case 2:
                            bottomNavigationView.getMenu().findItem(R.id.menuHistoryPayment).setChecked(true);
                            break;
                        case 3:
                            bottomNavigationView.getMenu().findItem(R.id.menuAccount).setChecked(true);
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bnvContainer);
        viewPager = findViewById(R.id.viewPager);
        billArrayList = new ArrayList<>();
        sqlBill = new SQLBill(MainActivity.this);
        billArrayList = sqlBill.getListBillSQL();
    }

    private int idBillSQL(String nameUser) {
        int i = -1;
        for (Bill b : billArrayList) {
            if (b.getCart().getAccount().getUserName().toString().trim().equals(nameUser.trim())) {
                i = b.getBillId();
            }
        }
        return i;
    }

}