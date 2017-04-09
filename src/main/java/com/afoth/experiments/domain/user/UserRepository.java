package com.afoth.experiments.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by des on 08.04.17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
