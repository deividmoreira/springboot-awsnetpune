package br.com.itau.neptunepoc.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.itau.neptunepoc.exceptions.Labels.CODE;
import static br.com.itau.neptunepoc.exceptions.Labels.MESSAGE;

//@Component
public class ErrorResponseGenerator {

    private final MessageSource messageSource;

    public ErrorResponseGenerator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ErrorResponse buildWithBusinessException(BusinessException exception) {
        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                this.getFormattedMessageWithSuffix(exception.getErrorCode().getKey(), CODE.getSufix()),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                this.getFormattedMessageWithSuffix(exception.getErrorCode().getKey(), MESSAGE.getSufix(), exception.getMessages())
        );
    }

    public ErrorResponse buildWithValidatorException(ValidatorException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getErrors().stream().map(
                        error -> new ApiError(
                                this.getFormattedMessageWithSuffix(error.getMessage(), CODE.getSufix()),
                                this.getFormattedMessageWithSuffix(error.getMessage(), MESSAGE.getSufix())
                        )
                ).collect(Collectors.toList()));
    }

    private String getFormattedMessageWithSuffix(String key, String suffix, String... args) {
        return getMessageWithArguments(String.format("%s.%s", key, suffix), args);
    }

    private String getMessageWithArguments(String messageKey, String... args) {
        var locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageKey, args, locale);
    }
}
