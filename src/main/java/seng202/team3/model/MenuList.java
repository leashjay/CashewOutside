package seng202.team3.model;

import java.util.ArrayList;

public class MenuList {

    private ArrayList<Menu> menuArrayList;

    public void addMenu(Menu menu) {
        if (!menuArrayList.contains(menu)) {
            menuArrayList.add(menu);
        }
    }

    public void removeMenu(Menu menu) {
        menuArrayList.remove(menu);
    }
}
