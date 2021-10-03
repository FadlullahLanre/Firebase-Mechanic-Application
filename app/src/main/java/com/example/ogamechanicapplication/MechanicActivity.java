package com.example.ogamechanicapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MechanicActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText company_Name, C_location, pricePhour, pNumber, service_type;
    MechanicsList mechanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        company_Name = findViewById(R.id.company_Name_edit_text);
        C_location = findViewById(R.id.company_location_edit_text);
        pricePhour = findViewById(R.id.price_perHour_edit_text);
        pNumber = findViewById(R.id.phone_number_edit_text);
        service_type = findViewById(R.id.service_type_edit_text);
        Intent intent = getIntent();
        MechanicsList mechanic = (MechanicsList) intent.getSerializableExtra("Mechanic");
        if (mechanic == null){
            mechanic = new MechanicsList();
        }
        this.mechanic = mechanic;
        company_Name.setText(mechanic.getCompanyName());
        C_location.setText(mechanic.getLocation());
        pricePhour.setText(mechanic.getPrice());
        pNumber.setText(mechanic.getPhoneNumber());
        service_type.setText(mechanic.getServiceType());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Mechanic Saved!", Toast.LENGTH_SHORT).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteDeal();
                Toast.makeText(this, "Mechanic Deleted", Toast.LENGTH_SHORT).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clean() {
        company_Name.setText("");
        service_type.setText("");
        C_location.setText("");
        pricePhour.setText("");
        pNumber.setText("");
        company_Name.requestFocus();

    }

    private void saveDeal() {
        mechanic.setCompanyName(company_Name.getText().toString());
        mechanic.setServiceType(service_type.getText().toString());
        mechanic.setLocation(C_location.getText().toString());
        mechanic.setPrice(pricePhour.getText().toString());
        mechanic.setPhoneNumber(pNumber.getText().toString());
        if (mechanic.getId() == null){
            mDatabaseReference.push().setValue(mechanic);

        }
        else{
            mDatabaseReference.child(mechanic.getId()).setValue(mechanic);
        }

    }

    private void deleteDeal(){
        if(mechanic == null){
            Toast.makeText(this, "Save mechanic details before deleting!", Toast.LENGTH_LONG).show();
            return;
        }
        mDatabaseReference.child(mechanic.getId()).removeValue();
    }

    private void backToList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}