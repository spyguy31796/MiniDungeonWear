package com.spylabs.MiniDungeonWear.Managers;

public class EventManager {
    // These should add up to 100
    public static int chances[] =
            {
                    44, //EVENT_ITEM
                    44, //EVENT_BATTLE
                    9, //EVENT_NEW_FLOOR
                    3 //EVENT_SHOP
            };

    public static int[] getEventChances()
    {
        return chances;
    }

    public static int getEventCount()
    {
        return chances.length;
    }

    public static int baseChanceOfEvent = 35;

    public static int getBaseChanceOfEvent()
    {
        return baseChanceOfEvent;
    }
}
