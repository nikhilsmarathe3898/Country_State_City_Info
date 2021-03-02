package com.example.country_state_city_info.RetrofitCity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientCity {

    public static RetrofitServiceCity getCity(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/nikhilsmarathe3898/country_state_city_json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitServiceCity retrofitServiceCity = retrofit.create(RetrofitServiceCity.class);
        return retrofitServiceCity;
    }
}
