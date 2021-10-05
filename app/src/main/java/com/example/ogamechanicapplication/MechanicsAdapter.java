package com.example.ogamechanicapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MechanicsAdapter extends RecyclerView.Adapter<MechanicsAdapter.MechanicViewHolder>{
    ArrayList<MechanicsList> mechanicsLists;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;


    public MechanicsAdapter(){
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        mechanicsLists = FirebaseUtil.mMechanicsLists;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {
                MechanicsList mechanicsList = snapshot.getValue(MechanicsList.class);
                Log.d("Mechanics", mechanicsList.getCompanyName());
                mechanicsList.setId(snapshot.getKey());
                mechanicsLists.add(mechanicsList);
                notifyItemInserted(mechanicsLists.size()-1);
                
            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);


    }

    @Override
    public MechanicViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false);
        return new MechanicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  MechanicsAdapter.MechanicViewHolder holder, int position) {

        MechanicsList mechanicsList = mechanicsLists.get(position);
        holder.bind(mechanicsList);

    }

    @Override
    public int getItemCount() {
        return mechanicsLists.size();
    }

    public class MechanicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_companyName, tv_serviceType, tv_location, tv_phoneNumber, tv_price;


        public MechanicViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_companyName = itemView.findViewById(R.id.textView_company);
            tv_serviceType = itemView.findViewById(R.id.tv_serviceType);
            tv_location  = itemView.findViewById(R.id.tv_location);
            tv_phoneNumber = itemView.findViewById(R.id.tv_phoneNo);
            tv_price = itemView.findViewById(R.id.price_per_hour);
            itemView.setOnClickListener(this);
        }

        public void bind(MechanicsList mechanicsList){
            tv_companyName.setText(mechanicsList.getCompanyName());
            tv_serviceType.setText(mechanicsList.getServiceType());
            tv_location.setText(mechanicsList.getLocation());
            tv_phoneNumber.setText(mechanicsList.getPhoneNumber());
            tv_price.setText(mechanicsList.getPrice());
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));
            MechanicsList mechanicsList = mechanicsLists.get(position);
            Intent intent = new Intent(v.getContext(), MechanicActivity.class);
            intent.putExtra("Mechanic", mechanicsList);
            v.getContext().startActivity(intent);
        }
    }
}
