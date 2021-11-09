package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Managers.MenuManager.goBackCallable;

import com.spylabs.MiniDungeonWear.Models.Menu;
import com.spylabs.MiniDungeonWear.Models.Character;

import java.util.concurrent.Callable;

public class CharacterManager {

    Character player;

    public CharacterManager() {
        player = new Character();
    }

    public Character getCharacter() {
        return player;
    }

    private static Menu getProgressMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Quit", "Return to main menu", goBackCallable()));
        return builder.build();
    }

    private static Menu getStatMenuDef() {
        Menu.MenuBuilder builder = new Menu.MenuBuilder();
        builder.add(new Menu.MenuEntry("Quit", "Return to main menu", goBackCallable()))
                .add(new Menu.MenuEntry("Increase", "Increase strength", null))
                .add(new Menu.MenuEntry("Increase", "Increase defense", null))
                .add(new Menu.MenuEntry("Increase", "Increase magic", null))
                .add(new Menu.MenuEntry("Increase", "Increase magic defense", null));
        return builder.build();
    }

    public static Callable<Void> showProgressMenuCallable() {
        return () -> {
            MenuManager.setCurrentMenu(getProgressMenuDef());
            return null;
        };
    }

    public static Callable<Void> showStatMenuCallable() {
        return () -> {
            MenuManager.setCurrentMenu(getStatMenuDef());
            return null;
        };
    }
}
