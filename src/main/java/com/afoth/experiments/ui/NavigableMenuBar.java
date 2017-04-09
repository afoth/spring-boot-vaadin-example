package com.afoth.experiments.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.MenuBar;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * Created by des on 08.04.17.
 */
@Slf4j
public class NavigableMenuBar extends MenuBar implements ViewChangeListener {
    private MenuItem currentMenuItem  = null;
    private MenuItem previousMenuItem = null;

    // MenuItem -> View Name
    HashMap<MenuItem, String> menuItems = new HashMap<MenuItem, String>();
    Navigator navigator = null;

    public NavigableMenuBar(Navigator navigator) {
        this.navigator = navigator;
    }

    MenuBar.Command navigateCommand = new Command() {
        @Override
        public void menuSelected(MenuItem selectedItem) {
            String viewName = menuItems.get(selectedItem);
            navigator.navigateTo(viewName);
        }
    };

    public void addView(String viewName, String menuItemCaption) {
        MenuItem menuItem = addItem(menuItemCaption, null, navigateCommand);
        menuItems.put(menuItem, viewName);
    }

    protected boolean selectView(String viewName) {
        // Check that the menu item exists
        if (!menuItems.containsValue(viewName))
            return false;

        if (previousMenuItem != null)
            previousMenuItem.setStyleName(null);
        if (currentMenuItem == null)
            currentMenuItem = getMenuItemForViewName(viewName);
        currentMenuItem.setStyleName("highlight");
        previousMenuItem = currentMenuItem;

        return true;
    }


    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return selectView(event.getViewName());
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {

    }

    private MenuItem getMenuItemForViewName(String viewName){
        for (MenuItem item : menuItems.keySet()) {
            if(menuItems.get(item).equals(viewName)) return item;
        }
        return null;
    }
}
