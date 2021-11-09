package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Managers.MenuManager.goBackCallable;

import com.spylabs.MiniDungeonWear.Models.Menu;

import java.util.concurrent.Callable;

public class OptionsManager {
    public boolean fastMode = false;

    public boolean GetFastMode() {
        return fastMode;
    }

    public void SetFastMode(boolean enable) {
        fastMode = enable;
    }

    private static Menu getOptionsMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Quit", "Return to main menu", goBackCallable()))
                .add(new Menu.MenuEntry("Toggle", "Toggle Vibration", null))
                .add(new Menu.MenuEntry("Toggle", "Speed up events", null))
                .add(new Menu.MenuEntry("Toggle", "Use old graphics", null))
                .add(new Menu.MenuEntry("Toggle", "Run in background", null))
                .add(new Menu.MenuEntry("Toggle", "Launch from background", null));
        return builder.build();
    }

    public static Callable<Void> showOptionsMenuCallable() {
        return () -> {
            MenuManager.setCurrentMenu(getOptionsMenuDef());
            return null;
        };
    }
}
