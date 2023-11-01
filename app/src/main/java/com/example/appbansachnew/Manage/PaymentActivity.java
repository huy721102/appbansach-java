package com.example.appbansachnew.Manage;

import static android.app.PendingIntent.getActivity;

import static java.lang.String.format;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbansachnew.Database.SQLPayment;
import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Model.Bill;
import com.example.appbansachnew.Model.Payment;
import com.example.appbansachnew.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private TextView textPayment,textCancel,textTotalMoney,textTotalBook;
    private EditText edtUserName,edtUserPhone,edtUserAddress;
    private SQLPayment sqlPayment;
    private ArrayList<Payment> paymentArrayList;
    private String listBookPayment = "";
    //private MainActivity mainActivity;
    // private ImageView imageHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        intiView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int totalBook =  bundle.getInt("totalBook");
            String money = bundle.getString("totalMoney");
            listBookPayment = bundle.getString("listBook");
            textTotalBook.setText(String.valueOf(totalBook));
           // float f = Float.valueOf(money);
            textTotalMoney.setText(money);
        }
        textPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(edtUserName.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    if(edtUserPhone.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    if(edtUserAddress.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    Payment payment = new Payment(getId(),MainActivity.userName.toString().trim(),
                            edtUserName.getText().toString(),edtUserPhone.getText().toString(),
                            edtUserAddress.getText().toString(), LocalDate.now().toString(),
                            Integer.valueOf(textTotalBook.getText().toString().trim()),
                            Integer.valueOf(textTotalMoney.getText().toString().trim()),listBookPayment);
                    sqlPayment.setListPaymentSQL(payment);

                    Toast.makeText(PaymentActivity.this, "Thanh toán thành công !!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(PaymentActivity.this,MainActivity.class);
                    startActivity(intent1);
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PaymentActivity.this, "Vui lòng nhập đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "Hủy thanh toán !!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(PaymentActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void intiView() {
        textPayment = findViewById(R.id.textPayment);
        textCancel = findViewById(R.id.textCancelPayment);
        textTotalMoney = findViewById(R.id.textTotalMoney);
        textTotalBook = findViewById(R.id.textTotalBook);
        edtUserName = findViewById(R.id.edtUserNamePayment);
        edtUserPhone = findViewById(R.id.edtUserPhonePayment);
        edtUserAddress = findViewById(R.id.edtUserAddressPayment);
        // imageHome = findViewById(R.id.imageHome);
        sqlPayment = new SQLPayment(PaymentActivity.this);
        paymentArrayList = new ArrayList<>();
        paymentArrayList = sqlPayment.getListPaymentSQL(MainActivity.userName.toString().trim());
        /*for (Payment p: paymentArrayList) {
            Log.d("afgrt",p.getPaymentName());
        }*/
    }

    private int getId() {
        ArrayList<Payment> listFull = new ArrayList<>();
        listFull = sqlPayment.getListFullPaymentSQL();
        int max = 0;
        for (Payment m : listFull) {
            if (m.getPaymentId() > max) {
                max = m.getPaymentId();
            }
        }
        if (listFull.size() == 0) {
            return max;
        }
        return max + 1;
    }
}
