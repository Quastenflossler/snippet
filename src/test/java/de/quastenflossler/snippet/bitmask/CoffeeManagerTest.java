package de.quastenflossler.snippet.bitmask;

import de.quastenflossler.snippet.bitmask.CoffeeManager;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CoffeeManagerTest {

    private final CoffeeManager classUnderTest = new CoffeeManager();

    private int coffeOrder;
    private BigDecimal expectedResult;

    public CoffeeManagerTest (final String testcase, final int coffeOrder, final BigDecimal expectedResult) {
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
                {"coffee with milk only", 1, new BigDecimal(2.00)},
                {"coffee with sucker only", 2, new BigDecimal(2.00)},
                {"coffee with cookie only", 4, new BigDecimal(2.00)},
                {"coffee with milk, sucker and cookie", 7, new BigDecimal(2.00)},

                // extra price
                {"coffee with cream only", 8, new BigDecimal(2.50)},
                {"coffee with sirup only", 16, new BigDecimal(2.50)},
                {"coffee with cream and sirup", 24, new BigDecimal(3.00)},

                // special price
                {"coffee with everything", 31, new BigDecimal(2.80)},
        });
    }
}