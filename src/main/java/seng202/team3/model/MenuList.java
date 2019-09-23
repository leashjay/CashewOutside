package seng202.team3.model;

import java.util.ArrayList;
import seng202.team3.util.MenuType;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;

public class MenuList {

    private ArrayList<Menu> menuArrayList;
    private ArrayList<Menu> activeMenus = new ArrayList<>();
    private ArrayList<Menu> winterMenus = new ArrayList<>();
    private ArrayList<Menu> summerMenus = new ArrayList<>();
    private ArrayList<Menu> festivalMenus = new ArrayList<>();

    public MenuList(ArrayList menuArrayList) {
        this.menuArrayList = menuArrayList;
    }

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
        winterMenus.clear();
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.WINTER) {
                winterMenus.add(current);
            }
        }
        return winterMenus;
    }

    public ArrayList<Menu> getSummerMenus() {
        summerMenus.clear();
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            if (current.getMenuType() == MenuType.SUMMER) {
                summerMenus.add(current);
            }
        }
        return summerMenus;
    }

    public ArrayList<Menu> getFestivalMenus() {
        festivalMenus.clear();
        for (int i = 0; i < menuArrayList.size(); i++) {
            Menu current = menuArrayList.get(i);
            System.out.println(current);
            if (current.getMenuType() == MenuType.FESTIVAL) {
                System.out.println("we in\n");
                festivalMenus.add(current);
            }
        }
        System.out.println("done\n=====================");
        return festivalMenus;
    }
}
