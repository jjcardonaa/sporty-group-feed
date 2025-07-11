package com.sportygroup.feed.controller.validations;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.util.JsonConverterUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.sportygroup.feed.constants.ApplicationConstants.FEED_DTO_SCHEMA;

/**
 * Validates {@link FeedDto} instance.
 */
@Component
public class FeedDtoRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FeedDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FeedDto feedDto = (FeedDto) target;
        String schema = JsonConverterUtil.convertDtoToJsonString(feedDto);
        JsonConverterUtil.validateMsg(schema, FEED_DTO_SCHEMA, errors );
    }
}
