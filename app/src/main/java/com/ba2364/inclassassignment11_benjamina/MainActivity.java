package com.ba2364.inclassassignment11_benjamina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private List<City> cities;



    private DatabaseReference citiesReference = FirebaseDatabase.getInstance().getReference("cities");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityAdapter = new CityAdapter(cities, this);
        recyclerView.setAdapter(cityAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cityAdapter.cleanup(); // Stop listening if the activity is destroyed
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                cities.add(getRandomCity());
                cityAdapter.notifyDataSetChanged();   // this is important to inform the program that data has changed
                Toast.makeText(this, "City added", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void addCity(View view) {
        String id = UUID.randomUUID().toString();
        Random random = new Random();
        citiesReference.child(id).setValue(new City(id, "Somewhere", random.nextInt(100), random.nextBoolean()));
    }
}
