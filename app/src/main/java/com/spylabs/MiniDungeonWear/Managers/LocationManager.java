package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Models.Configuration.DEFAULT_IMAGE_SUFFIX;

import com.spylabs.MiniDungeonWear.Models.Location;

public class LocationManager {
    //String[] validLocations = {"dungeonleft", "dungeonright", "dungeondeadend", "dungeonstraight", "newfloor"};

    //String[] validEnemies = {"dragon", "goblin", "lich", "rat", "turtle", "wizard", "zombie"};


    public Location getNewRandomLocation() {
        // int selection = (int) Math.floor(Math.random() * validLocations.length);
        // return new Location(getResourceString(validLocations[selection]), null);
        int result = (int) Math.floor(Math.random() * 13);
        if(result < 6) {
            return new Location(getResourceString("dungeonstraight"), null);
        }
        else if(result < 9) {
            return new Location(getResourceString("dungeonleft"), null);
        }
        else if(result < 12) {
            return new Location(getResourceString("dungeonright"), null);
        }
        else {
            return new Location(getResourceString("dungeondeadend"), null);
        }
    }

    private String getResourceString(String name) {
        return "@drawable/" + name + DEFAULT_IMAGE_SUFFIX();
    }

}
