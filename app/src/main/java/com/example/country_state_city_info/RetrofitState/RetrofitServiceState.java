package com.example.country_state_city_info.RetrofitState;

import com.example.country_state_city_info.Model.StatesListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitServiceState {

    @GET("main/state.json")
    Call<List<StatesListModel>> fetchStateList();
}
