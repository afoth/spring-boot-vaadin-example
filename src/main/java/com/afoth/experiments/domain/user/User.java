package com.afoth.experiments.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Created by des on 08.04.17.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column(nullable = false)
    @Email
    String email;

    @Column(nullable = false)
    String encryptedPassword;

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.encryptedPassword = user.encryptedPassword;
    }
}
