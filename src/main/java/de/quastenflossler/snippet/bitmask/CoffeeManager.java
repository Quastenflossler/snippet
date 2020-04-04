package de.quastenflossler.snippet.bitmask;

import java.math.BigDecimal;

public class CoffeeManager {

    private static final int FLAG_WITH_MILK = 1;    // Binary 000000001
    private static final int FLAG_WITH_SUGAR = 2;   // Binary 000000010
    private static final int FLAG_WITH_COOKIE = 4;  // Binary 000000100
    private static final int FLAG_WITH_CREAM = 8;   // Binary 000001000
    private static final int FLAG_WITH_SIRUP = 16;  // Binary 000010000

    private static final BigDecimal BASE_PRICE = new BigDecimal(2.00);
    private static final BigDecimal SPECIAL_PRICE = new BigDecimal(2.80);

    public BigDecimal calculatePrice(final int coffeeOrder) {

        int everythingActive = FLAG_WITH_MILK | FLAG_WITH_SUGAR | FLAG_WITH_COOKIE |
                FLAG_WITH_CREAM | FLAG_WITH_SIRUP;

        if (checkFlag(coffeeOrder, everythingActive)) {
            return SPECIAL_PRICE;
        }

        BigDecimal actualPrice = BASE_PRICE;

        if (checkFlag(coffeeOrder, FLAG_WITH_CREAM)) {
            actualPrice = actualPrice.add(new BigDecimal(0.50));
        }

        if (checkFlag(coffeeOrder, FLAG_WITH_SIRUP)) {
            actualPrice = actualPrice.add(new BigDecimal(0.50));
        }

        return actualPrice;
    }

    private boolean checkFlag(final int bitmask, final int flagToCheck) {

        return (bitmask & flagToCheck) == flagToCheck;
    }
}
