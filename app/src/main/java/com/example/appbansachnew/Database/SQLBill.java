package com.example.appbansachnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbansachnew.Model.Account;
import com.example.appbansachnew.Model.Bill;
import com.example.appbansachnew.Model.Cart;

import java.util.ArrayList;

public class SQLBill {
    DBHeplper dtbH;

    public SQLBill(Context context) {
        dtbH = new DBHeplper(context);
    }

    public ArrayList<Bill> getListBillSQL() {
        ArrayList<Bill> list = new ArrayList<>();
        SQLiteDatabase dtb = dtbH.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM Bill", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                int id = cs.getInt(0);
                String user = cs.getString(1);
                Cart cart = new Cart(new Account(user));
                Bill t = new Bill(id, cart,0,0);
                list.add(t);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        cs.close();
        return list;
    }
    public boolean setListBillSQL(Bill bill) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("billId", bill.getBillId());
        values.put("userName", bill.getCart().getAccount().getUserName());
        long r = db.insert("Bill", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
   /* public boolean updateListBillSQL(Account tk) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pas
        sWord", tk.getPassWord());
        int r = db.update("Bill", values, "userName=?", new String[]{tk.getUserName()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean deleteListBillSQL(Account tk) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        int r = db.delete("Bill", "userName=?", new String[]{tk.getUserName()});
        if (r <= 0) {
            return false;
        }
        return true;
    }*/
}
