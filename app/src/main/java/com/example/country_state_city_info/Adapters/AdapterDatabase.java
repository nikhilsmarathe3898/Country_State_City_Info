package com.example.country_state_city_info.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.country_state_city_info.Model.CountryStateCity;
import com.example.country_state_city_info.R;

import java.util.List;

public class AdapterDatabase extends RecyclerView.Adapter<AdapterDatabase.DatabaseViewHolder> {

    Context context;
    List<CountryStateCity> clsData;

    public AdapterDatabase() {
    }

    public AdapterDatabase(Context context, List<CountryStateCity> clsData) {
        this.context = context;
        this.clsData = clsData;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.raw_country,parent,false);
        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
    final CountryStateCity current = clsData.get(position);
    holder.country.setText(current.getCountry());
    holder.state.setText(current.getState());
    holder.city.setText(current.getCity());
    }

    @Override
    public int getItemCount() {
        return clsData.size();
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView country, state, city;

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.tvRowCountry);
            state = itemView.findViewById(R.id.tvRowState);
            city = itemView.findViewById(R.id.tvRowCity);
        }
    }
}
