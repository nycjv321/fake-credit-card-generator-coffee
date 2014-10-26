package com.nycjv321.creditcard;

import com.nycjv321.luhn.Luhn;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by Javier on 10/8/2014.
 */
public final class FakeCreditCardGenerator {

    public static final long generateDiscover() {
        final long prefix = CreditCardPrefix.getDiscover();
        String number = null;
        final int length = String.valueOf(prefix).length();
        switch (length) {
            case 4:
                number = RandomStringUtils.randomNumeric(11);
                break;
            case 2:
                number = RandomStringUtils.randomNumeric(13);
                break;
            case 3:
                number = RandomStringUtils.randomNumeric(12);
                break;
            case 6:
                number = RandomStringUtils.randomNumeric(9);
                break;
        }
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));

    }

    public static final long generateMasterCard() {
        final String number = RandomStringUtils.randomNumeric(13);
        final long prefix = CreditCardPrefix.getMasterCard();
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));
    }

    public static final long generateVisa() {
        final String number = RandomStringUtils.randomNumeric(14);
        final long prefix = CreditCardPrefix.getVisa();
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));
    }

    public static final long generateAmericanExpress() {
        final long prefix = CreditCardPrefix.getAmericanExpress();
        final String number = RandomStringUtils.randomNumeric(12);
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));

    }

    /**
     * Determines the credit card associated with the provided string.
     * Return null if no credit card matches.
     * @param creditCardAsString a string representing a credit card
     * @return the credit card constant
     */
    public static CreditCard determineCard(String creditCardAsString) {
        CreditCard[] creditCards = CreditCard.values();
        for (int i = 0; i < creditCards.length; ++i) {
            CreditCard creditCard = creditCards[i];
            if (creditCardAsString.matches(creditCard.getRegex())) {
                return creditCard;
            }
        }
        return null;
    }
}
