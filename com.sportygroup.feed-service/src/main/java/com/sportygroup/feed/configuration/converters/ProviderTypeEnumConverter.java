package com.sportygroup.feed.configuration.converters;

import com.sportygroup.feed.enums.ProviderTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProviderTypeEnumConverter implements Converter<String, ProviderTypeEnum> {

    @Override
    public ProviderTypeEnum convert(String source) {
        return ProviderTypeEnum.fromString(source);
    }
}