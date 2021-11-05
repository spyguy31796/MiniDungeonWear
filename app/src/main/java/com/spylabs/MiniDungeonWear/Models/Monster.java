package com.spylabs.MiniDungeonWear.Models;

public class Monster {
    private String name;
    // TODO: Lookups are inefficient, make this actually an imageId
    private String imageId;
    private int extraFireDefenseMultiplier;
    private int extraIceDefenseMultiplier;
    private int extraLightningDefenseMultiple;
    private int powerLevel;
    private int healthLevel;
    private int defenseLevel;
    private int magicDefenseLevel;
    private boolean allowPhysicalAttack;
    private boolean allowMagicAttack;
    private int goldScale;

    public Monster(String name,
                   String imageId,
                   int extraFireDefenseMultiplier,
                   int extraIceDefenseMultiplier,
                   int extraLightningDefenseMultiple,
                   int powerLevel,
                   int healthLevel,
                   int defenseLevel,
                   int magicDefenseLevel,
                   boolean allowPhysicalAttack,
                   boolean allowMagicAttack,
                   int goldScale) {
        this.name = name;
        this.imageId = imageId;
        this.extraFireDefenseMultiplier = extraFireDefenseMultiplier;
        this.extraIceDefenseMultiplier = extraIceDefenseMultiplier;
        this.extraLightningDefenseMultiple = extraLightningDefenseMultiple;
        this.powerLevel = powerLevel;
        this.healthLevel = healthLevel;
        this.defenseLevel = defenseLevel;
        this.magicDefenseLevel = magicDefenseLevel;
        this.allowPhysicalAttack = allowPhysicalAttack;
        this.allowMagicAttack = allowMagicAttack;
        this.goldScale = goldScale;
    }

    public String getName() {
        return name;
    }

    public String getImageId() {
        return imageId;
    }

    public int getExtraFireDefenseMultiplier() {
        return extraFireDefenseMultiplier;
    }

    public int getExtraIceDefenseMultiplier() {
        return extraIceDefenseMultiplier;
    }

    public int getExtraLightningDefenseMultiple() {
        return extraLightningDefenseMultiple;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public int getMagicDefenseLevel() {
        return magicDefenseLevel;
    }

    public boolean isAllowPhysicalAttack() {
        return allowPhysicalAttack;
    }

    public boolean isAllowMagicAttack() {
        return allowMagicAttack;
    }

    public int getGoldScale() {
        return goldScale;
    }
}
