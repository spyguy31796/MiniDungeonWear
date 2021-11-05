package com.spylabs.MiniDungeonWear.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.spylabs.MiniDungeonWear.Models.Character;
import com.spylabs.MiniDungeonWear.Models.Location;
import com.spylabs.MiniDungeonWear.Managers.LocationManager;
import com.spylabs.MiniDungeonWear.databinding.ActivityGameBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class GameActivity extends Activity {

    private ActivityGameBinding binding;

    private TextView clock;

    private TextView currentHP;

    private TextView maxHP;

    private TextView level;

    private ImageView viewPort;

    private ImageView enemyViewPort;

    private TextView description;

    private Character c;

    private LocationManager locationManager = new LocationManager();

    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        bindElements();

        updateClock();

        // TODO: Load state
        c = new Character();

        updateCharacter();

        currentLocation = locationManager.getNewRandomLocation();

        // TODO: Persist with background service.
        // Perform Actions Every Minute
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIME_TICK");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentLocation = locationManager.getNewRandomLocation();
                updateClock();
                updateLocation();
            }
        };
        registerReceiver(receiver, filter);
    }

    private void bindElements() {
        clock = binding.txtClock;
        currentHP = binding.txtCurrentHP;
        maxHP = binding.txtMaxHP;
        level = binding.txtLevel;
        viewPort = binding.imgViewPort;
        description = binding.txtDescription;
        enemyViewPort = binding.imgEnemyViewPort;
    }

    private void updateClock() {
        long now = System.currentTimeMillis();
        Calendar c = new GregorianCalendar();
        c.setTimeZone(TimeZone.getDefault());
        c.setTimeInMillis(now);
        clock.setText(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
    }

    private void updateCharacter() {
        currentHP.setText(String.valueOf(c.getStats().getCurrentHealth()));
        maxHP.setText(String.valueOf(c.getStats().getMaxHealth()));
        level.setText(String.valueOf(c.getLevel()));
    }

    private void updateLocation() {
        // TODO: This is not efficient, should atleast keep a mapping of ids to resources for quick changes.
        Context context = viewPort.getContext();
        int id = context.getResources().getIdentifier(currentLocation.getImageResource(), "drawable", context.getPackageName());
        viewPort.setImageResource(id);

        if (currentLocation.getEnemyResource() != null) {
            context = enemyViewPort.getContext();
            id = context.getResources().getIdentifier(currentLocation.getEnemyResource(), "drawable", context.getPackageName());
            enemyViewPort.setImageResource(id);
        } else {
            enemyViewPort.setImageDrawable(null);
        }
    }
}