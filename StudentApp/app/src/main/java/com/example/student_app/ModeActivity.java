package com.example.student_app;

import android.os.Bundle;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.student_app.databinding.ActivityModeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ActivityModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Tabs + ViewPager2
        binding.viewPager.setAdapter(new TabsAdapter(this));
        String[] titles = {"First", "Second", "Third"};
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();

        // Back-stack behavior
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.viewPager.getCurrentItem() != 0) {
                    binding.viewPager.setCurrentItem(0);
                } else {
                    finish(); // returns to MainActivity
                }
            }
        });

        // Popup menu
        binding.btnSetMode.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(ModeActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.mode_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.mode_light) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (id == R.id.mode_night) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else if (id == R.id.mode_system) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                return true;
            });
            popup.show();
        });
    }

    // Up navigation -> MainActivity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
