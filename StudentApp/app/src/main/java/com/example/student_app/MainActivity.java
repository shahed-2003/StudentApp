package com.example.student_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;

import com.example.student_app.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean backPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initially disable the login button
        binding.btnLogin.setEnabled(false);

        // TextWatcher for Email and Password
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateInputs();
            }
        };
        binding.etEmail.addTextChangedListener(watcher);
        binding.etPassword.addTextChangedListener(watcher);

        // Login button click
        binding.btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });
    }

    private void validateInputs() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString();
        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean isPasswordValid = password.length() >= 6;
        binding.btnLogin.setEnabled(isEmailValid && isPasswordValid);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }
        backPressedOnce = true;
        Snackbar.make(binding.getRoot(), "Press back again to exit", Snackbar.LENGTH_SHORT).show();
        // Reset flag after 2 seconds
        binding.getRoot().postDelayed(() -> backPressedOnce = false, 2000);
    }
}
