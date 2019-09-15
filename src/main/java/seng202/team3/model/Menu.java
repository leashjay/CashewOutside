package seng202.team3.model;

import seng202.team3.parsing.MenuItemAdapter;
import seng202.team3.util.MenuType;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

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

    /** Type of Menu {SUMMER, WINTER, FESTIVAL} */
    @XmlAttribute(name = "type")
    //@XmlJavaTypeAdapter()
    private MenuType menuType;

    private Boolean isActive;

    /** List of available menu item in the menu */
    @XmlElement(name = "items")
    @XmlJavaTypeAdapter(MenuItemAdapter.class)
    private HashMap<String, MenuItem> menuContent;

    /**
     * No-arg constructor for JAXB
     */
    public Menu() {    }

    /**
     * Constructor for Menu class
     *
     * @param title The title of the menu
     * @param desc The description of the menu
     * @param menuType The type of menu
     * @param menuContent The content of the menu
     */
    public Menu(String title, String desc, MenuType menuType, HashMap<String, MenuItem> menuContent) {
        this.title = title;
        this.desc = desc;
        this.menuType = menuType;
        this.menuContent = menuContent;
    }

    /**
     * getter for isActive attribute
     * @return isActive
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * sets isActive to true
     * void
     */
    public void activate() {
        isActive = true;
    }

    public MenuType getType() {
        return menuType;
    }

    /**
     * sets isActive to true
     * void
     */
    public void deActivate() {
        isActive = false;
    }

    /**
     * flips boolean value for isActive (for gui button beside menu status
     */
    public void flipActive() {
        if (isActive) {
            activate();
        } else {
            deActivate();
        }
    }


    /**
     * Getter for list of menu item
     * @return menuContent
     */
    public HashMap<String, MenuItem> getMenuItem() {
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
     * Getter for the type of Menu
     * @return menuType
     */
    public MenuType getMenuType() { return menuType; }

    //    public void addToCollection(MenuItem) {}
//
//    public void removeFromCollection(MenuItem) {}
//
//    public List<MenuItem> filterByDietaryReq() {}

}

