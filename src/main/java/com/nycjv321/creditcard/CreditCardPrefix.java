package com.nycjv321.creditcard;

import com.nycjv321.utilities.NumberUtilities;

import java.util.Random;

import static com.nycjv321.utilities.NumberUtilities.getRandom;

/**
* Created by Javier on 10/8/2014.
*/
public class CreditCardPrefix {
    private static Random random = new Random();

    public static long getDiscover() {
        int[] prefixes = {6011, 65, getRandom(random, 622126, 622925), getRandom(random, 644, 649)};
        return prefixes[NumberUtilities.getRandom(random, 0, prefixes.length - 1)];
    }

    public static long getMasterCard() {
        return getRandom(random, 51, 55);
    }

    public static long getVisa() {
        return 4;
    }

    public static long getAmericanExpress() {
        int[] prefixes = {34, 37};
        return prefixes[NumberUtilities.getRandom(random, 0, prefixes.length - 1)];
    }
}
