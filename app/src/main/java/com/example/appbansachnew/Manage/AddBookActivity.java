package com.example.appbansachnew.Manage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbansachnew.Database.SQLCart;
import com.example.appbansachnew.Fragment.CartFragment;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.Model.SelectedBook;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {
    private DataBook dataBook;
    private ArrayList<Book> bookArrayList;
    private int idBook,idBill;
    private Book book;
    private TextView textViewName, textViewPrice, textViewNumber;
    private ImageView imageBook, imageViewSubtraction, imageViewSummation,backBookCart;
    private TextView btnAddCart;
    private SQLCart sqlCart;
    private SelectedBook selectedBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        intiView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            idBook = bundle.getInt("id");
            idBill = bundle.getInt("idBill");
        }
        for (Book b : bookArrayList) {
            if (b.getBookId() == idBook) {
                book = b;
                imageBook.setImageBitmap(book.getPathImage());
                textViewName.setText(book.getNameBook());
                textViewPrice.setText(String.valueOf(book.getPriceBook()));
                break;
            }
        }

        imageViewSummation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(textViewNumber.getText().toString().trim());
                textViewNumber.setText(String.valueOf(number + 1));

            }
        });

        imageViewSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(textViewNumber.getText().toString());
                if (number > 1) {
                    textViewNumber.setText(String.valueOf(number - 1));
                } else {
                    Toast.makeText(AddBookActivity.this, "Số lượng sach phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedBook = new SelectedBook(book, Integer.valueOf(textViewNumber.getText().toString().trim()));
                sqlCart.setListCartSQL(idBill, selectedBook);
               /* ArrayList<SelectedBook> select = sqlCart.getListCartSQL(idBill);
                for (SelectedBook s:select) {
                    Log.d("a",s.getBookId() + s.getNameBook() + s.getNumberOfSelectedBook());
                }*/
                Toast.makeText(AddBookActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AddBookActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        backBookCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddBookActivity.this, "Bỏ chọn thành công", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AddBookActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void intiView() {
        dataBook = new DataBook(bookArrayList, AddBookActivity.this);
        bookArrayList = dataBook.getListBook();
        textViewName = findViewById(R.id.textName);
        textViewPrice = findViewById(R.id.textPrice);
        imageBook = findViewById(R.id.imageBook);
        textViewNumber = findViewById(R.id.numberBook);
        imageViewSubtraction = findViewById(R.id.subtraction);
        imageViewSummation = findViewById(R.id.summation);
        btnAddCart = findViewById(R.id.addCart);
        backBookCart= findViewById(R.id.backBookCart);
        sqlCart = new SQLCart(AddBookActivity.this);
    }
}
