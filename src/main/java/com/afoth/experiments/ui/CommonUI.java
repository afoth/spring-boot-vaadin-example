package com.afoth.experiments.ui;

import com.afoth.experiments.security.AuthDetails;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by des on 11.04.17.
 */
@Theme("apptheme")
public abstract class CommonUI extends UI {

    @Autowired
    SpringViewProvider springViewProvider;

    Navigator navigator;
    NavigableMenuBar menuBar;
    VerticalLayout layout;
    VerticalLayout content;


    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        content = new VerticalLayout();
        content.setSizeFull();

        navigator = new Navigator(this, content);
        navigator.addProvider(springViewProvider);

        menuBar = new NavigableMenuBar(navigator);
        menuBar.addStyleName("mybarmenu");
        layout.addComponent(menuBar);
        navigator.addViewChangeListener(menuBar);

        menuBar.addView("home", "Home");
        if(isAuthenticated()){
            menuBar.addView("profile", "Profile");
            menuBar.addItem("Log out", this::logout);
        } else {
            menuBar.addView("signup", "Sign up");
            menuBar.addView("login", "Sign in");
        }

        layout.addComponent(content);
        layout.setExpandRatio(content, 1.0f);

        HorizontalLayout footer = new HorizontalLayout();
    }

    public boolean isAuthenticated(){
        return getUserDetails() != null;
    }

    public AuthDetails getUserDetails(){
        AuthDetails authDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(AuthDetails.class.isInstance(principal)){
            authDetails = (AuthDetails)principal;
        }
        return authDetails;
    }

    private void logout(MenuBar.MenuItem menuItem) {
        SecurityContextHolder.getContext().setAuthentication(null);
        getPage().setLocation("/login");
    }

}
