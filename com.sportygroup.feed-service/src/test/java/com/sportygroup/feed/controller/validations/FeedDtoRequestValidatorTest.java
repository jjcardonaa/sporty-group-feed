package com.sportygroup.feed.controller.validations;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.MessageTypeEnum;
import com.sportygroup.feed.utils.DtosFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit Test for FeedDtoRequestValidator.
 */
@ExtendWith(MockitoExtension.class)
public class FeedDtoRequestValidatorTest {

    @InjectMocks
    private FeedDtoRequestValidator feedDtoRequestValidator;

    @Mock
    private Errors errors;

    private FeedDto feedDto;

    @BeforeEach
    public void setup() {
        feedDto = DtosFactory.createMockDto(FeedDto.class);
        errors = mock(Errors.class);
    }

    @Test
    @DisplayName("When Feed request is valid.")
    public void validate_customerEmailRequest() {
        errors = mock(Errors.class);
        feedDtoRequestValidator.validate(feedDto, errors);
        verify(errors, never()).rejectValue(anyString(), anyString(), any(),anyString());

    }

    @Test
    @DisplayName("When Feed request is not valid.")
    public void validate_customerEmail_QuoteNotValid() {
        feedDto.setMessageType(MessageTypeEnum.UNDEFINED);
        errors = mock(Errors.class);
        feedDtoRequestValidator.validate(feedDto, errors);
        verify(errors).rejectValue(anyString(), anyString(), any(), anyString());
    }
}
