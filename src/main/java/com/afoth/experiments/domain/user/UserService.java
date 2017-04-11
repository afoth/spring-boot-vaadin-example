package com.afoth.experiments.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by des on 09.04.17.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<String> getRegisteredEmails(){
        return userRepository.findAll().stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
