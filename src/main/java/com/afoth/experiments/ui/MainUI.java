package com.afoth.experiments.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

/**
 * Created by des on 08.04.17.
 */
@SpringUI(path="/")
public class MainUI extends CommonUI {


    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
        navigator.navigateTo("home");
    }

}
