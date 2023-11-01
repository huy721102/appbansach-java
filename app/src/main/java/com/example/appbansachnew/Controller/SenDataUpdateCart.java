package com.example.appbansachnew.Controller;

import com.example.appbansachnew.Model.SelectedBook;

import java.util.ArrayList;

public interface SenDataUpdateCart {
    public void RemoveBookCart(ArrayList<SelectedBook> list, int position);
    public void UpdateBookCart(ArrayList<SelectedBook> list, int position);
}
