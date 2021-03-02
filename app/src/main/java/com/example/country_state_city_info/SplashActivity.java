package com.example.country_state_city_info;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.country_state_city_info.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}