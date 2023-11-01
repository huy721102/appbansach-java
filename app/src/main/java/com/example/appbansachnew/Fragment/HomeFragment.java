package com.example.appbansachnew.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbansachnew.Adapter.BookAdapter;
import com.example.appbansachnew.Controller.SenDataAddBook;
import com.example.appbansachnew.Login.LoginActivity;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Manage.AddBookActivity;
import com.example.appbansachnew.Manage.DataBook;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements SenDataAddBook {
    private ArrayList<Book> bookArrayList;
    private BookAdapter bookAdapter;
    private RecyclerView recyclerView;
    private ViewPager2 viewPager2;
    private View view;
    private MainActivity mainActivity;
    private DataBook dataBook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        dataBook = new DataBook(bookArrayList,(MainActivity)mainActivity);
        recyclerView = view.findViewById(R.id.recyclerView_home);
        recyclerView.setHasFixedSize(true);
        viewPager2 = view.findViewById(R.id.viewPager2_home);
        viewPager2.setVisibility(View.GONE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,
                2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        bookAdapter = new BookAdapter(dataBook.getListBook(),HomeFragment.this);
        recyclerView.setAdapter(bookAdapter);
        return view;
    }

    @Override
    public void addBook(ArrayList<Book> list, int poition) {
        Intent intent = new Intent(getActivity(), AddBookActivity.class);
        intent.putExtra("id",list.get(poition).getBookId());
        intent.putExtra("idBill",mainActivity.idBill);
        startActivity(intent);
    }
}
