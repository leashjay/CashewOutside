package seng202.team3.parsing;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Converting date and time string to LocalDate and LocalTime object
     *
     * @return HashMap of menu items
     */
    public LocalDate unmarshal(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    public String marshal(LocalDate localDate) {
        String dateString = localDate.toString();
        return dateString;
    }
}
