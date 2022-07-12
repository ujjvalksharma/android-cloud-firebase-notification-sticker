package com.example.a8_temp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SaveInFirebase<T> {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SaveInFirebase(String tableName){
        this.firebaseDatabase = FirebaseDatabase.getInstance("https://fir-example-e11f0-default-rtdb.firebaseio.com/");
        this.databaseReference = firebaseDatabase.getReference(tableName);
    }

    public T save(T t){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.push().setValue(t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return t;
    }

}
