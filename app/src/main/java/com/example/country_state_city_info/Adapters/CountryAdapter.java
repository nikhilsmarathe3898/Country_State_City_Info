package com.example.country_state_city_info.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.country_state_city_info.Model.Country;
import com.example.country_state_city_info.Model.Country_;
import com.example.country_state_city_info.OnClickListener;
import com.example.country_state_city_info.R;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private Context context;
    private Country country;
    private Country_ country_;
    private OnClickListener<Country_> listener;  // step1: Initialization


    public CountryAdapter(Context context, Country country) {
        this.context = context;
        this.country = country;

    }

    public void setListener(OnClickListener<Country_> listener) { // step2: Setter Method Declaration
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.raw_countries_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Country_ c = country.getCountries().get(position);
        holder.textView.setText(country.getCountries().get(position).getName());
        holder.Bind(listener, c, position);  // // step3: if we want to do something on CLick then use Interface
    }

    @Override
    public int getItemCount() {
        return country.getCountries().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvCounties);
        }

        void Bind(final OnClickListener<Country_> listener, final Country_ c, final int position) { // step4: Method Define
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(c, position, "");
                }
            });
        }
    }
}