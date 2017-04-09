package com.afoth.experiments.ui;

import com.afoth.experiments.domain.user.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by des on 08.04.17.
 */
@SpringView(name="home")
public class HomeView extends VerticalLayout implements View{

    @Autowired
    UserService userService;

    ListSelect<String> emailList = new ListSelect<>("Registered emails");

    @PostConstruct
    public void init() {
        emailList.setRows(10);
        emailList.setWidth("200px");
        updateList();
        addComponent(emailList);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateList();
    }

    private void updateList(){
        emailList.setItems(userService.getRegisteredEmails());
    }
}
