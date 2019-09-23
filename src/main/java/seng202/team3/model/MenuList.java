package seng202.team3.model;

import java.util.ArrayList;
import seng202.team3.util.MenuType;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;

public class MenuList {

    private ArrayList<Menu> menuArrayList;
    private ArrayList<Menu> activeMenus;
    private ArrayList<Menu> winterMenus;
    private ArrayList<Menu> summerMenus;
    private ArrayList<Menu> festivalMenus;
    @XmlAttribute(name = "type")
    private MenuType type;

    public void addMenu(Menu menu) {
        if (!menuArrayList.contains(menu)) {
            menuArrayList.add(menu);
        }
    }

    public void removeMenu(Menu menu) {
        menuArrayList.remove(menu);
    }

    public ArrayList<Menu> getMenus() {
        return menuArrayList;
    }

    public ArrayList<Menu> getActiveMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getActive()) {
                activeMenus.add(current);
            }
        }
        return activeMenus;
    }


    public ArrayList<Menu> getWinterMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.WINTER) {
                winterMenus.add(current);
            }
        }
        return winterMenus;
    }

    public ArrayList<Menu> getSummerMenus() {
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.SUMMER) {
                summerMenus.add(current);
            }
        }
        return summerMenus;
    }

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
