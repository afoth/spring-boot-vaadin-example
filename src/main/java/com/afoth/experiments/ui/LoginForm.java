package com.afoth.experiments.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * Created by des on 08.04.17.
 */
public class LoginForm extends FormLayout {

    TextField email;
    PasswordField password;
    Button button;

    public LoginForm() {
        super();
        email = new TextField("Email");
        password = new PasswordField("Password");
        button = new Button("Sign in", this::login);
        addComponents(email, password, button);
    }

    private void login(Button.ClickEvent event){

    }

}
