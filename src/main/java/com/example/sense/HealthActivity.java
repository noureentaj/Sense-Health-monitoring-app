package com.example.sense;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class HealthActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref1,ref2,ref3;
    private TextView pul,bodytemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        pul= (TextView) findViewById (R.id.pulse);
        bodytemp= (TextView) findViewById (R.id.temp);
        database = FirebaseDatabase.getInstance();

        database=FirebaseDatabase.getInstance();
        ref1=database.getReference().child("Pi").child("pulse");
        ref2=database.getReference().child("Pi").child("temp");
        ref3 = database.getReference().child("Pi").child("fall");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String puls=dataSnapshot.getValue().toString();
                pul.setText(puls);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                String tem = dataSnapshot.getValue().toString();
                bodytemp.setText(tem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    ref3.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String falling = dataSnapshot.getValue().toString();


            if (falling.equals("true")) {
                sendNotification("FALL", "person has taken a fall");
                /*f.setText("True: 1");
                Log.d("falling", falling);
                DrawableCompat.setTint(img.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));*/

            }
            /*else {
                f.setText("False: 0");
                DrawableCompat.setTint(img.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

            }*/
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }
    private void sendNotification(String i, String j) {
        Notification.Builder builder =  new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(i)
                .setContentText(j);
        NotificationManager manager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contentIntent);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;

        manager.notify(new Random().nextInt(),notification);


    }
}
