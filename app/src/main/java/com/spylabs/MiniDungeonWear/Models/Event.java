package com.spylabs.MiniDungeonWear.Models;

public class Event {
    public enum EventType {
        ITEM,
        BATTLE,
        NEW_FLOOR,
        SHOP
    }

    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
