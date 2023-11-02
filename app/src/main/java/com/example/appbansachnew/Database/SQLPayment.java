package com.example.appbansachnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbansachnew.Model.Account;
import com.example.appbansachnew.Model.Payment;

import java.time.LocalDate;
import java.util.ArrayList;

public class SQLPayment {
    DBHeplper dtbH;

    public SQLPayment(Context context) {
        dtbH = new DBHeplper(context);
    }

    public ArrayList<Payment> getListPaymentSQL(String user) {
        ArrayList<Payment> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbH.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM Payment", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                String TEST="DAY LA DAATA";
                int id  = cs.getInt(0);
                String userName = cs.getString(1);
                String name = cs.getString(2);
                String sdt = cs.getString(3);
                String address = cs.getString(4);
                String datePayment = cs.getString(5);
                int totalBook  = cs.getInt(6);
                int totalMonney  = cs.getInt(7);
                String listBook = cs.getString(8);
                if (userName.toString().trim().equals(user.toString().trim())) {
                    Payment t = new Payment(id,userName,name,sdt,address,datePayment,totalBook,totalMonney,listBook);
                    listTK.add(t);
                }
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return listTK;
    }
    public ArrayList<Payment> getListFullPaymentSQL() {
        ArrayList<Payment> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbH.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM Payment", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                int id  = cs.getInt(0);
                String userName = cs.getString(1);
                String name = cs.getString(2);
                String sdt = cs.getString(3);
                String address = cs.getString(4);
                String datePayment = cs.getString(5);
                int totalBook  = cs.getInt(6);
                int totalMonney  = cs.getInt(7);
                String listBook = cs.getString(8);

                // if (userName.toString().trim().equals(user.toString().trim())) {
                    Payment t = new Payment(id,userName,name,sdt,address,datePayment,totalBook,totalMonney,listBook);
                    listTK.add(t);
               // }
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return listTK;
    }
    public boolean setListPaymentSQL(Payment payment) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("paymentId",payment.getPaymentId());
        values.put("userNamePayment", payment.getPaymentUserName());
        values.put("name",payment.getPaymentName());
        values.put("sdt",payment.getPaymentSDT());
        values.put("address",payment.getPaymentAddress());
        values.put("datePayment", payment.getDatePayment());
        values.put("totalBook",payment.getPaymentTotalBook());
        values.put("totalMoney",payment.getPaymentTotalMoney());
        values.put("listBook",payment.getListBook());

        long r = db.insert("Payment", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean deleteListPaymentSQL(Payment payment) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        int r = db.delete("Payment", "paymentId=? AND userNamePayment=?", new String[]{String.valueOf(payment.getPaymentId()),payment.getPaymentUserName()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
