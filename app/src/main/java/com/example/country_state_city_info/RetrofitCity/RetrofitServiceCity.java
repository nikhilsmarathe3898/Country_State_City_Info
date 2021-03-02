package com.example.country_state_city_info.RetrofitCity;

import com.example.country_state_city_info.Model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitServiceCity {

    @GET("main/city.json")
    Call<List<City>> fetchCity();
}
