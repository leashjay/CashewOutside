package seng202.team3.parsing;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Convert LocalDate object to date string and vice versa when marshal/unmarshal
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Converting date string to LocalDate object
     *
     * @return LocalDate object
     */
    public LocalDate unmarshal(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    /**
     * Converting LocalDate object to date string
     *
     * @param localDate object
     * @return date string
     */
    public String marshal(LocalDate localDate) {
        String dateString = localDate.toString();
        return dateString;
    }
}
