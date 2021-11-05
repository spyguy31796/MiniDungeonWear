package com.spylabs.MiniDungeonWear.Models;

import static com.spylabs.MiniDungeonWear.Models.Configuration.XP_FOR_NEXT_LEVEL;

public class Character {
    private int level;
    private int xp;
    private int gold;
    private int escapes;
    private int xpForNextLevel;
    private Stats stats;
    private int statPointsToSpend;

    public Character() {
        this.xp = 0;
        this.level = 1;
        this.gold = 0;
        this.escapes = 0;
        this.xpForNextLevel = computeXPForNextLevel(1);
        this.stats = new Stats();
        this.stats.maxHealth = computePlayerHealth(1);
        this.stats.currentHealth = this.stats.maxHealth;
        this.stats.strength = 1;
        this.stats.magic = 1;
        this.stats.defense = 1;
        this.stats.magicDefense = 1;
        this.statPointsToSpend = 0;
    }

    public class Stats {
        private int maxHealth;
        private int currentHealth;
        private int strength;
        private int magic;
        private int defense;
        private int magicDefense;

        public int getMaxHealth() {
            return maxHealth;
        }

        public void setMaxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
        }

        public int getCurrentHealth() {
            return currentHealth;
        }

        public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
        }

        public int getStrength() {
            return strength;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }

        public int getMagic() {
            return magic;
        }

        public void setMagic(int magic) {
            this.magic = magic;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
        }

        public int getMagicDefense() {
            return magicDefense;
        }

        public void setMagicDefense(int magicDefense) {
            this.magicDefense = magicDefense;
        }

    }

    int computePlayerHealth(int level)
    {
        return 10 + ((level-1)*(level)/2) + ((level-1)*(level)*(level+1)/(6*32));
    }

    int computeXPForNextLevel(int level)
    {
        return XP_FOR_NEXT_LEVEL(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getEscapes() {
        return escapes;
    }

    public void setEscapes(int escapes) {
        this.escapes = escapes;
    }

    public int getXpForNextLevel() {
        return xpForNextLevel;
    }

    public void setXpForNextLevel(int xpForNextLevel) {
        this.xpForNextLevel = xpForNextLevel;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getStatPointsToSpend() {
        return statPointsToSpend;
    }

    public void setStatPointsToSpend(int statPointsToSpend) {
        this.statPointsToSpend = statPointsToSpend;
    }
}
