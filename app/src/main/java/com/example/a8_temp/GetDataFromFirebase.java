package com.example.a8_temp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetDataFromFirebase<T> {

    final FirebaseDatabase firebaseDatabase;
    final DatabaseReference databaseReference;
    final Class<T> typeParameterClass;
    final String tableName;


    public GetDataFromFirebase(Class<T> typeParameterClass,String tableName) {
        this.typeParameterClass = typeParameterClass;
        this.firebaseDatabase = FirebaseDatabase.getInstance("https://fir-example-e11f0-default-rtdb.firebaseio.com/");
        this.databaseReference = firebaseDatabase.getReference(tableName);
        this.tableName=tableName;
    }


    public void get(T t){

      /*  databaseReference.eq
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                T t = snapshot.getValue(typeParameterClass);
                if(t==null){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       */
    }

    public boolean isPresent(T t){
        final boolean[] isPresent = {false};
        DatabaseReference rootRef = this.firebaseDatabase.getReference(tableName);

        try {
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("I am here");
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        T value = ds.getValue(typeParameterClass);
                        if (value.toString().equals(t.toString())) {
                            isPresent[0] = true;
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("I am here1");
                }
            };
            rootRef.addListenerForSingleValueEvent(valueEventListener);
        } catch(Exception e){
            System.out.println(e);
        }
System.out.println("finished");

        return isPresent[0];
    }


    public ArrayList<T> getFilteredData() {
        return new ArrayList<T>();
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<T>();
        DatabaseReference rootRef = this.firebaseDatabase.getReference(tableName);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    T value = ds.getValue(typeParameterClass);
                    list.add(value);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        rootRef.addListenerForSingleValueEvent(valueEventListener);


        return list;

    }





}
