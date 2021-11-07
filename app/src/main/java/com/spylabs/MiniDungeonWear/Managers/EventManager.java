package com.spylabs.MiniDungeonWear.Managers;

import static com.spylabs.MiniDungeonWear.Models.Configuration.RAND_MAX;

import com.spylabs.MiniDungeonWear.Models.Event;

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

    public Event getRandomEvent(int ticksSinceLastEvent, boolean fastMode) {
        int randomEventIndex = computeRandomEvent(getBaseChanceOfEvent(), ticksSinceLastEvent, getEventChances(), getEventCount(), fastMode);
        Event.EventType t = null;
        switch (randomEventIndex) {
            case 0:
                t = Event.EventType.ITEM;
                break;
            case 1:
                t = Event.EventType.BATTLE;
                break;
            case 2:
                t = Event.EventType.NEW_FLOOR;
                break;
            case 3:
                t = Event.EventType.SHOP;
                break;
        }

        return new Event(t);
    }

    // TODO: Verify that this logic is reasonably similar to the original.
    private int Random_inline(int max)
    {
        return (int)(Math.random() * RAND_MAX() % max) + 1;
    }

    private int computeRandomEvent(int baseChanceOfEvent, int ticksSinceLastEvent, int[] events, int eventCount, boolean fastMode)
    {
        int result = Random_inline(100);
        int i = 0;
        int acc = 0;
        int chanceOfEvent = baseChanceOfEvent;
        int event = -1;
        if(ticksSinceLastEvent > 20)
        {
            chanceOfEvent += (ticksSinceLastEvent - 20) * 2;
        }

        if(!fastMode && result > chanceOfEvent)
            return -1;

        result = Random_inline(100);

        do
        {
            acc += events[i];
            if(acc >= result)
            {
                event = i;
                break;
            }
            ++i;
        } while (i < eventCount);
        return event;
    }
}
