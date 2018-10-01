package com.shohay.shohay_main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    public OrderFragment() {
        // Required empty public constructor
    }

    Activity context;
    OrderAdapter adapter;
    List<Order> ongoing_orders = new ArrayList<>();

    ListView ongoing_orderss;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        View thisFragment = inflater.inflate(R.layout.fragment_order, container, false);

        ongoing_orderss = thisFragment.findViewById(R.id.pen_orders);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Orders");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (ongoing_orders.size() != 0)
                    ongoing_orders.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    User user = data.getValue(User.class);
                    Order order = data.getValue(Order.class);
                    if (order.getStatus().matches("Pending") || order.getStatus().matches("Ongoing")) {
                        ongoing_orders.add(order);
                    }
                }
                lala();
                ongoing_orderss.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return thisFragment;
    }

    void lala() {
        adapter = new OrderAdapter(this.getActivity(), ongoing_orders);
    }

}
