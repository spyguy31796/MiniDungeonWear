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

import com.spylabs.MiniDungeonWear.Managers.AdventureManager;
import com.spylabs.MiniDungeonWear.Managers.OptionsManager;
import com.spylabs.MiniDungeonWear.Models.Character;
import com.spylabs.MiniDungeonWear.Models.Location;
import com.spylabs.MiniDungeonWear.Managers.LocationManager;
import com.spylabs.MiniDungeonWear.Models.Menu;
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

    private AdventureManager adventureManager = new AdventureManager(new OptionsManager());

    // private LocationManager locationManager = new LocationManager();

    // private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        bindElements();

        updateClock();

        renderCharacter();

        // TODO: Persist with background service.
        // Perform Actions Every Minute
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIME_TICK");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateClock();
                adventureManager.UpdateAdventure();

                renderLocation();
                renderEvent();
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

    private void renderCharacter() {
        Character c = adventureManager.getCharacter();
        currentHP.setText(String.valueOf(c.getStats().getCurrentHealth()));
        maxHP.setText(String.valueOf(c.getStats().getMaxHealth()));
        level.setText(String.valueOf(c.getLevel()));
    }

    private void renderMenu() {
        Menu currentMenu = adventureManager.getMenuManager().getCurrentMenu();
    }

    private void renderLocation() {
        // TODO: This is not efficient, should atleast keep a mapping of ids to resources for quick changes.
        Context context = viewPort.getContext();
        int id = context.getResources().getIdentifier(adventureManager.getCurrentLocation().getImageResource(), "drawable", context.getPackageName());
        viewPort.setImageResource(id);

        // TODO: Remove this
        if (adventureManager.getCurrentLocation().getEnemyResource() != null) {
            context = enemyViewPort.getContext();
            id = context.getResources().getIdentifier(adventureManager.getCurrentLocation().getEnemyResource(), "drawable", context.getPackageName());
            enemyViewPort.setImageResource(id);
        } else {
            enemyViewPort.setImageDrawable(null);
        }
    }

    private void renderEvent() {

    }
}