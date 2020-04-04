package de.quastenflossler.snippet.bitmask;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CoffeeManagerByEnumTest {

    private final CoffeeManagerByEnum classUnderTest = new CoffeeManagerByEnum();

    private EnumSet<Ingredients> coffeOrder;
    private BigDecimal expectedResult;

    public CoffeeManagerByEnumTest(final String testcase, final EnumSet<Ingredients> coffeOrder, final BigDecimal expectedResult) {
        this.coffeOrder = coffeOrder;
        this.expectedResult = expectedResult;
    }

    @Test
    public void calculatePriceTest() {
        BigDecimal actualResult = classUnderTest.calculatePrice(coffeOrder);
        assertThat(actualResult,  Matchers.comparesEqualTo(expectedResult));
    }

    @Parameterized.Parameters(name = "{index}: testcase={0} order={1} price={2}")
    public static Collection data() {
        return Arrays.asList(new Object[][]{

                // no extra price
                {"coffee with milk only", EnumSet.of(Ingredients.MILK), new BigDecimal(2.00)},
                {"coffee with sucker only", EnumSet.of(Ingredients.SUGAR), new BigDecimal(2.00)},
                {"coffee with cookie only", EnumSet.of(Ingredients.COOKIE), new BigDecimal(2.00)},
                {"coffee with milk, sucker and cookie", EnumSet.of(Ingredients.MILK, Ingredients.SUGAR, Ingredients.COOKIE), new BigDecimal(2.00)},

                // extra price
                {"coffee with cream only", EnumSet.of(Ingredients.CREAM), new BigDecimal(2.50)},
                {"coffee with sirup only", EnumSet.of(Ingredients.SIRUP), new BigDecimal(2.50)},
                {"coffee with cream and sirup", EnumSet.of(Ingredients.CREAM, Ingredients.SIRUP), new BigDecimal(3.00)},

                // special price
                {"coffee with everything", EnumSet.allOf(Ingredients.class), new BigDecimal(2.80)},
        });
    }
}