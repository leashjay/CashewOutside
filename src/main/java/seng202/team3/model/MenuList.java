package seng202.team3.model;

import seng202.team3.util.MenuType;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

public class MenuList {

    /** Main ArrayList handling all Menus */
    private ArrayList<Menu> menuArrayList;

    /** ArrayLists handling different types of Menus */
    private ArrayList<Menu> activeMenus;
    private ArrayList<Menu> winterMenus;
    private ArrayList<Menu> summerMenus;
    private ArrayList<Menu> festivalMenus;

    @XmlAttribute(name = "type")
    private MenuType type;

    /**
     * Constructor for MenuList class
     *
     *
     * @param menuArrayList List holding all menus
     *                      NOTE: will look at changing implementation to a hashmap with MenuType as the key and
     *                      an ArrayList of all corresponding Menus
     */
    public MenuList(ArrayList<Menu> menuArrayList) {
        this.menuArrayList = menuArrayList;
    }

    /**
     * adds a menu to the MenuList
     * @void
     */
    public void addMenu(Menu menu) {
        if (!menuArrayList.contains(menu)) {
            menuArrayList.add(menu);
        }
    }

    /**
     * removes Menu from MenuList
     * @void
     */
    public void removeMenu(Menu menu) {
        menuArrayList.remove(menu);
    }

    /**
     * gets all Menus in MenuList
     * @return menuArrayList
     */
    public ArrayList<Menu> getMenus() {
        return menuArrayList;
    }

    /**
     * gets all Menus that are active ie all menus that are in stock
     * NOTE: not yet fully implemented
     * @return activeMenus
     */
    public ArrayList<Menu> getActiveMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getActive()) {
                activeMenus.add(current);
            }
        }
        return activeMenus;
    }


    /**
     * gets all Menus designed for catering in Winter
     * @return winterMenus
     */
    public ArrayList<Menu> getWinterMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.WINTER) {
                winterMenus.add(current);
            }
        }
        return winterMenus;
    }

    /**
     * gets all Menus designed for catering in Summer
     * @return summerMenus
     */
    public ArrayList<Menu> getSummerMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.SUMMER) {
                summerMenus.add(current);
            }
        }
        return summerMenus;
    }

    /**
     * gets all Menus designed for catering at large festivals
     * @return getFestivalMenus
     */
    public ArrayList<Menu> getFestivalMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.FESTIVAL) {
                festivalMenus.add(current);
            }
        }
        return festivalMenus;
    }
}
