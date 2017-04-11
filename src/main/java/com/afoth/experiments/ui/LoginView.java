package com.afoth.experiments.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import javax.annotation.PostConstruct;

/**
 * Created by des on 08.04.17.
 */
@SpringView(name="login")
public class LoginView extends VerticalLayout implements View {

    @Autowired
    DaoAuthenticationProvider authenticationProvider;

    LoginForm form;

    @PostConstruct
    public void init() {
        form = new LoginForm(authenticationProvider);
        addComponent(new Label("Login"));
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
      form.email.focus();
    }
}
