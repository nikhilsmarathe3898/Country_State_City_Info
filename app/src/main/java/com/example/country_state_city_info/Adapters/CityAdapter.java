package com.example.country_state_city_info.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.country_state_city_info.Model.City;
import com.example.country_state_city_info.OnClickListener;
import com.example.country_state_city_info.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private Context context;
        private List<City> cityList;
    private OnClickListener<City> listener;

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    public void setListener(OnClickListener<City> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.raw_countries_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final City ct = cityList.get(position);
        holder.textView.setText(ct.getName());
        /*holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "City ID:" + cityList.get(position).getId() + "\n" + "City Name: " + cityList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.Bind(listener, ct, position); // Directly Get Click on Perticular Item.
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvCounties);
        }

        public void Bind(final OnClickListener<City> listener, final City ct, final int position) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(ct, position, "");
                }
            });
        }
    }
}