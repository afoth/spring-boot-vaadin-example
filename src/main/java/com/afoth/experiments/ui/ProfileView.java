package com.afoth.experiments.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by des on 08.04.17.
 */
public class ProfileView extends VerticalLayout implements View {
    public ProfileView() {
        addComponent(new Label("Profile"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
