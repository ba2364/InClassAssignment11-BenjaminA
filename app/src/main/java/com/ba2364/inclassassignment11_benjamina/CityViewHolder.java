package com.ba2364.inclassassignment11_benjamina;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CityViewHolder extends RecyclerView.ViewHolder {


    private CardView cardView;
    private TextView cityNameText;
    private TextView cityPopText;
    private TextView cityBigText;
    private Context context;


    public CityViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        cityNameText = (TextView) itemView.findViewById(R.id.city_name);
        cityPopText = (TextView) itemView.findViewById(R.id.city_pop);
        cityBigText = (TextView) itemView.findViewById(R.id.city_big);
        this.context = itemView.getContext();
    }


    public void bind(final City city) {
        cityNameText.setText(city.name);
        cityPopText.setText("Population: " + city.pop);
        cityBigText.setText("Big City: " + city.bigCity);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cityNameText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

