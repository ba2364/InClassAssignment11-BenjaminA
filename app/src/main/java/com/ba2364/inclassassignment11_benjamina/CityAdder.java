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

    private DatabaseReference cityReference = FirebaseDatabase.getInstance().getReference("city");
    private ArrayList<City> cities = new ArrayList<>();

    private EditText nameEditBox;
    private EditText popEditBox;
    private CheckBox bigCityCheck;
    private City cityObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

        if (getIntent() != null)
            cityObject = (City) getIntent().getSerializableExtra(Keys.CITY);

        nameEditBox = (EditText) findViewById(R.id.city_name_box);
        popEditBox = (EditText) findViewById(R.id.city_pop_box);
        bigCityCheck = (CheckBox) findViewById(R.id.bigCheck);

        if (cityObject != null) {
            nameEditBox.setText(cityObject.cityName);
            popEditBox.setText(String.valueOf(cityObject.cityPopulation));
            bigCityCheck.setChecked(cityObject.cityBig);

        }
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
                String cityName = nameEditBox.getText().toString();
                int cityPop = Integer.parseInt(popEditBox.getText().toString());
                boolean isBig = bigCityCheck.isChecked();
                cityReference.child(id).setValue(new City(id, cityName, cityPop, isBig));

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_trash:
                if (cityObject != null)
                    cityReference.child(cityObject.id).removeValue();
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void addPlanet() {
        String id = UUID.randomUUID().toString();
        City p = new City(id, nameEditBox.getText().toString(), Integer.parseInt(popEditBox.getText().toString()), bigCityCheck.isChecked());
        cityReference.child(id).setValue(p);
    }

    public void savePlanet() {
        cityObject.cityName = nameEditBox.getText().toString();
        cityObject.cityPopulation = Integer.parseInt(popEditBox.getText().toString());
        cityObject.cityBig = bigCityCheck.isChecked();

        cityReference.child(cityObject.id).setValue(nameEditBox);
    }
}
