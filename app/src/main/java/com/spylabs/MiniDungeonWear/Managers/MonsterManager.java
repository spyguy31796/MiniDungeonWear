package com.spylabs.MiniDungeonWear.Managers;

import com.spylabs.MiniDungeonWear.Models.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterManager {
    public enum MonsterDef {
        RAT("Rat",
                "rat",
                100,
                100,
                100,
                0,
                0,
                0,
                0,
                true,
                false,
                0),
        GOBLIN("Goblin",
                "goblin",
                100,
                200,
                100,
                1,
                1,
                1,
                0,
                true,
                false,
                1),
        WIZARD("Wizard",
                "wizard",
                100,
                100,
                100,
                1,
                1,
                1,
                3,
                false,
                true,
                2
                ),
        ZOMBIE("Zombie",
                "zombie",
                300,
                100,
                100,
                2,
                1,
                1,
                1,
                true,
                false,
                1
                ),
        TURTLE("Turtle",
                "turtle",
                100,
                100,
                600,
                1,
                2,
                3,
                1,
                true,
                false,
                0),
        LICH("Lich",
                "lich",
                100,
                600,
                100,
                2,
                2,
                3,
                1,
                false,
                true,
                3),
        DRAGON("Dragon",
                "dragon",
                100,
                100,
                100,
                2,
                2,
                2,
                2,
                true,
                true,
                10);

        public Monster monster;

        MonsterDef(String name,
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
            monster = new Monster(name,
                    imageId,
                    extraFireDefenseMultiplier,
                    extraIceDefenseMultiplier,
                    extraLightningDefenseMultiple,
                    powerLevel,
                    healthLevel,
                    defenseLevel,
                    magicDefenseLevel,
                    allowPhysicalAttack,
                    allowMagicAttack,
                    goldScale);
        }
    }

    private static List<Monster> randomMonsterMap = new ArrayList<>();

    private static int[] defenseLevelMap = {0, 5, 10, 20};

    private static int[] powerLevelMap = {10, 7, 4};

    static {
        randomMonsterMap.add(MonsterDef.DRAGON.monster);
        randomMonsterMap.add(MonsterDef.RAT.monster);
        randomMonsterMap.add(MonsterDef.GOBLIN.monster);
        randomMonsterMap.add(MonsterDef.WIZARD.monster);
        randomMonsterMap.add(MonsterDef.ZOMBIE.monster);
        randomMonsterMap.add(MonsterDef.TURTLE.monster);
        randomMonsterMap.add(MonsterDef.LICH.monster);
    }

    int mostRecentMonster = -1;

    public int getMostRecentMonster() {
        return mostRecentMonster;
    }

    public int getMonsterDefense(int defenseLevel) {
        if(defenseLevel < 4)
            return defenseLevelMap[defenseLevel];

        return 0;
    }

    public int scaleMonsterHealth(Monster monster, int baseHealth) {
        switch(monster.getHealthLevel())
        {
            case 0:
                return baseHealth/2;
            case 2:
                return baseHealth*2;
            case 1:
            default:
                return baseHealth;
        }
    }

    public int getMonsterPowerDivisor(int powerLevel) {
        if(powerLevel < 3)
            return powerLevelMap[powerLevel];

        return 10;
    }

    public int getMonsterTypeCount() {
        return randomMonsterMap.size();
    }

    public Monster getRandomMonster(int floor) {
        int limit;
        if(floor >= 20)
        {
            mostRecentMonster = 0;
        }
        else
        {
            limit = floor >= 12 ? 6 : (floor + 1) / 2;

            // TODO: Verify this against the original logic.
            mostRecentMonster = (int) Math.floor(Math.random() * limit);
        }
        return randomMonsterMap.get(mostRecentMonster);
    }

    public Monster getFixedMonster(int index)
    {
        if(index >= 0 && index < getMonsterTypeCount())
        {
            mostRecentMonster = index;
            return randomMonsterMap.get(index);
        }

        //ERROR_LOG("Monster type %d is out of range.", index);
        return null;
    }
}
