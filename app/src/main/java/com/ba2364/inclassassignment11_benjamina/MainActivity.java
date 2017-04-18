package com.ba2364.inclassassignment11_benjamina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;


    private DatabaseReference citiesReference = FirebaseDatabase.getInstance().getReference("cities");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesAdapter = new CitiesAdapter(citiesReference);


        recyclerView.setAdapter(citiesAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        citiesAdapter.cleanup(); // Stop listening if the activity is destroyed
    }


    public void addCity(View view) {
        String id = UUID.randomUUID().toString();
        Random random = new Random();
        citiesReference.child(id).setValue(new City(id, "Somewhere", random.nextInt(100), random.nextBoolean()));
    }
}
