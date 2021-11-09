package com.spylabs.MiniDungeonWear.Managers;

import com.spylabs.MiniDungeonWear.Models.Menu;

import java.util.Stack;
import java.util.concurrent.Callable;

// Made this manager static to facilitate placing menu logic in the individual managers.
// This class needs to be shared among all the others. Possibly consider redesigning this.
public class MenuManager {
    private static Menu currentMenu;
    private static Stack<Menu> menuStack = new Stack<>();

    public static void setCurrentMenu(Menu currentMenu) {
        menuStack.push(MenuManager.currentMenu);
        MenuManager.currentMenu = currentMenu;
    }

    public static void popMenu() {
        currentMenu = menuStack.pop();
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static Callable<Void> goBackCallable() {
        return () -> {
            MenuManager.popMenu();
            return null;
        };
    }
}
