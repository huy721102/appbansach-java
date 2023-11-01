package com.example.appbansachnew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansachnew.Controller.SenDataUpdateCart;
import com.example.appbansachnew.Fragment.CartFragment;
import com.example.appbansachnew.Manage.AddBookActivity;
import com.example.appbansachnew.Model.SelectedBook;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<SelectedBook> list;
    private CartFragment context;

    public CartAdapter(ArrayList<SelectedBook> list, CartFragment context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_add_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(list.get(position).getPathImage());
        holder.textViewName.setText(list.get(position).getNameBook());
        holder.textViewPrice.setText(String.valueOf(list.get(position).getPriceBook()));
        holder.textViewNumber.setText(String.valueOf(list.get(position).getNumberOfSelectedBook()));

        holder.imageViewSummation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.textViewNumber.getText().toString().trim());
                holder.textViewNumber.setText(String.valueOf(number + 1));
                list.get(holder.getAdapterPosition()).setNumberOfSelectedBook(Integer.valueOf(holder.textViewNumber.getText().toString().trim()));
            }
        });

        holder.imageViewSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.textViewNumber.getText().toString());
                if (number > 1) {
                    holder.textViewNumber.setText(String.valueOf(number - 1));
                    list.get(holder.getAdapterPosition()).setNumberOfSelectedBook(Integer.valueOf(holder.textViewNumber.getText().toString().trim()));
                } else {
                    Toast.makeText(context.getContext(), "Số lượng sach phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.imageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.senDataUpdateCart.RemoveBookCart(list, holder.getAdapterPosition());
            }
        });

        /*holder.updateBookCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.senDataUpdateCart.UpdateBookCart(list,holder.getAdapterPosition());
            }
        });*/
        holder.imageViewSummation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number=Integer.parseInt(holder.textViewNumber.getText().toString().trim());
                holder.textViewNumber.setText(String.valueOf(number+1));
                list.get(holder.getAdapterPosition()).setNumberOfSelectedBook(Integer.valueOf(holder.textViewNumber.getText().toString().trim()));
                holder.senDataUpdateCart.UpdateBookCart(list,holder.getAdapterPosition());
            }
        });
        holder.imageViewSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number=Integer.parseInt(holder.textViewNumber.getText().toString().trim());
                holder.textViewNumber.setText(String.valueOf(number-1));
                list.get(holder.getAdapterPosition()).setNumberOfSelectedBook(Integer.valueOf(holder.textViewNumber.getText().toString().trim()));
                holder.senDataUpdateCart.UpdateBookCart(list,holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, imageViewRemove,updateBookCart,imageViewSummation,imageViewSubtraction;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewNumber;
        private SenDataUpdateCart senDataUpdateCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageBook);
            textViewName = itemView.findViewById(R.id.textName);
            textViewPrice = itemView.findViewById(R.id.textPrice);
            textViewNumber = itemView.findViewById(R.id.numberBook);
            imageViewRemove = itemView.findViewById(R.id.removeBookCart);
           /* updateBookCart = itemView.findViewById(R.id.updateBookCart);*/
            imageViewSummation = itemView.findViewById(R.id.summationCart);
            imageViewSubtraction = itemView.findViewById(R.id.subtractionCart);
            senDataUpdateCart = (SenDataUpdateCart) context;

        }
    }
}
