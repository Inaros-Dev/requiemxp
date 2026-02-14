package com.inarosdev.level;


public final class XPTable {

    private static final long[] LEVEL_THRESHOLDS = {
            0,
            100,
            250,
            500,
            900,
            1500,
            2500,
            4000,
            6000,
            9000
    };

    public static final int MAX_LEVEL = LEVEL_THRESHOLDS.length;
    public static final int START_LEVEL = 1;

    private XPTable() {}

    /**
     * Finds the highest level whose XP threshold the player has reached.
     *
     * Iterates from max level down to 1. First threshold that's <= totalXP wins.
     * Example: totalXP=150 → checks Level 10 (9000? no), Level 9 (6000? no), ...
     * Level 2 (100? yes) → returns 2.
     *
     * Simple loop is fine here - with 10 levels, binary search would be overkill.
     */
    public static int getLevelForXP(long totalXP) {
        if (totalXP < 0) return START_LEVEL;

        for (int level = MAX_LEVEL; level >= START_LEVEL; level--) {
            if (totalXP >= LEVEL_THRESHOLDS[level - 1]) {
                return level;
            }
        }
        return START_LEVEL;
    }

    public static long getXPForLevel(int level) {
        if (level < START_LEVEL) return 0L;
        if (level > MAX_LEVEL) return LEVEL_THRESHOLDS[MAX_LEVEL - 1];
        return LEVEL_THRESHOLDS[level - 1];
    }

    public static long getXPInCurrentLevel(long totalXP) {
        var level = getLevelForXP(totalXP);
        return totalXP - getXPForLevel(level);
    }

    public static long getXPToNextLevel(long totalXP) {
        var level = getLevelForXP(totalXP);
        if (level >= MAX_LEVEL) return 0L;
        return LEVEL_THRESHOLDS[level] - totalXP;
    }

    public static float getProgressToNextLevel(long totalXP) {
        var level = getLevelForXP(totalXP);
        if (level >= MAX_LEVEL) return 1.0f;

        var currentThreshold = LEVEL_THRESHOLDS[level - 1];
        var nextThreshold = LEVEL_THRESHOLDS[level];
        var xpInLevel = totalXP - currentThreshold;
        var xpNeeded = nextThreshold - currentThreshold;

        return xpNeeded == 0 ? 1.0f : (float) xpInLevel / xpNeeded;
    }
}