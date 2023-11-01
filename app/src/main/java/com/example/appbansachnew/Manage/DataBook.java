package com.example.appbansachnew.Manage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.appbansachnew.MainActivity;
import com.example.appbansachnew.Model.Book;

import java.io.IOException;
import java.util.ArrayList;

public class DataBook {
    private ArrayList<Book> list;
    private Context context;

    public DataBook(ArrayList<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<Book> getListBook() {
        list = new ArrayList<>();
        try {
            String[] fileNames = context.getApplicationContext().getAssets().list("ImageBook");
            //ic_eagle.png
            String pathImage1 = "ImageBook" + "/" + fileNames[0];
            Bitmap bitmapImage1 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage1));
            list.add(new Book(1, "Ngã ở đâu đứng nên ở đó", "Cuộc sống", 20, 0, "Hồng Sơn", 2020, 300, bitmapImage1));

            String pathImage2 = "ImageBook" + "/" + fileNames[1];
            Bitmap bitmapImage2 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage2));
            list.add(new Book(2, "Đắc nhân tâm", "Cuộc sống", 30, 0, "Hồng Sơn", 2020, 300, bitmapImage2));

            String pathImage3 = "ImageBook" + "/" + fileNames[2];
            Bitmap bitmapImage3 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage3));
            list.add(new Book(3,"Nếu chỉ còn một ngày để sống","Cuộc sống",10,0,"Hồng Sơn",2020,300,bitmapImage3));

            String pathImage4 = "ImageBook" + "/" + fileNames[3];
            Bitmap bitmapImage4 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage4));
            list.add(new Book(4,"Lược sử vạn vật","Cuộc sống",40,0,"Hồng Sơn",2020,300,bitmapImage4));

            String pathImage5 = "ImageBook" + "/" + fileNames[4];
            Bitmap bitmapImage5 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage5));
            list.add(new Book(5,"Ta balo trên đất Á","Cuộc sống",50,0,"Hồng Sơn",2020,300,bitmapImage5));

            String pathImage6 = "ImageBook" + "/" + fileNames[5];
            Bitmap bitmapImage6 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage6));
            list.add(new Book(6,"Một lít nước mắt","Cuộc sống",70,0,"Hồng Sơn",2020,300,bitmapImage6));

            String pathImage7 = "ImageBook" + "/" + fileNames[6];
            Bitmap bitmapImage7 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage7));
            list.add(new Book(7,"Không gia đình","Cuộc sống",80,0,"Hồng Sơn",2020,300,bitmapImage7));

            String pathImage8 = "ImageBook" + "/" + fileNames[7];
            Bitmap bitmapImage8 = BitmapFactory.decodeStream(context.getApplicationContext().getAssets().open(pathImage8));
            list.add(new Book(8,"Hai số phận","Cuộc sống",20,0,"Hồng Sơn",2020,300,bitmapImage8));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
