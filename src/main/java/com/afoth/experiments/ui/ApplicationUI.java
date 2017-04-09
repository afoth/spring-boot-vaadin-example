package com.afoth.experiments.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by des on 08.04.17.
 */
@SpringUI
@Theme("apptheme")
public class ApplicationUI extends UI {

    @Autowired
    SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();

        Navigator navigator = new Navigator(this, content);
        navigator.addProvider(springViewProvider);
        navigator.navigateTo("home");

        NavigableMenuBar menuBar = new NavigableMenuBar(navigator);
        menuBar.addStyleName("mybarmenu");
        layout.addComponent(menuBar);
        navigator.addViewChangeListener(menuBar);

        menuBar.addView("home", "Home");
        menuBar.addView("signup", "Sign up");
        menuBar.addView("login", "Sign in");

        layout.addComponent(content);
        layout.setExpandRatio(content, 1.0f);

        HorizontalLayout footer = new HorizontalLayout();
    }
}
