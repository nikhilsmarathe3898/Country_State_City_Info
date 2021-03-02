package com.example.country_state_city_info.Retrofit;

import com.example.country_state_city_info.Model.Country;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

@GET("main/countries.json")/*countries.json*/
    Call<Country> fetchCountry();
}