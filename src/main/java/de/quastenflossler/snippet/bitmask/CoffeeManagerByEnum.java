package de.quastenflossler.snippet.bitmask;

import java.math.BigDecimal;
import java.util.EnumSet;

public class CoffeeManagerByEnum {

    private static final BigDecimal BASE_PRICE = new BigDecimal(2.00);
    private static final BigDecimal SPECIAL_PRICE = new BigDecimal(2.80);

    public BigDecimal calculatePrice(final EnumSet<Ingredients> coffeeOrder) {

        EnumSet<Ingredients> everythingActive = EnumSet.allOf(Ingredients.class);

        if (checkFlag(coffeeOrder, everythingActive)) {
            return SPECIAL_PRICE;
        }

        BigDecimal actualPrice = BASE_PRICE;

        if (checkFlag(coffeeOrder, EnumSet.of(Ingredients.CREAM))) {
            actualPrice = actualPrice.add(new BigDecimal(0.50));
        }

        if (checkFlag(coffeeOrder, EnumSet.of(Ingredients.SIRUP))) {
            actualPrice = actualPrice.add(new BigDecimal(0.50));
        }

        return actualPrice;
    }

    private boolean checkFlag(final EnumSet<Ingredients> bitmask, final EnumSet<Ingredients> flagToCheck) {

        return bitmask.containsAll(flagToCheck);
    }
}
