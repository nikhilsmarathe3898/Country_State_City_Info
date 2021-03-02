package com.example.country_state_city_info.RetrofitState;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientState {

    public static RetrofitServiceState getStateList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/nikhilsmarathe3898/country_state_city_json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitServiceState retrofitServiceState = retrofit.create(RetrofitServiceState.class);
        return retrofitServiceState;
    }
}
