package com.ba2364.inclassassignment11_benjamina;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class CitiesAdapter extends FirebaseRecyclerAdapter<City, CityViewHolder> {


    public CitiesAdapter(Query ref) {
        super(City.class, R.layout.card_view_city, CityViewHolder.class, ref);
    }


    @Override
    protected void populateViewHolder(CityViewHolder viewHolder, City city, int position) {
        viewHolder.bind(city);
    }
}





