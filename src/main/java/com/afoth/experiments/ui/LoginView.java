package com.afoth.experiments.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by des on 08.04.17.
 */
@SpringView(name="login")
public class LoginView extends VerticalLayout implements View {

    LoginForm form;

    public LoginView() {
        form = new LoginForm();
        addComponent(new Label("Login"));
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
      form.email.focus();
    }
}
