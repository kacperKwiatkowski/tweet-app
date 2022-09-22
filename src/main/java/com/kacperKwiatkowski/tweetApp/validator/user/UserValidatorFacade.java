package com.kacperKwiatkowski.tweetApp.validator.user;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import com.kacperKwiatkowski.tweetApp.validator.common.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserValidatorFacade {

    private final UserValidator userValidator;
    private final ValidatorUtil validatorUtil;

    public void validateUserRegisterAction(RegisterUserDto userToValidate) {
        verifyExceptionMessages(validatorUtil.gatherExceptionMessages(List.of(
                userValidator.checkIfUserExistsByUsername(userToValidate.getUsername()),
                userValidator.checkIfUserExistsByEmail(userToValidate.getEmail()),
                userValidator.checkIfPasswordAndPasswordConfirmAreValid(userToValidate.getPassword(), userToValidate.getPasswordConfirm())
        )));
    }

    public void validateUserForgotPassword(String username) {
        verifyExceptionMessages(validatorUtil.gatherExceptionMessages(List.of(
                userValidator.checkIfUserDoesntExistsByUsername(username))
        ));
    }

    private void verifyExceptionMessages(List<String> existingValidationMessages) {
        if (!existingValidationMessages.isEmpty()) {
            throw new FailedUserValidationException(existingValidationMessages);
        }
    }
}
