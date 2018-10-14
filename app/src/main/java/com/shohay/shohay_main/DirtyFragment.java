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
public class DirtyFragment extends Fragment {


    public DirtyFragment() {
        // Required empty public constructor
    }

    Activity context;
    moilaAdapter adapter;
    List<User> moilas = new ArrayList<>();

    ListView moilass;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //hides action title bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        context = this.getActivity();
        View thisFragment = inflater.inflate(R.layout.fragment_dirty, container, false);

        moilass = thisFragment.findViewById(R.id.dirties);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (moilas.size() != 0)
                    moilas.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
//                    if (!user.getMoila_rate().matches("0")) {
//                        moilas.add(user);
//                    }
                }
                lala();
                moilass.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return thisFragment;
    }

    void lala() {
        adapter = new moilaAdapter(this.getActivity(), moilas);
    }

}
