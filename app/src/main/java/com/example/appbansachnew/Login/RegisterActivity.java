package com.example.appbansachnew.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbansachnew.Database.SQLAccount;
import com.example.appbansachnew.Database.SQLBill;
import com.example.appbansachnew.Model.Account;
import com.example.appbansachnew.Model.Bill;
import com.example.appbansachnew.Model.Cart;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private TextView  btnRegister, btnRetype;
    private EditText edtUser, edtPassWord, edtRetypePassWord,edtEmail,edtPhone;
    private RadioButton radioNam,radioNu;
    private ArrayList<Account> accountUserList;
    private SQLAccount sqlAccount;
    private SQLBill  sqlBill;
    private ArrayList<Bill> billArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initVew();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlAccount = new SQLAccount(RegisterActivity.this);
                sqlBill = new SQLBill(RegisterActivity.this);
                String nameAc = edtUser.getText().toString();
                String passAc = edtPassWord.getText().toString();
                String retypePassAc = edtRetypePassWord.getText().toString();
                boolean CheckAc = true;
                Account account = new Account(nameAc, passAc,"","","");

                accountUserList = sqlAccount.getListAccountSQL();
                billArrayList = sqlBill.getListBillSQL();
                String name;
                for (Account ac : accountUserList) {
                    name = ac.getUserName();
                    if (nameAc.equals(name.trim())) {
                        CheckAc = false;
                        break;
                    }
                }
                if (nameAc.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Tên tài khoản không được để trống !!", Toast.LENGTH_SHORT).show();
                } else {
                    if (passAc.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu  không đươc để trống !!", Toast.LENGTH_SHORT).show();
                    } else  {
                        if(passAc.toString().trim().length() < 6) {
                            Toast.makeText(RegisterActivity.this, "Mật khẩu phải từ 6 chữ số trở nên !!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (passAc.equals(retypePassAc) == false) {
                                Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu không khớp !!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (CheckAc == false) {
                                    Toast.makeText(RegisterActivity.this, "Tài khoản này đã tồn tại !!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Account newAccount = new Account(nameAc, passAc,"1","1","1");
                                    sqlAccount.setListAccountSQL(newAccount);
                                    sqlBill.setListBillSQL(new Bill(getId(),new Cart(newAccount),0,0));
                                    Toast.makeText(RegisterActivity.this, "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.putExtra("nameUser", newAccount.getUserName().trim());
                                    intent.putExtra("passWordUser", newAccount.getPassWord().trim());
                                    setResult(RESULT_OK,intent);
                                    finish();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void initVew() {
        btnRegister = findViewById(R.id.btnRegister);
        btnRetype = findViewById(R.id.btnRegister);
        accountUserList = new ArrayList<>();
        edtUser = findViewById(R.id.edtUserNamePayment);
        edtPassWord = findViewById(R.id.edtPassword);
        edtRetypePassWord = findViewById(R.id.edtRetypePassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.etdPhone);
        radioNam = findViewById(R.id.radioNam);
        radioNu = findViewById(R.id.radioNu);
    }
    private int getId() {
        int max = 0;
        for (Bill m : billArrayList) {
            if (m.getBillId() > max) {
                max = m.getBillId();
            }
        }
        if (billArrayList.size() == 0) {
            return max;
        }
        return max + 1;
    }

    /*private int idBill() {
        ArrayList<Bill> list = new ArrayList<>();
        list = sqlBill.getListBillSQL();
        int max = 0;
        for (Bill bill: list) {
            if (bill.getBillId() > max) {
                max = bill.getBillId();
            }
        }
        return max;

    }*/

}

