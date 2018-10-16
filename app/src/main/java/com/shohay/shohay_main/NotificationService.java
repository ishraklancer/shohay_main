package com.shohay.shohay_main;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationService extends Service {
    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    DatabaseReference reference;

    void lala() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(NotificationService.this)
                        .setSmallIcon(R.drawable.profile_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");


        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = this
                .getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);
        reference = FirebaseDatabase.getInstance().getReference("orders").child("providers").child(preferences.getString("serviceType", "").toLowerCase()).child(preferences.getString("phonenumber", ""));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Order temp = dataSnapshot1.getValue(Order.class);
                    if (temp.getStatus().toString().equals("pending")) {
                        lala();
                    } else {
                    }

                    // When you issue multiple notifications about the same type of event,
                    // it’s best practice for your app to try to update an existing notification
                    // with this new information, rather than immediately creating a new notification.
                    // If you want to update this notification at a later date, you need to assign it an ID.
                    // You can then use this ID whenever you issue a subsequent notification.
                    // If the previous notification is still visible, the system will update this existing notification,
                    // rather than create a new one. In this example, the notification’s ID is 001//

//
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
