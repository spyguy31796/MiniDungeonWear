package com.spylabs.MiniDungeonWear.Models;

// TODO: Complete porting relevant configurations from MiniDungeon.h
public class Configuration {
    public static int STAT_POINTS_PER_LEVEL() {
        return 2;
    }
    public static int XP_FOR_NEXT_LEVEL(int level) {
        return 5 * level;
    }

    public static boolean ALLOW_SHOP() {
        return true;
    }

    public static String DEFAULT_IMAGE_SUFFIX() {
        return "_color";
    }
}
