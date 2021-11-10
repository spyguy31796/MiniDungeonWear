package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Managers.CharacterManager.showProgressMenuCallable;
import static com.spylabs.MiniDungeonWear.Managers.CharacterManager.showStatMenuCallable;
import static com.spylabs.MiniDungeonWear.Managers.MenuManager.goBackCallable;
import static com.spylabs.MiniDungeonWear.Managers.OptionsManager.showOptionsMenuCallable;
import static com.spylabs.MiniDungeonWear.Models.Configuration.EVENT_CHANCE_SCALING;

import com.spylabs.MiniDungeonWear.Models.Event;
import com.spylabs.MiniDungeonWear.Models.Location;
import com.spylabs.MiniDungeonWear.Models.Menu;

import java.util.concurrent.Callable;

public class AdventureManager {
    EventManager eventManager;
    Event currentEvent;
    LocationManager locationManager;
    Location currentLocation;
    OptionsManager optionsManager;

    CharacterManager characterManager;



    int ticksSinceLastEvent = 0;

    int updateDelay = 0;

    // TODO: Load state
    public AdventureManager(OptionsManager options) {
        this.eventManager = new EventManager();
        this.locationManager = new LocationManager();
        // TODO: I don't know if I need to pass this in here... I might consider separating the options out of the game
        //  if I add a permanent front end wrapper
        this.optionsManager = options;
        this.characterManager = new CharacterManager();

        try {
            showAdventureMenuCallable().call();
        } catch (Exception ex) {
            // TODO: Add proper logging.
            System.out.println("Error creating menu");
        }
    }

    // TODO: Consider moving all menus to the MenuManager
    private static Menu getAdventureMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Main", "Open the main menu", showMainMenuCallable()));
        return builder.build();
    }

    // TODO: Belongs somewhere else?
    private static Menu getMainMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Quit", "Return to adventure", goBackCallable()))
                .add(new Menu.MenuEntry("Items", "Items Owned", null))
                .add(new Menu.MenuEntry("Progress", "Character advancement", showProgressMenuCallable()))
                .add(new Menu.MenuEntry("Stats", "Character Stats", showStatMenuCallable()))
                .add(new Menu.MenuEntry("Options", "Open the options menu", showOptionsMenuCallable()));
        return builder.build();
    }

    public static Callable<Void> showAdventureMenuCallable() {
        return () -> {
            MenuManager.setCurrentMenu(getAdventureMenuDef());
            return null;
        };
    }

    // TODO: Belongs somewhere else?
    public static Callable<Void> showMainMenuCallable() {
        return () -> {
            MenuManager.setCurrentMenu(getMainMenuDef());
            return null;
        };
    }

    public void UpdateAdventure() {
        // TODO: Intelligent skipping when app not focused.
        //if(!adventureWindowVisible)
        //    return;

        // TODO: Forced battles? Not sure what that is?
        //if(IsBattleForced())
        //{
        //    INFO_LOG("Triggering forced battle.");
        //    ShowBattleWindow();
        //    return;
        //}

        if (EVENT_CHANCE_SCALING()) {
            ++ticksSinceLastEvent;
        }

        // TODO: Determine the need for this "updateDelay" flag
        if(updateDelay > 0 && !optionsManager.GetFastMode())
        {
            --updateDelay;
            return;
        }

        // TODO: Consider cleaning this up since the compute random event function now lives in EventManager
        ExecuteEvent(eventManager.getRandomEvent(ticksSinceLastEvent, optionsManager.GetFastMode()));
        currentLocation = locationManager.getNewRandomLocation();
    }

    private void ExecuteEvent(Event e)
    {
        if (e.getType() == null) {
            currentEvent = null;
            return;
        }

        // TODO: Support vibration.
        //if(GetVibration())
        //    vibes_short_pulse();

        if (EVENT_CHANCE_SCALING()) {
            ticksSinceLastEvent = 0;
        }

        currentEvent = e;
    }

    public void SetUpdateDelay()
    {
        updateDelay = 1;
    }

    public int GetTickCount() {
        return ticksSinceLastEvent;
    }

    public void SetTickCount(int ticks) {
        ticksSinceLastEvent = ticks;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }
}
