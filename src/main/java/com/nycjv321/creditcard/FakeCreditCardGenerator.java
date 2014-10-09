package com.nycjv321.creditcard;

import com.nycjv321.luhn.Luhn;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by Javier on 10/8/2014.
 */
public final class FakeCreditCardGenerator implements CreditCard {
    private CreditCardPrefix creditCardPrefix = new CreditCardPrefix();

    @Override
    public final long getDiscover() {
        final long prefix = creditCardPrefix.getDiscover();
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

    @Override
    public final long getMasterCard() {
        final String number = RandomStringUtils.randomNumeric(13);
        final long prefix = creditCardPrefix.getMasterCard();
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));
    }

    @Override
    public final long getVisa() {
        final String number = RandomStringUtils.randomNumeric(14);
        final long prefix = creditCardPrefix.getVisa();
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));
    }

    @Override
    public final long getAmericanExpress() {
        final long prefix = creditCardPrefix.getAmericanExpress();
        final String number = RandomStringUtils.randomNumeric(12);
        final long checksum = Luhn.getCheckDigit(prefix + number);
        return Long.parseLong(prefix + number + String.valueOf(checksum));

    }

}
