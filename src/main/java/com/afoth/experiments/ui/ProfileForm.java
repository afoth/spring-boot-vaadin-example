package com.afoth.experiments.ui;

import com.afoth.experiments.domain.user.User;
import com.afoth.experiments.domain.user.UserService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;

/**
 * Created by des on 11.04.17.
 */

public class ProfileForm extends FormLayout{

    private UserService userService;

    TextField name = new TextField("Name");;
    TextField email = new TextField("Email");;
    Button button;

    User user;
    BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

    @Autowired
    public ProfileForm(UserService userService, User user) {
        super();
        this.userService = userService;
        this.user = user;

        button = new Button("Update", this::submit);
        addComponents(name, email, button);

        binder.setBean(user);
        binder.bindInstanceFields(this);
    }

    public void submit(Button.ClickEvent event){
        if(binder.writeBeanIfValid(user)){
            try{
                userService.saveUser(user);
                UI.getCurrent().getNavigator().navigateTo("home");
                Notification.show("Profile updated.", Notification.Type.HUMANIZED_MESSAGE);
            } catch(ValidationException e){
                Notification.show(e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } else {
            Notification.show("Check your input.", Notification.Type.ERROR_MESSAGE);
        }
    }
}
