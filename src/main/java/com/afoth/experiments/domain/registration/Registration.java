package com.afoth.experiments.domain.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by des on 08.04.17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

    @NotNull
    @NotBlank
    @Email
    String email;

    @NotNull
    @NotBlank
    @Size(min = 6)
    String password;

    @NotNull
    @NotBlank
    String passwordConfirmation;
}
