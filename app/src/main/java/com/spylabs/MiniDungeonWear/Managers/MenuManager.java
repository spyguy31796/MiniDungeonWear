package com.spylabs.MiniDungeonWear.Managers;

import com.spylabs.MiniDungeonWear.Models.Menu;

import java.util.Stack;

public class MenuManager {
    private Menu currentMenu;
    private Stack<Menu> menuStack;


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
}
