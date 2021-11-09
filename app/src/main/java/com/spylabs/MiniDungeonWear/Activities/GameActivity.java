package com.spylabs.MiniDungeonWear.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;

import com.spylabs.MiniDungeonWear.Managers.AdventureManager;
import com.spylabs.MiniDungeonWear.Managers.MenuManager;
import com.spylabs.MiniDungeonWear.Managers.OptionsManager;
import com.spylabs.MiniDungeonWear.Models.Character;
import com.spylabs.MiniDungeonWear.Models.Location;
import com.spylabs.MiniDungeonWear.Managers.LocationManager;
import com.spylabs.MiniDungeonWear.Models.Menu;
import com.spylabs.MiniDungeonWear.databinding.ActivityGameBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class GameActivity extends Activity {

    private ActivityGameBinding binding;

    private GestureDetectorCompat detector;

    private TextView clock;

    private TextView currentHP;

    private TextView maxHP;

    private TextView level;

    private ImageView viewPort;

    private ImageView enemyViewPort;

    private TextView description;

    private AdventureManager adventureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adventureManager = new AdventureManager(new OptionsManager());

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        detector = new GestureDetectorCompat(this, new MyGestureListener());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        bindElements();

        updateClock();

        renderCharacter();

        renderMenu();

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
        Character c = adventureManager.getCharacterManager().getCharacter();
        currentHP.setText(String.valueOf(c.getStats().getCurrentHealth()));
        maxHP.setText(String.valueOf(c.getStats().getMaxHealth()));
        level.setText(String.valueOf(c.getLevel()));
    }

    private void renderMenu() {
        Menu currentMenu = MenuManager.getCurrentMenu();
        List<Menu.MenuEntry> menuEntryList = currentMenu.getMenuItems();
        int currentPosition = currentMenu.getPosition();
        for (int i = 0; i < binding.MenuLayout.getChildCount(); i++) {
            if (menuEntryList.size() > i) {
                ((TextView)((TableRow) binding.MenuLayout.getChildAt(i)).getChildAt(0)).setText(menuEntryList.get(i).getName());
            } else {
                ((TextView)((TableRow) binding.MenuLayout.getChildAt(i)).getChildAt(0)).setText("");
            }

            // Highlight selection
            if (i == currentPosition) {
                (((TableRow) binding.MenuLayout.getChildAt(i)).getChildAt(0)).setBackgroundColor(Color.BLACK);
            } else {
                (((TableRow) binding.MenuLayout.getChildAt(i)).getChildAt(0)).setBackgroundColor(Color.TRANSPARENT);
            }
        }
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

    public boolean onSwipeUp() {
        //displayToast("Swiped Up");
        MenuManager.getCurrentMenu().decrementPosition();
        // TODO: Inefficient to just rerender the whole menu just to change selection
        renderMenu();
        return true;
    }

    public boolean onSwipeDown() {
        //displayToast("Swiped Down");
        MenuManager.getCurrentMenu().incrementPosition();
        // TODO: Inefficient to just rerender the whole menu just to change selection
        renderMenu();
        return true;
    }

    public boolean onTap() {
        Menu m = MenuManager.getCurrentMenu();
        m.getMenuItems().get(m.getPosition()).processCallback();
        renderMenu();
        return true;
    }

    private void displayToast(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    // Touch handling, perhaps move this elsewhere?
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Log.d(DEBUG_TAG,"onSingleTapConfirmed: " + event.toString());
            return onTap();
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            boolean result = false;
            try {
                float diffY = event2.getY() - event1.getY();
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        result = onSwipeDown();
                    } else {
                        result = onSwipeUp();
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
}