package com.example.appbansachnew.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appbansachnew.Fragment.AccountFragment;
import com.example.appbansachnew.Fragment.CartFragment;
import com.example.appbansachnew.Fragment.HistoryPaymentFragment;
import com.example.appbansachnew.Fragment.HomeFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new HomeFragment();
            case 1 :
                return new CartFragment();
            case 2:
                return new HistoryPaymentFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
