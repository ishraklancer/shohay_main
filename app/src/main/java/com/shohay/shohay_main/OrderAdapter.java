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
    private List<Order> orderlist;

    public OrderAdapter(@NonNull Activity context, List<Order> orderlist) {
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

        Order order = orderlist.get(position);

//        serv_pro.setText("You Have Taken Service From " + order.getServ_pro());
//        pldate.setText("Order Placed Date: " + order.getOrder_timestamp());
//        comdate.setText("Complete Date: " + order.getDelivery_timestamp());
//        status.setText("Status: " + order.getStatus());
//        taka.setText("Contracted Money: " + order.getPayment());

        /// TODO: set item info

        return item;
    }
}
