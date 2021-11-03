package com.spylabs.MiniDungeonWear;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.spylabs.MiniDungeonWear.ClickHandlers.MenuClickHandlers;
import com.spylabs.MiniDungeonWear.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;
    private Button btnStart;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnStart = binding.btnStart;

        btnStart.setOnClickListener(MenuClickHandlers.getStartButtonClickHandler());

        btnSettings = binding.btnSetting;

        btnSettings.setOnClickListener(MenuClickHandlers.getSettingsButtonClickHandler());
    }
}