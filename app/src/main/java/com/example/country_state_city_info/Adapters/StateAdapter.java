package com.example.country_state_city_info.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.country_state_city_info.Model.StatesListModel;
import com.example.country_state_city_info.OnClickListener;
import com.example.country_state_city_info.R;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.MyViewHolder> {


    private Context context;
    private List<StatesListModel> listModel;
    private OnClickListener<StatesListModel> listener;

    public StateAdapter(Context context, List<StatesListModel> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    public void setListener(OnClickListener<StatesListModel> listener) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final StatesListModel sm = listModel.get(position);
        holder.textView.setText(listModel.get(position).getName());
        holder.Bind(listener,sm,position);
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvCounties);
        }

        public void Bind(final OnClickListener<StatesListModel> listener,final StatesListModel sm, final int position) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(sm,position,"");
                }
            });
        }
    }
}