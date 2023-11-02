package com.example.appbansachnew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansachnew.Controller.SenDataAddBook;
import com.example.appbansachnew.Fragment.HomeFragment;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private ArrayList<Book> list;
    private  HomeFragment context;

    public BookAdapter(ArrayList<Book> list, HomeFragment context) {
        this.list = list;
        this.context = context;
        String tess123t="di";
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(list.get(position).getPathImage());
        holder.textViewName.setText(list.get(position).getNameBook());
        holder.textViewPrice.setText(String.valueOf(list.get(position).getPriceBook()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewPrice;
        private SenDataAddBook senDataAddBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageBook);
            textViewName = itemView.findViewById(R.id.textName);
            textViewPrice = itemView.findViewById(R.id.textPrice);
            senDataAddBook = (SenDataAddBook) context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    senDataAddBook.addBook(list,position);
                }
            });
        }
    }
}
