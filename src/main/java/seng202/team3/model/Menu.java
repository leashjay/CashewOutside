package seng202.team3.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Collection of menu item
 */

@XmlRootElement(name = "menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {

    /** Title of the menu */
    @XmlElement(name = "title")
    private String title;

    /** Menu description */
    @XmlElement(name = "description")
    private String desc;

    /** Start month of the menu */
    @XmlAttribute(name = "from")
    private String startMonth;

    /** End month of the menu */
    @XmlAttribute(name = "to")
    private String endMonth;

    /** List of available menu item in the menu */
    @XmlElement(name = "item")
    private List<MenuItem> menuContent;

    /**
     * Temporary Constructor
     */
    public Menu() {
        ;
    }

    /**
     * Constructor for Menu class
     *
     * @param title
     * @param desc
     * @param startMonth
     * @param endMonth
     * @param menuContent
     */
    public Menu(String title, String desc, String startMonth, String endMonth, List<MenuItem> menuContent) {
        this.title = title;
        this.desc = desc;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.menuContent = menuContent;
    }

    /**
     * Getter for list of menu item
     * @return menuContent
     */
    public List<MenuItem> getMenuItem() {
        return menuContent;
    }

    /**
     * Getter for title of menu
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for description of menu
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for start month of menu
     * @return startMonth
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * Getter for end month of menu
     * @return endMonth
     */
    public String getEndMonth() {
        return endMonth;
    }


//    public void addToCollection(MenuItem) {}
//
//    public void removeFromCollection(MenuItem) {}
//
//    public List<MenuItem> filterByDietaryReq() {}


}

