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

import com.example.country_state_city_info.Adapters.StateAdapter;
import com.example.country_state_city_info.Model.StatesListModel;
import com.example.country_state_city_info.RetrofitState.RetrofitClientState;
import com.example.country_state_city_info.RetrofitState.RetrofitServiceState;
import com.example.country_state_city_info.databinding.ActivityStateBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateActivity extends AppCompatActivity {

    private ActivityStateBinding stateBinding;
    int countryID; // for get data from Country Json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateBinding = ActivityStateBinding.inflate(getLayoutInflater());
        View view = stateBinding.getRoot();
        setContentView(view);

        countryID = getIntent().getIntExtra("CountryID", 0); // initialize value from Categories and store into this variable.
        Log.i("Country ID", "Found: " + countryID);

        RetrofitServiceState serviceState = RetrofitClientState.getStateList();
        Call<List<StatesListModel>> call = serviceState.fetchStateList();
        call.enqueue(new Callback<List<StatesListModel>>() {
            @Override
            public void onResponse(Call<List<StatesListModel>> call, Response<List<StatesListModel>> response) {
                List<StatesListModel> list = response.body();

                for (StatesListModel sm : list) {
                    if (sm.getCountry_id().equals(101)) {
                        Log.d("State Name: ", sm.getName().length() + " " + sm.getName());
                        Toast.makeText(getApplicationContext(), sm.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
                int size = 0;
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getCountry_id().equals("101")) {
                        Log.i("Response", "State ID:" + list.get(i).getId() + (i + 1) + ", State Name: " + list.get(i).getName() + ", Country_Id: " + list.get(i).getCountry_id());
                        size++;
                    }

                }
                Log.i("Total", "onResponse: " + size);

                stateBinding.pbStateProgress.setVisibility(View.GONE);
                setStateAdapter(list);
            }

            @Override
            public void onFailure(Call<List<StatesListModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setStateAdapter(List<StatesListModel> list) {
        /*ArrayAdapter<StatesListModel> adapter = new ArrayAdapter<StatesListModel>(StateActivity.this, android.R.layout.simple_list_item_1, list);
        stateBinding.lvListView.setAdapter(adapter);*/ // only show simple data into list

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        stateBinding.rvStateList.setLayoutManager(layoutManager);

        /*--------------------- Get only Selected Country's Data------------------*/
        List<StatesListModel> filterlist = new ArrayList<>(); //create new list for getting only those data which we want.

        for (StatesListModel item : list) { // item is single data and it looping till last parameter of list.
            if (Integer.parseInt(item.getCountry_id()) == countryID) { // match both data to each Other in data
                filterlist.add(item); // add only matching data from condition & add those data into adapter(show down)
            }
        }
        //StateAdapter stateAdapter = new StateAdapter(this, list); // display whole List Data
        StateAdapter stateAdapter = new StateAdapter(this, filterlist); // here attech filterlist and it give only matching data of condition.
        stateBinding.rvStateList.setAdapter(stateAdapter);
        /*-------------------------------------------------------------------------*/

        if((filterlist.size() > 0)){
            stateAdapter.setListener(new OnClickListener<StatesListModel>() {
                @Override
                public void OnClick(StatesListModel current, int position, String mode) {
                    Intent intent = getIntent();
                    intent.putExtra("StateName", current.getName());
                    intent.putExtra("StateID", current.getId());
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                    Toast.makeText(com.example.country_state_city_info.StateActivity.this, "OnClick" + current.getName() + "\n" + "Id:" + current.getId(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Intent intent = getIntent();
            intent.putExtra("StateName", "-");
            intent.putExtra("StateID", "0");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }
}