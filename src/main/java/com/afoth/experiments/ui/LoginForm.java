package com.afoth.experiments.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by des on 08.04.17.
 */
public class LoginForm extends FormLayout {

    Label status;
    TextField email;
    PasswordField password;
    Button button;

    DaoAuthenticationProvider authenticationProvider;

    @Autowired
    public LoginForm(DaoAuthenticationProvider authenticationProvider) {
        super();
        this.authenticationProvider = authenticationProvider;
        status = new Label();
        status.setStyleName(ValoTheme.LABEL_FAILURE);
        status.setVisible(false);
        email = new TextField("Email");
        password = new PasswordField("Password");
        button = new Button("Sign in", this::login);
        addComponents(status, email, password, button);
    }

    private void login(Button.ClickEvent event){
        Authentication auth = new UsernamePasswordAuthenticationToken(email.getValue(), password.getValue());
        try {
            Authentication authenticated = authenticationProvider.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            getUI().getPage().setLocation("/");
        } catch(BadCredentialsException e){
            status.setValue(e.getMessage());
            status.setVisible(true);
        }
    }

}
