package seng202.team3.parsing;


import seng202.team3.model.MenuItem;
import seng202.team3.wrapper.MenuItems;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter to map Array in MenuItems class to a HashMap
 */

public class MenuItemAdapter extends XmlAdapter<MenuItems, Map<String, MenuItem>> {

    /**
     * Converting array of menu items into HashMap
     * @param value array of menu items from wrapper class
     * @return HashMap of menu items
     */
    @Override
    public Map<String, MenuItem> unmarshal(MenuItems value) throws Exception {
        Map<String, MenuItem> map = new HashMap<String, MenuItem>();
        for (MenuItem item : value.items)
            map.put(item.getId(), item);
        return map;
    }


    /**
     * Converting HashMap of menu items into array
     * @param map HashMap of menu items
     * @return Array of menu items
     */
    @Override
    public MenuItems marshal(Map<String, MenuItem> map) throws Exception {
        MenuItems itemCont = new MenuItems();
        Collection<MenuItem> items = map.values();
        itemCont.items = items.toArray(new MenuItem[items.size()]);
        return itemCont;
    }
}
