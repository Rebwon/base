package ko.maeng.base.java.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StandardIncomeTaxTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                /* 소득액 연도 세금액수 */
                {0, 2008, 0},
                {1000, 2008, 80},
                {1200, 2008, 96},
                {1205, 2008, 96},
                {1206, 2008, 97},
                {4600, 2008, 674},
                {5000, 2008, 778}
        });
    }

    private int income;
    private int taxAmount;
    private int year;

    public StandardIncomeTaxTest(int income, int taxAmount, int year) {
        this.income = income;
        this.taxAmount = taxAmount;
        this.year = year;
    }

    @Test
    public void testGetTaxAmount() throws Exception {
        StandardTax incomeTax = new StandardTax();
        assertEquals(this.taxAmount, incomeTax.getTaxAmount(this.income));
    }

    class StandardTax {
        public int getTaxAmount(int income) {
            if(income < 1200) {
                return (int)(income * 0.08);
            } else if(income < 4600) {
                return (int)(income * 0.17 - 108);
            } else if(income < 8800) {
                return (int)(income * 0.26 - 522);
            }
            return 0;
        }
    }
}
