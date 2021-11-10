package com.spylabs.MiniDungeonWear.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Menu {
    private List<MenuEntry> menuItems;
    private int position;
    private String displayText;
    private String displayImage;

    public List<MenuEntry> getMenuItems() {
        return menuItems;
    }

    public boolean hasDisplayableElement() {
        return displayText != null || displayImage != null;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public boolean incrementPosition() {
        if (position + 1 >= menuItems.size()) {
            return false;
        }

        this.position++;

        if (menuItems.get(position).callback == null) {
            if (!incrementPosition()) {
                this.position--;
                return false;
            }
        }

        return true;
    }

    public boolean decrementPosition() {
        if (position - 1 < 0) {
            return false;
        }

        this.position--;

        if (menuItems.get(position).callback == null) {
            if (!decrementPosition()) {
                this.position++;
                return false;
            }
        }

        return true;
    }

    public int getPosition() {
        return position;
    }

    public static class MenuBuilder {
        List<MenuEntry> itemsToSet = new ArrayList<>();
        int position = 0;
        String displayText = null;
        String displayImage = null;

        public MenuBuilder add(MenuEntry entry) {
            itemsToSet.add(entry);
            return this;
        }

        public MenuBuilder startingPosition(int i) {
            position = i;
            return this;
        }

        public MenuBuilder displayText(String s) {
            displayText = s;
            return this;
        }

        public MenuBuilder displayImage(String s) {
            displayImage = s;
            return this;
        }

        public Menu build() {
            Menu m = new Menu();
            m.menuItems = itemsToSet;
            m.position = position;
            m.displayText = displayText;
            m.displayImage = displayImage;
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

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void processCallback() {
            try {
                callback.call();
            } catch (Exception ex) {
                Log.d("MENUCALLBACKERROR","Failure processing menu callback: " + ex.toString());
            }
        }
    }
}
