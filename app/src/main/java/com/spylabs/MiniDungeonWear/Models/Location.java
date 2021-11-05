package com.spylabs.MiniDungeonWear.Models;

public class Location {
    private String imageResource;
    private String enemyResource;
    public Location(String imageResource, String enemyResource) {
        this.imageResource = imageResource;
        this.enemyResource = enemyResource;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getEnemyResource() {
        return enemyResource;
    }
}
