package com.nycjv321.creditcard;

import java.util.stream.Stream;

/**
 * Holds the constants used to represent credit cards.
 * Created by Javier on 10/25/2014.
 */
public enum CreditCard {
    VISA(
            "Visa",
            "^4[0-9]{12}(?:[0-9]{3})?$",
            16
    ),
    DISCOVER(
            "Discover",
            "^6(?:011\\d{12}|5\\d{14}|4[4-9]\\d{13}|22(?:1(?:2[6-9]|[3-9]\\d)|[2-8]\\d{2}|9(?:[01]\\d|2[0-5]))\\d{10})$",
            16
    ),
    AMERICAN_EXPRESS(
            "American Express",
            "^3[47][0-9]{13}$",
            15
    ),
    MASTER_CARD(
            "MasterCard",
            "^5[1-5][0-9]{14}$",
            16
    );
    private String card;
    private String regex;
    private int length;

    private CreditCard(String card, String regex, int length) {
        this.card = card;
        this.regex = regex;
        this.length = length;
    }

    @Override
    public String toString() {
        return card;
    }

    public static CreditCard parse(String cardCard) {
        CreditCard[] creditCards = values();
        for (CreditCard creditCard : creditCards) {
            if (creditCard.toString().equals(cardCard)) {
                return creditCard;
            }
        }
        return null;
    }

    /**
     * Returns a regex used to identify the credit card
     * @return
     */
    public String getRegex() {
        return regex;
    }

    public int getLength() {
        return length;
    }

    public static int getMinimumLength() {
        CreditCard[] creditCards = values();
        return Stream.of(creditCards).map(CreditCard::getLength).min(Integer::compare).get();
    }

    public static int getMaximumLength() {
        CreditCard[] creditCards = values();
        return Stream.of(creditCards).map(CreditCard::getLength).max(Integer::compare).get();
    }

}
