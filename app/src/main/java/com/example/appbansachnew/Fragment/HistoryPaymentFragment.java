package com.example.appbansachnew.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbansachnew.Adapter.BookAdapter;
import com.example.appbansachnew.Adapter.HistoryPaymentAdapter;
import com.example.appbansachnew.Controller.SenDataAddBook;
import com.example.appbansachnew.Controller.SenDataHistoryPayment;
import com.example.appbansachnew.Database.SQLPayment;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Manage.AddBookActivity;
import com.example.appbansachnew.Manage.DataBook;
import com.example.appbansachnew.Manage.DetailPayment;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.Model.Payment;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class HistoryPaymentFragment extends Fragment implements SenDataHistoryPayment {
    private ArrayList<Payment> paymentArrayList;
    private RecyclerView recyclerViewHistoryPayment;
    private ViewPager2 viewPager2HistoryPayment;
    private HistoryPaymentAdapter historyPaymentAdapter;
    private View view;
    private MainActivity mainActivity;
    private SQLPayment sqlPayment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history_payment, container, false);

        mainActivity = (MainActivity) getActivity();
        sqlPayment = new SQLPayment(mainActivity);
        paymentArrayList = new ArrayList<>();

        recyclerViewHistoryPayment = view.findViewById(R.id.recyclerViewHistoryPayment);
        recyclerViewHistoryPayment.setHasFixedSize(true);
        viewPager2HistoryPayment = view.findViewById(R.id.viewPager2HistoryPayment);
        viewPager2HistoryPayment.setVisibility(View.GONE);

        loadHistory();
        return view;
    }

    private void loadHistory() {
        paymentArrayList = sqlPayment.getListPaymentSQL(MainActivity.userName);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,
                1, GridLayoutManager.VERTICAL, false);
        recyclerViewHistoryPayment.setLayoutManager(gridLayoutManager);
        historyPaymentAdapter = new HistoryPaymentAdapter(paymentArrayList, HistoryPaymentFragment.this);
        recyclerViewHistoryPayment.setAdapter(historyPaymentAdapter);
    }

    @Override
    public void RemoveHistoryPayment(ArrayList<Payment> list, int position) {
        sqlPayment.deleteListPaymentSQL(list.get(position));
        loadHistory();
    }

    @Override
    public void SenDataToDetailPayment(ArrayList<Payment> list, int position) {
        Intent intent = new Intent(mainActivity, DetailPayment.class);
        intent.putExtra("idPayment",list.get(position).getPaymentId());
        intent.putExtra("nameUserPayment",list.get(position).getPaymentName());
        intent.putExtra("addressPayment",list.get(position).getPaymentAddress());
        intent.putExtra("phonePayment",list.get(position).getPaymentSDT());
        intent.putExtra("datePayment",list.get(position).getDatePayment());
        intent.putExtra("numberBookPayment",list.get(position).getPaymentTotalBook());
        intent.putExtra("moneyPayment",list.get(position).getPaymentTotalMoney());
        intent.putExtra("listBook",list.get(position).getListBook());

        startActivity(intent);

    }
}
