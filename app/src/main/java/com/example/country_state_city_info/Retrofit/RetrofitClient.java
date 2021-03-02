package com.example.country_state_city_info.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitService getCountry(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/nikhilsmarathe3898/country_state_city_json/")/*https://ftouch.app:444/data/*/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }
}