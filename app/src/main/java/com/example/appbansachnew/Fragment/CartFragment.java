package com.example.appbansachnew.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbansachnew.Adapter.BookAdapter;
import com.example.appbansachnew.Adapter.CartAdapter;
import com.example.appbansachnew.Controller.SenDataUpdateCart;
import com.example.appbansachnew.Database.SQLCart;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Manage.DataBook;
import com.example.appbansachnew.Manage.PaymentActivity;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.Model.SelectedBook;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class CartFragment extends Fragment implements SenDataUpdateCart {
    private ArrayList<SelectedBook> selectedBookArrayList;
    private ArrayList<Book> bookArrayList;
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;
    private ViewPager2 viewPager2;
    private View view;
    private MainActivity mainActivity;
    private SQLCart sqlCart;
    private DataBook dataBook;
    private TextView textViewTotalMoney;
    private TextView buttonPayment;
    private int totalBook;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        mainActivity = (MainActivity) getActivity();
        initView();
        setListBookCart();
        buttonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listBookSelect = "";
                for (SelectedBook s:selectedBookArrayList) {
                    sqlCart.deleteListCartSQL(mainActivity.idBill,s);
                    listBookSelect += " + " + s.getNameBook() + "\n";
                }
                Intent intent = new Intent(mainActivity, PaymentActivity.class);
                intent.putExtra("totalBook",totalBook);
                intent.putExtra("totalMoney",textViewTotalMoney.getText().toString());
                intent.putExtra("listBook",listBookSelect);

                startActivity(intent);
            }
        });
        return view;
    }

    private void setListBookCart() {
        bookArrayList = dataBook.getListBook();
        selectedBookArrayList = sqlCart.getListCartSQL(mainActivity.idBill);
        selectedBookArrayList = getSelectList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,
                1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        cartAdapter = new CartAdapter(selectedBookArrayList,CartFragment.this);
        recyclerView.setAdapter(cartAdapter);

        totalBook = getTotalBook();
        textViewTotalMoney.setText(String.valueOf(getTotalMoney()));
    }

    private int getTotalMoney() {
        int total = 0;
        for (SelectedBook selectBook:selectedBookArrayList) {
            total += selectBook.getNumberOfSelectedBook() * selectBook.getPriceBook();
        }
        return total;
    }
    private int getTotalBook() {
        int total = 0;
        for (SelectedBook selectBook:selectedBookArrayList) {
            total += selectBook.getNumberOfSelectedBook();
        }
        return total;
    }

    private void initView() {
        dataBook = new DataBook(bookArrayList,mainActivity);
        sqlCart = new SQLCart(mainActivity);
        textViewTotalMoney = view.findViewById(R.id.textTotalMoney);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        viewPager2 = view.findViewById(R.id.viewPager2Cart);
        viewPager2.setVisibility(View.GONE);
        buttonPayment = view.findViewById(R.id.payment);
    }

    private ArrayList<SelectedBook> getSelectList() {
        ArrayList<SelectedBook> list = new ArrayList<>();
        for (SelectedBook selectBook:selectedBookArrayList) {
            for (Book book:bookArrayList) {
                if (selectBook.getBookId() == book.getBookId()) {
                    SelectedBook s = new SelectedBook(book,selectBook.getNumberOfSelectedBook());
                    list.add(s);
                }
            }
        }

        return  list;
    }

    @Override
    public void RemoveBookCart(ArrayList<SelectedBook> list, int position) {
        SelectedBook selectedBook = list.get(position);
        sqlCart.deleteListCartSQL(mainActivity.idBill,selectedBook);
        selectedBookArrayList = sqlCart.getListCartSQL(mainActivity.idBill);
        selectedBookArrayList = getSelectList();
        Toast.makeText(mainActivity, "Xóa hàng thành công!!", Toast.LENGTH_SHORT).show();
        setListBookCart();
    }

    @Override
    public void UpdateBookCart(ArrayList<SelectedBook> list, int position) {
        SelectedBook selectedBook = list.get(position);
        sqlCart.updateListBillSQL(mainActivity.idBill,selectedBook);
        selectedBookArrayList = sqlCart.getListCartSQL(mainActivity.idBill);
        selectedBookArrayList = getSelectList();
        Toast.makeText(mainActivity, "Update lại giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        setListBookCart();
    }
}
