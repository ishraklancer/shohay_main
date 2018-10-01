package com.shohay.shohay_main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter {
    private Activity context;
    private List<User> orderlist;

    public OrderAdapter(@NonNull Activity context, List<User> orderlist) {
        super(context, R.layout.order_item, orderlist);
        this.context = context;
        this.orderlist = orderlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.order_item, null, true);
        TextView serv_pro = item.findViewById(R.id.order_serv);
        TextView pldate = item.findViewById(R.id.order_date);
//        RatingBar  = item.findViewById(R.id.dhupi_rating);
        TextView status = item.findViewById(R.id.status);
        TextView taka = item.findViewById(R.id.order_taka);
        TextView comdate = item.findViewById(R.id.complete_date);

        User user = orderlist.get(position);

        /// TODO: set item info

        return item;
    }
}
