package com.spylabs.MiniDungeonWear.Managers;

import com.spylabs.MiniDungeonWear.Models.Menu;

import java.util.Stack;
import java.util.concurrent.Callable;

public class MenuManager {
    private Menu currentMenu;
    private Stack<Menu> menuStack;

    public MenuManager() {
        menuStack = new Stack<>();
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.menuStack.push(this.currentMenu);
        this.currentMenu = currentMenu;
    }

    public void popMenu() {
        currentMenu = menuStack.pop();
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public Callable<Void> goBackCallable() {
        return () -> {
            this.popMenu();
            return null;
        };
    }
}
