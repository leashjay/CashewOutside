package seng202.team3.parsing;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to convert LocalTime to time string and vice versa when unmarshal/marshal
 */
public class TimeAdapter extends XmlAdapter<String, LocalTime> {

    /**
     * Converting time string to LocalTime object
     *
     * @return LocalTime object
     */
    public LocalTime unmarshal(String time) {
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
        return localTime;
    }

    /**
     * Converting LocalTime object to time string
     *
     * @param localTime object
     * @return time string
     */
    public String marshal(LocalTime localTime) {
        String timeString = localTime.toString();
        return timeString;
    }
}
