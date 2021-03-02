package com.example.country_state_city_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.country_state_city_info.Adapters.CityAdapter;
import com.example.country_state_city_info.Model.City;
import com.example.country_state_city_info.RetrofitCity.RetrofitClientCity;
import com.example.country_state_city_info.RetrofitCity.RetrofitServiceCity;
import com.example.country_state_city_info.databinding.ActivityCityBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends AppCompatActivity {

    private ActivityCityBinding cityBinding;
    String stateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cityBinding = ActivityCityBinding.inflate(getLayoutInflater());
        View view = cityBinding.getRoot();
        setContentView(view);

        stateID = getIntent().getStringExtra("StateID");
        Log.i("city---", "State ID: " + stateID);

        RetrofitServiceCity serviceCity = RetrofitClientCity.getCity();
        Call<List<City>> call = serviceCity.fetchCity();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> cityList = response.body();

                // ------------
                Gson gson = new Gson();
                String json = gson.toJson(cityList);
                Log.d("city---", "onResponse: " + json);
                Log.d("city---", "URL: " + call.request().url());

                List<City> filterCityList = new ArrayList<>();
                for (City cityitem : cityList) {
                    if (cityitem.getStateId().equalsIgnoreCase(stateID)) {
                        filterCityList.add(cityitem);
                        Log.i("city---", "setCityAdapter: " + cityitem.getName());
                    }
                }
                Log.i("City -->", "Total Cities: " + filterCityList.size());


                setCityAdapter(filterCityList);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(com.example.country_state_city_info.CityActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCityAdapter(final List<City> cityList) {
        /*ArrayAdapter<City> arrayAdapter = new ArrayAdapter<City>(CityActivity.this,android.R.layout.simple_list_item_1,cityList);
        cityBinding.lvCities.setAdapter(arrayAdapter);*/

        Log.d("City -> ", "setCityAdapter: " + cityList.size());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cityBinding.rvCities.setLayoutManager(layoutManager);

        CityAdapter cityAdapter = new CityAdapter(this, cityList);
        cityBinding.rvCities.setAdapter(cityAdapter);

        if ((cityList.size() > 0)) {
            cityAdapter.setListener(new OnClickListener<City>() {
                @Override
                public void OnClick(City current, int position, String mode) {
                    Intent intent = getIntent();
                    intent.putExtra("CityName", current.getName());
                    intent.putExtra("CityID", current.getId());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            Intent intent = getIntent();
            intent.putExtra("CityName", "-");
            intent.putExtra("CityID", 0);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
