package com.afoth.experiments.ui;

import com.afoth.experiments.domain.registration.Registration;
import com.afoth.experiments.domain.registration.RegistrationService;
import com.vaadin.data.*;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.v7.MBeanFieldGroup;

import javax.validation.*;
import javax.validation.ValidationException;
import java.util.StringJoiner;

/**
 * Created by des on 08.04.17.
 */
@Slf4j
public class SignupForm extends FormLayout {

    TextField email = new TextField("Email");;
    PasswordField password = new PasswordField("Password");;
    PasswordField passwordConfirmation = new PasswordField("Password confirmation");;
    Button button;

    Registration registration = new Registration();
    BeanValidationBinder<Registration> binder = new BeanValidationBinder<>(Registration.class);

    RegistrationService registrationService;

    @Autowired
    public SignupForm(RegistrationService registrationService) {
        this.registrationService = registrationService;

        button = new Button("Sign up", this::submit);
        addComponents(email, password, passwordConfirmation, button);

        binder.setBean(registration);
        binder.bindInstanceFields(this);
        binder.forField(passwordConfirmation).withValidator(new BeanValidator(Registration.class, "passwordConfirmation"));
    }

    public void submit(Button.ClickEvent event) {
        Registration registration = new Registration();
        if(binder.writeBeanIfValid(registration)){
            try{
                registrationService.registerUser(registration);
                UI.getCurrent().getNavigator().navigateTo("home");
                Notification.show("Sign up successful.", Notification.Type.HUMANIZED_MESSAGE);
            } catch(ValidationException e){
                Notification.show(e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } else {
            Notification.show("Check your input.", Notification.Type.ERROR_MESSAGE);
        }
    }
}
