/**
 * Title: DateTimeProvider
 * Author: Gabriel Mellides
 * Type: Java Class
 * Description: This class provides date and time from system.
 */
package erpsystem.util.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeProvider {
    /**
     * Name: GetTime
     * Type: Method
     * Description: Returns a String with current time (based on System)
     * @return 
     */
    public String GetTime(){
        DateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return TimeFormat.format(date);
    } // GetTime()
    /**
     * Name: GetTime
     * Type: Method
     * Description: Returns a String with current date (based on System)
     * @return 
     */
    public String GetDate(){
        DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return DateFormat.format(date);
    } // GetDate()
    /**
     * Name: GetTime
     * Type: Method
     * Description: Returns a String with current date & time (based on System time)
     * @return 
     */
    public String GetDateTime(){
        DateFormat DateTimeFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy  HH:mm:ss");
        Date date = new Date();
        return DateTimeFormat.format(date);
    } // GetDateTime()  
    
    public String GetDateTime_file(){
        DateFormat DateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
        Date date = new Date();
        return DateTimeFormat.format(date);
    } // GetDateTime() 
}
