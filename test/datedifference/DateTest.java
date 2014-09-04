package datedifference;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test for Date class;
 * 
 * @author Paul Esson
 */
public class DateTest {

    public DateTest() {
    }

    /**
     * Test of difference method, of class Date. This tests a short difference
     * of 2 days.
     */
    @Test
    public void testSimpleDifference() {
        Date d1 = new Date((byte) 12, (byte) 12, (short) 2012);
        Date d2 = new Date((byte) 10, (byte) 12, (short) 2012);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 2;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test the difference method checks method works correctly for one month
     */
    @Test
    public void testFirstMonthDifference() {
        Date d1 = new Date((byte) 1, (byte) 1, (short) 2012);
        Date d2 = new Date((byte) 1, (byte) 2, (short) 2012);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 31;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    /**
     * Test of difference method of class Date. This tests the method works for
     * the range asked for in the question.
     */
    @Test
    public void testRangeDifference() {
        Date d1 = new Date((byte) 31, (byte) 12, (short) 2010);
        Date d2 = new Date((byte) 1, (byte) 1, (short) 1900);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 40541;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    /**
     * Test of difference method, This tests we add the day if we are past
     * February in a leap year.
     */
    @Test
    public void testMiddleLeapDifference() {
        Date d1 = new Date((byte) 1, (byte) 1, (short) 2012);
        Date d2 = new Date((byte) 1, (byte) 3, (short) 2012);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 60;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    @Test
    public void testLeapYearDifference() {
        Date d1 = new Date((byte) 1, (byte) 1, (short) 2012);
        Date d2 = new Date((byte) 1, (byte) 1, (short) 2013);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 366;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    @Test
    public void testTwoYearDifference() {
        Date d1 = new Date((byte) 1, (byte) 1, (short) 2012);
        Date d2 = new Date((byte) 1, (byte) 1, (short) 2010);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 730;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test from the date that the British empire embraced the Gregorian system,
     * into the distant future.
     */
    @Test
    public void testStartOfGregorianYearDifference() {
        Date d1 = new Date((byte) 14, (byte) 9, (short) 1752);
        Date d2 = new Date((byte) 1, (byte) 1, (short) 3999);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 820443;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    @Test
    public void testPreGregorianYearDifference() {
        Date d1 = new Date((byte) 01, (byte) 01, (short) 01);
        Date d2 = new Date((byte) 1, (byte) 1, (short) 3999);
        long result;
        try {
            result = d1.difference(d2);
            long expResult = 1460239;
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

    /**
     * Test from 1 Ad. Note that 1AD is not Julian, Gregorian 1/1/1 is 2 days
     * after
     */
    @Test
    public void testFrom1Ad() {
        Date instance = new Date((byte) 20, (byte) 8, (short) 2014);
        long expResult = 735464;
        long result;
        try {
            result = instance.daysSince1AD();
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
    }

    @Test
    public void testFutureFrom1Ad() {
        Date instance = new Date((byte) 1, (byte) 1, (short) 3999);
        long expResult = 1460239;
        long result;
        try {
            result = instance.daysSince1AD();
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void testPastFrom1Ad() {
        Date instance = new Date((byte) 1, (byte) 1, (short) 1);
        long expResult = 0;
        long result;
        try {
            result = instance.daysSince1AD();
            assertEquals(expResult, result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
    }

    /**
     * Test of isLeapYear method Test the isLeapYear method with a year that is
     * a leap year
     */
    
    @Test
    public void testLeapYearIsLeapYear() {
        short year = 2012;
        Date instance = new Date((byte) 1, (byte) 1, year);

        boolean result = instance.isLeapYear();
        boolean expected = true;
        assertEquals(expected, result);
    }

    /**
     * Test of isLeapYear method Test the isLeapYear method with a year that is
     * not a leap year
     */

    @Test
    public void testNonLeapYearIsLeapYear() {
        short year = 1900;
        Date instance = new Date((byte) 1, (byte) 1, year);
        boolean result = instance.isLeapYear();
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void isValidDateInvalidYear() {
        short year = 0;
        byte month = 1, day = 1;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
            fail();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
        }
        
    }

    @Test
    public void isValidDateInvalidMonthHigh() {
        short year = 1;
        byte month = 13, day = 1;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
            fail();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void isValidDateInvalidMonthLow() {
        short year = 1;
        byte month = 0, day = 1;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
            fail();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void isValidDateInvalidDayHigh() {
        short year = 1;
        byte month = 2, day = 29;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
            fail();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void isValidDateInvalidDayLow() {
        short year = 1;
        byte month = 2, day = 0;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
            fail();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
        }
    }
    
    @Test
    public void isValidDateValidDate(){
        short year = 1;
        byte month = 2, day = 12;
        Date instance = new Date(day, month, year);
        
        try {
            instance.validate();
        } catch (DateOutOfRangeException ex) {
            assertNotNull(ex.getMessage());
            fail();
        }
    }
    
    @Test
    public void isGreaterThen(){
        short year = 1;
        byte month = 1, day = 1, day2 = 2;
        Date instance1 = new Date(day, month, year);
        Date instance2 = new Date(day2, month, year);
        boolean result;
        try {
            result = instance1.isGreaterThen(instance2);
            boolean expectedResult = false;
            assertEquals(expectedResult,result);
        } catch (DateOutOfRangeException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }

}
