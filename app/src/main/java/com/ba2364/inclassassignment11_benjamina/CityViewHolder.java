package com.ba2364.inclassassignment11_benjamina;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CityViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView cityNameText;
    private TextView cityPopText;
    private TextView cityBigChecker;
    private Context context;

    public CityViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        cityNameText = (TextView) itemView.findViewById(R.id.city_name);
        cityPopText = (TextView) itemView.findViewById(R.id.city_pop);
        cityBigChecker = (TextView) itemView.findViewById(R.id.city_big);
        this.context = itemView.getContext();
    }

    public void bind(final City city) {
        cityNameText.setText(city.cityName);
        cityPopText.setText("Population: " + city.cityPopulation);
        cityBigChecker.setText("Big City: " + city.cityBig);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cityNameText.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CityAdder.class);
                intent.putExtra(Keys.CITY, city);
                context.startActivity(intent);
            }
        });

    }
}
