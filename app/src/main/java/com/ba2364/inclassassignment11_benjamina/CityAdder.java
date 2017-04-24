package com.ba2364.inclassassignment11_benjamina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CityAdder extends AppCompatActivity {

    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    private DatabaseReference cityReference = data.getReference("cityNameBox");


    private EditText cityNameBox;
    private EditText cityPopBox;
    private CheckBox cityBigChecker;

    private City city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_adder);

        if (getIntent() != null)
            city = (City) getIntent().getSerializableExtra(Keys.CITY);

        cityNameBox = (EditText) findViewById(R.id.city_name_box);
        cityPopBox = (EditText) findViewById(R.id.city_pop_box);
        cityBigChecker = (CheckBox) findViewById(R.id.bigSwitch);

        if (city != null)
        {
            cityNameBox.setText(city.name);
            cityPopBox.setText(String.valueOf(city.pop));
            cityBigChecker.setChecked(city.bigCity);
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
                if (city == null)
                    addCity();
                else
                    saveCity();
                finish();
                return true;
            case R.id.menu_item_trash:
                if (city != null)
                    cityReference.child(city.id).removeValue();
                finish();
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void addCity(){
        String id = UUID.randomUUID().toString();
        City city = new City(id, cityNameBox.getText().toString(), Integer.parseInt(cityPopBox.getText().toString()), cityBigChecker.isChecked());
        cityReference.child(id).setValue(city);
    }

    public void saveCity(){
        city.name = cityNameBox.getText().toString();
        city.pop = Integer.parseInt(cityPopBox.getText().toString());
        city.bigCity = cityBigChecker.isChecked();

        cityReference.child(city.id).setValue(city);
    }
}

