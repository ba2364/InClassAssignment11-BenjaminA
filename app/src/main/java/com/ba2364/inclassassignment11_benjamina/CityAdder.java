package com.ba2364.inclassassignment11_benjamina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class CityAdder extends AppCompatActivity {

    private DatabaseReference cityReference = FirebaseDatabase.getInstance().getReference("cityNameBox");
    private ArrayList<City> cityList = new ArrayList<>();

    private EditText cityNameBox;
    private EditText cityPopBox;
    private CheckBox cityBigChecker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

        cityNameBox = (EditText) findViewById(R.id.city_name_box);
        cityPopBox = (EditText) findViewById(R.id.city_pop_box);
        cityBigChecker = (CheckBox) findViewById(R.id.bigSwitch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adder, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                String id = UUID.randomUUID().toString();
                Random random = new Random();
                String cityName = cityNameBox.getText().toString();
                int cityPop = Integer.parseInt(cityPopBox.getText().toString());
                boolean cityBigChecked = cityBigChecker.isChecked();
                cityReference.child(id).setValue(new City(id, cityName, cityPop, cityBigChecked));

                Intent intentAdd = new Intent(this, MainActivity.class);
                startActivity(intentAdd);
                return true;
            case R.id.menu_item_trash:
                Intent intentRemove = new Intent(this, MainActivity.class);
                startActivity(intentRemove);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

