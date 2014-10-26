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
        String discoverCardRegex = CreditCard.DISCOVER.getRegex();
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
        String masterCardRegex = CreditCard.MASTER_CARD.getRegex();
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
        String visaCardRegex = CreditCard.VISA.getRegex();
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
        String americanExpressRegex = CreditCard.AMERICAN_EXPRESS.getRegex();
        assertTrue(
                americanExpressCard.matches(americanExpressRegex),
                String.format("Expected %s to match %s regex",
                        americanExpressCard,
                        americanExpressRegex
                )
        );
    }
}
