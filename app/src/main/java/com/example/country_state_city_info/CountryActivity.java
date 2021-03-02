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

import com.example.country_state_city_info.Adapters.CountryAdapter;
import com.example.country_state_city_info.Model.Country;
import com.example.country_state_city_info.Model.Country_;
import com.example.country_state_city_info.Retrofit.RetrofitClient;
import com.example.country_state_city_info.Retrofit.RetrofitService;
import com.example.country_state_city_info.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RetrofitService service = RetrofitClient.getCountry();
        Call<Country> call = service.fetchCountry();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Country list = response.body();

                Gson gson = new Gson();
                String json = gson.toJson(list);
                Log.d("State---", "onResponse: "+json);
                Log.d("State---", "URL: "+call.request().url());

                for (int i = 0; i < list.getCountries().size(); i++) {
                    Log.i("Response", "Country Name: " + (i + 1) + " " + list.getCountries().get(i).getName() + " sortname: " + list.getCountries().get(i).getSortname() + " Phone Code: " + list.getCountries().get(i).getPhoneCode());
                }
                Log.i("Total", "onResponse: " + list.getCountries().size());
                binding.pbCountryProgress.setVisibility(View.GONE);
                setMyAdapter(list);
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(com.example.country_state_city_info.CountryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("state---", "onFailure: "+t.toString());

            }
        });
    }

    private void setMyAdapter(Country list) {

        /*ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(MainActivity.this,android.R.layout.simple_list_item_1, Collections.singletonList((Country) list));
        binding.lvListView.setAdapter(adapter);*/

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvRecyclerCountries.setLayoutManager(layoutManager);

        CountryAdapter countryAdapter = new CountryAdapter(this, list);
        binding.rvRecyclerCountries.setAdapter(countryAdapter);

        countryAdapter.setListener(new OnClickListener<Country_>() {
            @Override
            public void OnClick(Country_ current, int position, String mode) {
                Intent intent = getIntent();
                intent.putExtra("CountryData",current.getName());
                intent.putExtra("Id",current.getId());
                setResult(Activity.RESULT_OK,intent);
                finish();
                Toast.makeText(com.example.country_state_city_info.CountryActivity.this, "OnClick " +current.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}