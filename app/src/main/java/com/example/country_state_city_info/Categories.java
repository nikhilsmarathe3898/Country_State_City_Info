package com.example.country_state_city_info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.country_state_city_info.Adapters.AdapterDatabase;
import com.example.country_state_city_info.AsyncTask.MyTask;
import com.example.country_state_city_info.Model.CountryStateCity;
import com.example.country_state_city_info.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.country_state_city_info.DatabaseHelper.CITY;
import static com.example.country_state_city_info.DatabaseHelper.COUNTRY;
import static com.example.country_state_city_info.DatabaseHelper.STATE;

public class Categories extends AppCompatActivity {

    private ActivityCategoriesBinding binding;
    int CountryID = 0;
    String StateID = "";
    String CityID = "";

    String country, state, city;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = new DatabaseHelper(this);
    }

    public void ll_City(View view) {
        Intent intentCity = new Intent(this, CityActivity.class);
        intentCity.putExtra("StateID", StateID); // PAss data from State to City Activity
        startActivityForResult(intentCity, 3);
        Toast.makeText(this, "City", Toast.LENGTH_SHORT).show();

    }

    public void ll_State(View view) {

        Intent intentState = new Intent(this, StateActivity.class);
        intentState.putExtra("CountryID", CountryID); // Pass data from Country(Main) to State Activity
        startActivityForResult(intentState, 2);
        Toast.makeText(this, "State", Toast.LENGTH_SHORT).show();

    }

    public void ll_Country(View view) {
        Intent intent = new Intent(this, CountryActivity.class);
         startActivityForResult(intent,1);
        Toast.makeText(this, "Country", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String countryName = data.getStringExtra("CountryData");
                CountryID = data.getIntExtra("Id", 0);
                binding.tvCountry.setText(countryName);
                country = countryName;
                Toast.makeText(this, "Country_Id" + CountryID, Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String stateName = data.getStringExtra("StateName");
                StateID = data.getStringExtra("StateID");
                if (StateID.equalsIgnoreCase("0")) {
                    binding.llCity.setEnabled(false);
                    binding.tvState.setText(stateName);
                    binding.tvCity.setText(stateName);
                    binding.llButton.setVisibility(View.VISIBLE);
                } else {
                    binding.tvState.setText(stateName);
                    state = stateName;
                    Toast.makeText(this, "State ID: " + StateID + "\n" + "State Name: " + stateName, Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (requestCode == 3) {
            String cityName = data.getStringExtra("CityName");
            CityID = data.getStringExtra("CityID");
            binding.tvCity.setText(cityName);
            city = cityName;
            binding.llButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "City ID: " + CityID + "\n" + "City Name: " + cityName, Toast.LENGTH_SHORT).show();
        }

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add Data Into Database..
                String desh = binding.tvCountry.getText().toString();
                String rajya = binding.tvState.getText().toString();
                String shahar = binding.tvCity.getText().toString();
                if (desh.length() != 0 && rajya.length() != 0 && shahar.length() != 0) {
                    AddData(desh, rajya, shahar);
                    binding.tvCountry.setText("");
                    binding.tvState.setText("");
                    binding.tvCity.setText("");

                    Log.e("--DatabaseOperation--", "Country: " + country + ", State: " + state + ", City: " + city);
                    Toast.makeText(Categories.this, "Data Stored..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Categories.this, "You must need to fill all fields..!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = findViewById(R.id.tvStatus);

                MyTask myTask = new MyTask(Categories.this, textView, binding.btnViewData);
                myTask.execute();
                binding.btnViewData.setEnabled(false);
                displayListData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Refresh").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("Logout").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Refresh")) {
            Toast.makeText(this, "Refresh Clicked!", Toast.LENGTH_SHORT).show();
            binding.tvCountry.setText("New Country");
            binding.tvState.setText("New State");
            binding.tvCity.setText("New City");

        }
        if (item.getTitle().equals("Logout")) {
            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("flag", false);
            editor.apply();
            finish();
            Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddData(String countryData, String stateData, String cityData) {
        boolean insertData = helper.insertData(countryData, stateData, cityData);
        if (insertData) {
            Toast.makeText(this, "Data Successfully Stored..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Somethimg Went Wrong....!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayListData() {
        Log.e("--DisplayData--", "Displaying Data in Listview:");

        List<CountryStateCity> list = new ArrayList<>();

        Cursor data = helper.getData();
        while (data.moveToNext()) {
            CountryStateCity countryStateCity = new CountryStateCity();
            countryStateCity.setCountry(data.getString(data.getColumnIndex(COUNTRY)));
            countryStateCity.setState(data.getString(data.getColumnIndex(STATE)));
            countryStateCity.setCity(data.getString(data.getColumnIndex(CITY)));
            list.add(countryStateCity);
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        binding.rvStoredData.setLayoutManager(manager);

        AdapterDatabase adapterDatabase = new AdapterDatabase(this, list);
        binding.rvStoredData.setAdapter(adapterDatabase);
    }
}