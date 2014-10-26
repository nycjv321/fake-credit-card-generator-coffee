package com.nycjv321.creditcard;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by Javier on 10/8/2014.
 */
public final class FakeCreditCardGeneratorTests {

    @Test(invocationCount = 100)
    public void testGenerateDiscover() {
        String discoverCard = String.valueOf(FakeCreditCardGenerator.generateDiscover());
        String discoverCardRegex =
                "^6(?:011\\d{12}|5\\d{14}|4[4-9]\\d{13}|22(?:1(?:2[6-9]|[3-9]\\d)|[2-8]\\d{2}|9(?:[01]\\d|2[0-5]))\\d{10})$";
        assertTrue(
                discoverCard.matches(discoverCardRegex),
                String.format("Expected %s to match %s regex",
                        discoverCard,
                        discoverCardRegex
                )
        );
    }

    @Test(invocationCount = 100)
    public void testGenerateMasterCard() {
        String masterCard = String.valueOf(FakeCreditCardGenerator.generateMasterCard());
        String masterCardRegex = "^5[1-5][0-9]{14}$";
        assertTrue(
                masterCard.matches(masterCardRegex),
                String.format("Expected %s to match %s regex",
                        masterCard,
                        masterCardRegex
                )
        );
    }

    @Test(invocationCount = 100)
    public void testGenerateVisa() {
        String visaCard = String.valueOf(FakeCreditCardGenerator.generateVisa());
        String visaCardRegex = "^4[0-9]{12}(?:[0-9]{3})?$";
        assertTrue(
                visaCard.matches(visaCardRegex),
                String.format("Expected %s to match %s regex",
                        visaCard,
                        visaCardRegex
                )
        );
    }

    @Test(invocationCount = 100)
    public void testGenerateAmericanExpress() {
        String americanExpressCard = String.valueOf(FakeCreditCardGenerator.generateAmericanExpress());
        String americanExpressRegex = "^3[47][0-9]{13}$";
        assertTrue(
                americanExpressCard.matches(americanExpressRegex),
                String.format("Expected %s to match %s regex",
                        americanExpressCard,
                        americanExpressRegex
                )
        );
    }
}
