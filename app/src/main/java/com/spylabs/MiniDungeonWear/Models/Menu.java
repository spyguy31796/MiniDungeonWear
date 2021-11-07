package com.spylabs.MiniDungeonWear.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Menu {
    private List<MenuEntry> menuItems;

    public List<MenuEntry> getMenuItems() {
        return menuItems;
    }

    public static class MenuBuilder {
        List<MenuEntry> itemsToSet = new ArrayList<>();

        public void add(MenuEntry entry) {
            itemsToSet.add(entry);
        }

        public Menu build() {
            Menu m = new Menu();
            m.menuItems = itemsToSet;
            return m;
        }
    }

    public static class MenuEntry {
        String name;
        String description;
        Callable<Void> callback;

        public MenuEntry(String name, String description, Callable<Void> callback) {
            this.name = name;
            this.description = description;
            this.callback = callback;
        }
    }
}
