package seng202.team3.parsing;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeAdapter extends XmlAdapter<String, LocalTime> {

    /**
     * Converting date and time string to LocalDate and LocalTime object
     *
     * @return HashMap of menu items
     */
    public LocalTime unmarshal(String time) {
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
        return localTime;
    }

    public String marshal(LocalTime localTime) {
        String timeString = localTime.toString();
        return timeString;
    }
}
