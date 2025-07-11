package com.sportygroup.feed.controller.advice;

import com.networknt.schema.ValidationMessage;
import com.sportygroup.feed.dto.response.SportyGroupFeedResponseDto;
import com.sportygroup.feed.exceptions.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Handles exceptions thrown by controllers and provides a structured response.
 */
@Log4j2
@RestControllerAdvice
public class AdviceController {

    /**
     * Handles MethodArgumentNotValidException and returns a structured response
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    ResponseEntity<ExceptionResponse> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        StringBuilder errorMessage = new StringBuilder("Error validating: ");
        List<SportyGroupFeedResponseDto> validationErrors = new ArrayList<>();

        Optional.of(e.getBindingResult())
                .map(Errors::getAllErrors)
                .orElse(Collections.emptyList())
                .forEach(error -> {

                    SportyGroupFeedResponseDto.SportyGroupFeedResponseDtoBuilder errorResponseDtoBuilder =
                            SportyGroupFeedResponseDto.builder();
                    SportyGroupFeedResponseDto errorResponse = null;

                    if (error instanceof FieldError fieldError) {
                        String fieldInError = fieldError.getField();

                        errorMessage.append(fieldError.getField()).append(", ");
                        errorResponse = errorResponseDtoBuilder
                                .id(fieldInError)
                                .errorMessage(getErrorMessage(fieldError, request))
                                .rejectedValue(
                                        fieldError.getRejectedValue() != null ? fieldError.getRejectedValue() : "null")
                                .build();
                        try {
                            Integer errorCodeInt = Integer.valueOf(error.getCode());
                            errorResponse.setErrorCode(errorCodeInt);
                        } catch (Exception exception) {
                            log.debug("The error code is not integer.");
                            errorResponse.setField(fieldError.getCode());
                        }

                    } else {
                        String errorMessageFromObjectError = getErrorMessageFromObjectError(request, error);
                        if (isEmpty(errorMessageFromObjectError)) {
                            errorMessage.append(error.getDefaultMessage());
                        } else {
                            errorMessage.append(errorMessageFromObjectError);
                        }
                        if (isNotEmpty(error.getCodes())) {
                            errorResponse = errorResponseDtoBuilder
                                    .id(Arrays.stream(error.getCodes()).findFirst().orElse(""))
                                    .errorMessage(getErrorMessageFromObjectError(request, error))
                                    .rejectedValue(String.valueOf(error.getDefaultMessage()))
                                    .build();

                            try {
                                Integer errorCodeInt = Integer.valueOf(error.getCode());
                                errorResponse.setErrorCode(errorCodeInt);
                            } catch (Exception exception) {
                                log.debug("The error code is not integer.");
                                errorResponse.setErrorCode(0);
                            }
                        }
                    }
                    if (nonNull(errorResponse)) {
                        validationErrors.add(errorResponse);
                    }
                });

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .httpStatus(BAD_REQUEST)
                .message(errorMessage.toString().trim())
                .validationErrors(validationErrors)
                .build();

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), BAD_REQUEST);
    }

    private String getErrorMessageFromObjectError(HttpServletRequest request, ObjectError error) {
        String originalError = Optional.ofNullable(error.getDefaultMessage())
                .map(err -> err.replace("$.", ""))
                .map(err -> err.split(":")[0])
                .orElse("");
        FieldError fieldError = new FieldError(originalError, originalError, originalError);
        return getErrorMessage(fieldError, request);
    }

    private String getErrorMessage(FieldError fieldError, HttpServletRequest request) {
        Optional<String> errorType = Optional.ofNullable(fieldError.getArguments())
                .map(args -> args[0])
                .map(arg -> (ValidationMessage) arg)
                .map(ValidationMessage::getType);

        Optional<String> errorMessage = Optional.empty();
        if (errorType.isPresent()) {
            errorMessage = getErrorMessage("error.".concat(fieldError.getField()).concat(".").concat(errorType.get()));
        }

        if (errorMessage.isEmpty()) {
            errorMessage = getErrorMessage("error.".concat(fieldError.getField()));
        }

        if (errorMessage.isEmpty() && nonNull(fieldError.getCode())) {
            errorMessage = getErrorMessage("error.".concat(fieldError.getCode()));
        }

        return errorMessage.orElse(fieldError.getDefaultMessage());
    }

    private Optional<String> getErrorMessage(String messageName) {
        try {
            return Optional.ofNullable(messageName);
        } catch (NoSuchMessageException nsm) {
            return Optional.empty();
        }
    }
}
