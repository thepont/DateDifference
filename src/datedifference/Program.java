package datedifference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This application looks at to dates then outputs the amount of days between them
 * 
 * @author Paul Esson
 */
public class Program {

    private static final String DATE_FORMAT_MESSAGE = "Please enter the date in the format \nYYYY MM DD, YYYY MM DD\n:";
    private static final String FORMAT_ERROR_MESSAGE = "Error in date formatting";
    private static final String OUTPUT_FORMAT = "%04d %02d %02d, %04d %02d %02d %d\n";
    
    private static final String TOKENS = " |, |,";

    public static void main(String[] args) {

        String inputString;
        boolean done = false;
        BufferedReader br = null;
        byte d1, d2, m1, m2;
        short y1, y2;
        long difference;

        try {
            while (!done) {
                System.out.print(DATE_FORMAT_MESSAGE);
                try {
                    br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
                    inputString = br.readLine();
                    Scanner s = new Scanner(inputString).useDelimiter(TOKENS);

                    y1 = s.nextShort();
                    m1 = s.nextByte();
                    d1 = s.nextByte();

                    y2 = s.nextShort();
                    m2 = s.nextByte();
                    d2 = s.nextByte();

                    Date date1 = new Date(d1, m1, y1);
                    Date date2 = new Date(d2, m2, y2);

                    difference = date1.difference(date2);
                    
                    if (date1.isGreaterThen(date2)) {
                        System.out.printf(OUTPUT_FORMAT, y2, m2, d2, y1, m1, d1, difference);
                    } else {
                        System.out.printf(OUTPUT_FORMAT, y1, m1, d1, y2, m2, d2, difference);
                    }
                    done = true;
                } catch (DateOutOfRangeException ex) {
                    System.err.println(ex.getMessage());
                    System.out.println();
                } catch (NoSuchElementException ex) {
                    System.err.println(FORMAT_ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
