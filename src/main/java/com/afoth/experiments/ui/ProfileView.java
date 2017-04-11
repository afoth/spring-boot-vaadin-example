package com.afoth.experiments.ui;

import com.afoth.experiments.domain.user.UserService;
import com.afoth.experiments.security.AuthDetails;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;

/**
 * Created by des on 08.04.17.
 */
@SpringView(name="profile")
public class ProfileView extends VerticalLayout implements View {

    @Autowired
    UserService userService;

    ProfileForm form;

    @PostConstruct
    public void init() {
        form = new ProfileForm(userService, getUserDetails().getUser());
        addComponent(new Label("Profile"));
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        form.name.focus();
    }

    public AuthDetails getUserDetails(){
        AuthDetails authDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(AuthDetails.class.isInstance(principal)){
            authDetails = (AuthDetails)principal;
        }
        return authDetails;
    }

}
