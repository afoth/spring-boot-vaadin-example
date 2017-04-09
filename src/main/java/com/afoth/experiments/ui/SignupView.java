package com.afoth.experiments.ui;

import com.afoth.experiments.domain.registration.RegistrationService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by des on 08.04.17.
 */
@Slf4j
@SpringView(name="signup")
public class SignupView extends VerticalLayout implements View {

    @Autowired
    RegistrationService registrationService;

    SignupForm form;

    @PostConstruct
    public void init() {
        form = new SignupForm(registrationService);
        addComponent(new Label("Sign up"));
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        form.email.focus();
    }
}
