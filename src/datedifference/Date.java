package datedifference;

/**
 *
 * This class represents a Date in the Gregorian calender system.
 *
 * @author Paul Esson
 */
public class Date {

    private static final byte[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final short[] ACCUMULATED_DAYS_IN_MONTH = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private static final short FIRST_VALID_YEAR = 1;
    private static final byte MONTHS_IN_YEAR = 12;

    private static final byte FIRST_MONTH_IN_YEAR = 1;
    private static final byte FEB = 2;
    private static final byte LEAP_YEAR_FEB = 29;

    //Leap year occors every 4 years, unless its the 100th year then only every 400 years.
    private static final short LEAP_YEAR_OCCORANCE = 4;
    private static final short GREGORIAN_NON_LEAP_YEAR = 100;
    private static final short GREGORIAN_LEAP_YEAR = 400;

    private static final short DAY_OFFSET = 1;
    private static final long UNKNOWN_DAYS = Long.MIN_VALUE;

    private final static String MONTH_INVALID_STRING = "Invalid month %d, only " + MONTHS_IN_YEAR + " months";
    private final static String DAY_INVALID_STRING = "Invalid day %d, only %d days in month %d";
    private final static String YEAR_INVALID_STRING = "Invalid year %d, year must be greater then " + FIRST_VALID_YEAR;

    byte day;
    byte month;
    short year;
    long daysSince1Ad = UNKNOWN_DAYS;

    Date(byte day, byte month, short year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * 
     * @return the day of the month
     */
    public byte getDay() {
        return day;
    }
    /**
     * 
     * @return the current month 
     */
    public byte getMonth() {
        return month;
    }

    /**
     * 
     * @return the current year 
     */
    public short getYear() {
        return year;
    }

    /**
     * Calculates the difference between two dates
     *
     * @param otherDate date to calculate the difference between
     * @return amount of days between the two days
     * @throws DateOutOfRangeException if either of the two dates are not valid
     */
    public long difference(Date otherDate) throws DateOutOfRangeException {
        return Math.abs(this.daysSince1AD() - otherDate.daysSince1AD());
    }

    /**
     * Calculates the amount of leap days that have occurred between now and
     * 1AD.
     *
     * @return amount of Leap Days.
     */
    public int calculateLeapDays() {
        int testyear;
        if (this.month > FEB) {
            testyear = year;
        } else {
            testyear = year - 1;
        }
        return (int) (Math.floor(testyear / LEAP_YEAR_OCCORANCE) - Math.floor(testyear / GREGORIAN_NON_LEAP_YEAR) + Math.floor(testyear / GREGORIAN_LEAP_YEAR));
    }

    /**
     * Computes the amount of days since the 1/1/1 in the Gregorian calender.
     * 1/1/1 in the Gregorian calender equates to the 3/1/1 in the Julian
     * calender
     *
     * @return The amount of days since the 1/1/1
     * @throws datedifference.DateOutOfRangeException
     */
    public long daysSince1AD() throws DateOutOfRangeException {
        if (daysSince1Ad == UNKNOWN_DAYS) {
            validate();
            long daysInNormalYears;
            daysInNormalYears = (this.year - 1) * 365;
            daysSince1Ad = this.day + ACCUMULATED_DAYS_IN_MONTH[this.month - 1] + daysInNormalYears + calculateLeapDays() - DAY_OFFSET;
        }
        return daysSince1Ad;
    }

    /**
     * Returns true if date is a valid Gregorian Date
     *
     * @return true if the date is a valid Gregorian date
     * @throws DateOutOfRangeException if the date is out of range.
     */
    public boolean validate() throws DateOutOfRangeException {
        if (year < FIRST_VALID_YEAR) {
            String exceptionmessage = String.format(YEAR_INVALID_STRING, year);
            throw new DateOutOfRangeException(exceptionmessage);
        }
        if (month > MONTHS_IN_YEAR || month < FIRST_MONTH_IN_YEAR) {
            String exceptionmessage = String.format(MONTH_INVALID_STRING, month);
            throw new DateOutOfRangeException(exceptionmessage);
        }
        if (day > getDaysInMonth() || day < DAY_OFFSET) {
            String exceptionmessage = String.format(DAY_INVALID_STRING, day, getDaysInMonth(), month);
            throw new DateOutOfRangeException(exceptionmessage);
        }
        return true;
    }

    /**
     * Returns the amount of days in the month
     *
     * @param month numerical month to lookup in calender
     * @return the amount of days in the requested month.
     * @throws DateOutOfRangeException if an invalid month is given.
     */
    private byte getDaysInMonth() throws DateOutOfRangeException {
        if (month > MONTHS_IN_YEAR || month < FIRST_MONTH_IN_YEAR) {
            String exceptionmessage = String.format(MONTH_INVALID_STRING, month);
            throw new DateOutOfRangeException(exceptionmessage);
        }
        if (month == FEB && isLeapYear()) {
            return LEAP_YEAR_FEB;
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    /**
     * Returns if year is a leap year
     *
     * @return true if the year is a leap year
     */
    public boolean isLeapYear() {
        //Leap year occors every 4 years, unless its the 100th year then only every 400 years.
        if (year % LEAP_YEAR_OCCORANCE == 0) {
            return year % GREGORIAN_NON_LEAP_YEAR != 0 || year % GREGORIAN_LEAP_YEAR == 0;
        } else {
            return false;
        }
    }

    /**
     * Compares two dates.
     *
     * @param compare date to compare against
     * @return true if greater then the compared date
     * @throws datedifference.DateOutOfRangeException on invalid date
     */
    public boolean isGreaterThen(Date compare) throws DateOutOfRangeException {
        return daysSince1AD() > compare.daysSince1AD();
    }

}
