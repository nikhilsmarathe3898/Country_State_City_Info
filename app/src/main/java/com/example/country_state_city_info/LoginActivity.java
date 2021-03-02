package com.example.country_state_city_info;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.country_state_city_info.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
        checkLogin();
    }

    private void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag) {
            startActivity(new Intent(this, Categories.class));
            finish();
        }
    }

    public void btn_submit(View view) {

        String mail = loginBinding.etEmail.getText().toString().trim();
        String password = loginBinding.etPassword.getText().toString().trim();

        if (!mail.isEmpty() || !password.isEmpty()) {

            if (mail.equals("nikhil@gmail.com") && password.equals("1234")) {
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("flag", true);
                editor.commit();
                checkLogin();
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Email or Password MisMatch!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Email : nikhil@gmail.com \n Password : 1234", Toast.LENGTH_SHORT).show();
        }
    }
}