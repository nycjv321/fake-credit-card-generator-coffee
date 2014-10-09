package com.nycjv321.creditcard;

import com.nycjv321.utilities.NumberUtilities;

import java.util.Random;

import static com.nycjv321.utilities.NumberUtilities.getRandom;

/**
* Created by Javier on 10/8/2014.
*/
public class CreditCardPrefix implements CreditCard {
    private static Random random = new Random();

    @Override
    public long getDiscover() {
        int[] prefixes = {6011, 65, getRandom(random, 622126, 622925), getRandom(random, 644, 649)};
        return prefixes[NumberUtilities.getRandom(random, 0, prefixes.length - 1)];
    }

    @Override
    public long getMasterCard() {
        return getRandom(random, 51, 55);
    }

    @Override
    public long getVisa() {
        return 4;
    }

    @Override
    public long getAmericanExpress() {
        int[] prefixes = {34, 37};
        return prefixes[NumberUtilities.getRandom(random, 0, prefixes.length - 1)];
    }
}
