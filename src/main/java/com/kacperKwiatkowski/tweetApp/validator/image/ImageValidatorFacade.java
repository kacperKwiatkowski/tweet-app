package com.kacperKwiatkowski.tweetApp.validator.image;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.validator.common.ValidatorUtil;
import com.kacperKwiatkowski.tweetApp.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ImageValidatorFacade {

    private final UserValidator userValidator;
    private final ValidatorUtil validatorUtil;

    public void validateImageDownload(String username) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username)
                )));
    }

    private void verifyExceptionMessages(List<String> existingValidationMessages) {
        if (!existingValidationMessages.isEmpty()) {
            throw new FailedTweetValidationException(existingValidationMessages);
        }
    }
}
