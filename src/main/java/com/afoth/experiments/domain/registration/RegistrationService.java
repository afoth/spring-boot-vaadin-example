package com.afoth.experiments.domain.registration;

import com.afoth.experiments.domain.user.User;
import com.afoth.experiments.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;


/**
 * Created by des on 08.04.17.
 */
@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User registerUser(Registration registration) throws ValidationException {
        if(registration.password.equals(registration.passwordConfirmation)) {
            String encryptedPassword = passwordEncoder.encode(registration.getPassword());
            User user = User.builder().email(registration.getEmail()).encryptedPassword(encryptedPassword).build();
            return userRepository.save(user);
        } else {
            throw new ValidationException("Password and password confirmation don't match");
        }
    }
}
