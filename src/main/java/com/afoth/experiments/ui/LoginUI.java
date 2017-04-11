package com.afoth.experiments.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

/**
 * Created by des on 11.04.17.
 */
@SpringUI(path = "/login")
public class LoginUI extends CommonUI {

    public void init(VaadinRequest request){
        super.init(request);
        navigator.navigateTo("login");
    }
}
