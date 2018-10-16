package com.shohay.shohay_main;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
public class DhupiFragment extends Fragment {


    public DhupiFragment() {
        // Required empty public constructor
    }

    Activity context;
    DhupiAdapter adapter;
    List<ProviderClass> dhupis = new ArrayList<>();

    ListView dhupiss;
    FirebaseDatabase database;
    DatabaseReference reference;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView
            (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //hides action title bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        context = this.getActivity();
        View thisFragment = inflater.inflate(R.layout.fragment_dhupi, container, false);

        swipeRefreshLayout = thisFragment.findViewById(R.id.pulltorefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: lala
            }
        });

        dhupiss = thisFragment.findViewById(R.id.dhupis);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("providers");

//        dhupiss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                toaster();
//            }
//        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dhupis.size() != 0)
                    dhupis.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    User user = data.getValue(User.class);
////                    if (!user.getDhupi_rate().matches("0")) {
//                    dhupis.add(user);
////                    }
                }
                lala();
                dhupiss.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return thisFragment;
    }

    void lala() {
        adapter = new DhupiAdapter(this.getActivity(), dhupis);
    }

    void toaster() {
        Toast.makeText(this.getContext(), "lala", Toast.LENGTH_LONG).show();
    }

}
