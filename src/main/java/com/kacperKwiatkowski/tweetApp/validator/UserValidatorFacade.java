package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserValidatorFacade {

    private final UserValidator userValidator;
    private final ValidatorUtil validatorUtil;

    public void validateUserRegisterAction(RegisterUserDto userToValidate){
        verifyExceptionMessages(validatorUtil.gatherExceptionMessages(List.of(
                userValidator.checkIfUserExistsByUsername(userToValidate.getUsername()),
                userValidator.checkIfUserExistsByEmail(userToValidate.getEmail()),
                userValidator.checkIfPasswordAndPasswordConfirmAreValid(userToValidate.getPassword(), userToValidate.getPasswordConfirm())
        )));
    }

    private void verifyExceptionMessages(List<String> existingValidationMessages) {
        if (!existingValidationMessages.isEmpty()) {
            throw new FailedUserValidationException(existingValidationMessages);
        }
    }
}
