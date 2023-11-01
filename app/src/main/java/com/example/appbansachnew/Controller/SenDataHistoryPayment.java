package com.example.appbansachnew.Controller;

import com.example.appbansachnew.Model.Payment;
import com.example.appbansachnew.Model.SelectedBook;

import java.util.ArrayList;

public interface SenDataHistoryPayment {
    public void RemoveHistoryPayment(ArrayList<Payment> list, int position);
    public void SenDataToDetailPayment(ArrayList<Payment> list, int position);
}
