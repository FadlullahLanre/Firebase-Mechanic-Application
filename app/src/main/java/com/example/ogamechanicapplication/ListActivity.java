package com.example.ogamechanicapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<MechanicsList> mechanicsLists;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//
//        FirebaseUtil.openFbReference("mechanics");
//        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
//        mDatabaseReference = FirebaseUtil.mDatabaseReference;
//        mChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
//
//                TextView mechanics = (TextView) findViewById(R.id.tv_mechanics);
//                MechanicsList mechanicsList = snapshot.getValue(MechanicsList.class);
//                mechanics.setText(mechanics.getText() + "\n" + mechanicsList.getCompanyName());
//
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull  DatabaseError error) {
//
//            }
//        };
//        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.list_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert_menu:
                Intent intent = new Intent(this, MechanicActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseUtil.removeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUtil.attachListener();
        FirebaseUtil.openFbReference("mechanics", this);
        RecyclerView recyclerView = findViewById(R.id.rvMechanics);
        final MechanicsAdapter mechanicsAdapter = new MechanicsAdapter();
        recyclerView.setAdapter(mechanicsAdapter);
        LinearLayoutManager mechanicLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mechanicLayoutManager);
    }
}