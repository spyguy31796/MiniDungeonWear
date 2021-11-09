package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Models.Configuration.EVENT_CHANCE_SCALING;

import com.spylabs.MiniDungeonWear.Models.Character;
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
    MenuManager menuManager;

    Character character;

    int ticksSinceLastEvent = 0;

    int updateDelay = 0;

    // TODO: Load state
    public AdventureManager(OptionsManager options) {
        this.eventManager = new EventManager();
        this.locationManager = new LocationManager();
        this.optionsManager = options;
        this.menuManager = new MenuManager();

        this.character  = new Character();

        try {
            showAdventureMenuCallable().call();
        } catch (Exception ex) {
            // TODO: Add proper logging.
            System.out.println("Error creating menu");
        }
    }

    private Menu getAdventureMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Main", "Open the main menu", showMainMenuCallable()))
                .add(new Menu.MenuEntry("Unselectable", "For Debug", null))
                .add(new Menu.MenuEntry("Debug", "For Debug", showMainMenuCallable()))
                .add(new Menu.MenuEntry("Debug2", "For Debug", showMainMenuCallable()));
        return builder.build();
    }

    // TODO: Belongs somewhere else?
    private Menu getMainMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Back", "Open the main menu", menuManager.goBackCallable()))
                .add(new Menu.MenuEntry("Unselectable", "For Debug", null))
                .add(new Menu.MenuEntry("Diff", "For Debug", null))
                .add(new Menu.MenuEntry("Diff2", "For Debug", menuManager.goBackCallable()));
        return builder.build();
    }

    public Callable<Void> showAdventureMenuCallable() {
        return () -> {
            menuManager.setCurrentMenu(getAdventureMenuDef());
            return null;
        };
    }

    // TODO: Belongs somewhere else?
    public Callable<Void> showMainMenuCallable() {
        return () -> {
            menuManager.setCurrentMenu(getMainMenuDef());
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

    public Character getCharacter() {
        return character;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }
}
