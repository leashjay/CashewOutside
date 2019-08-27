package seng202.team3.model;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {


    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String desc;

    @XmlAttribute(name = "from")
    private String startMonth;

    @XmlAttribute(name = "to")
    private String endMonth;

    @XmlElement(name = "item")
    private List<MenuItem> menuContent;


    public List<MenuItem> getMenuItem() {
        return menuContent;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }


//    public void addToCollection(MenuItem) {}
//
//    public void removeFromCollection(MenuItem) {}
//
//    public List<MenuItem> filterByDietaryReq() {}


}

