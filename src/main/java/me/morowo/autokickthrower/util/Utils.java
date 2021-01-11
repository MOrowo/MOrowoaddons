package me.morowo.autokickthrower.util;

import java.util.regex.Pattern;

public class Utils {
    private static final Pattern pFinderPattern = Pattern.compile("Dungeon Finder > (\\w{3,32}) joined the dungeon group!");
    public String playerName = pFinderPattern.matcher("").group(1);

    public static Pattern getpFinderPattern() {
        return pFinderPattern;
    }

    public String getPlayerName() {
        return playerName;
    }
}
