package com.example.appbansachnew.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbansachnew.Controller.SenDataAddBook;
import com.example.appbansachnew.Controller.SenDataHistoryPayment;
import com.example.appbansachnew.Fragment.HistoryPaymentFragment;
import com.example.appbansachnew.Fragment.HomeFragment;
import com.example.appbansachnew.Model.Book;
import com.example.appbansachnew.Model.Payment;
import com.example.appbansachnew.R;

import java.util.ArrayList;

public class HistoryPaymentAdapter extends RecyclerView.Adapter<HistoryPaymentAdapter.ViewHolder> {
    private ArrayList<Payment> list;
    private HistoryPaymentFragment context;

    public HistoryPaymentAdapter(ArrayList<Payment> list, HistoryPaymentFragment context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUserNamePayment.setText(list.get(position).getPaymentName());
        holder.txtMoneyPayment.setText(String.valueOf(list.get(position).getPaymentTotalMoney()));
        holder.txtDatePayment.setText(list.get(position).getDatePayment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUserNamePayment, txtMoneyPayment,txtDatePayment;
        private ImageView imageRemoveHistoryPayment;
        private SenDataHistoryPayment senDataHistoryPayment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserNamePayment = itemView.findViewById(R.id.txtUserNamePayment);
            txtMoneyPayment = itemView.findViewById(R.id.txtMoneyPayment);
            imageRemoveHistoryPayment = itemView.findViewById(R.id.imageRemoveHistoryPayment);
            txtDatePayment  =itemView.findViewById(R.id.txtDatePayment);
            senDataHistoryPayment = (SenDataHistoryPayment) context;
            imageRemoveHistoryPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    senDataHistoryPayment.RemoveHistoryPayment(list, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    senDataHistoryPayment.SenDataToDetailPayment(list,getAdapterPosition());
                }
            });
        }
    }
}
