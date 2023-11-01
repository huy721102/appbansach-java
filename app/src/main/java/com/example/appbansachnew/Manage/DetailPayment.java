package com.example.appbansachnew.Manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbansachnew.Adapter.CartAdapter;
import com.example.appbansachnew.Database.SQLCart;
import com.example.appbansachnew.Fragment.CartFragment;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.Model.SelectedBook;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class DetailPayment extends AppCompatActivity {
    private TextView txtIdDetailPayment,
            txtNameDetailPayment,
            txtAddressDetailPayment,txtDateDetailPayment, txtPhoneDetailPayment,
            txtNumberBookDetailPayment, txtMoneyDetailPayment,textListBookDetailPayment;
    private ImageView imageBackDetailPayment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);
        intiView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            txtIdDetailPayment.setText(String.valueOf(bundle.getInt("idPayment")));
            txtNameDetailPayment.setText(bundle.getString("nameUserPayment"));
            txtAddressDetailPayment.setText(bundle.getString("addressPayment"));
            txtPhoneDetailPayment.setText(bundle.getString("phonePayment"));
            txtDateDetailPayment.setText(bundle.getString("datePayment"));
            txtNumberBookDetailPayment.setText(String.valueOf(bundle.getInt("numberBookPayment")));
            txtMoneyDetailPayment.setText(String.valueOf(bundle.getInt("moneyPayment")));
            textListBookDetailPayment.setText(bundle.getString("listBook"));

        }
        imageBackDetailPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailPayment.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void intiView() {
        txtIdDetailPayment = findViewById(R.id.txtIdDetailPayment);
        txtNameDetailPayment = findViewById(R.id.txtNameDetailPayment);
        txtAddressDetailPayment = findViewById(R.id.txtAddressDetailPayment);
        txtPhoneDetailPayment = findViewById(R.id.txtPhoneDetailPayment);
        txtDateDetailPayment = findViewById(R.id.txtDateDetailPayment);
        txtNumberBookDetailPayment = findViewById(R.id.txtNumberBookDetailPayment);
        txtMoneyDetailPayment = findViewById(R.id.txtMoneyDetailPayment);
        imageBackDetailPayment = findViewById(R.id.imageBackDetailPayment);
        textListBookDetailPayment = findViewById(R.id.textListBookDetailPayment);
    }


}
