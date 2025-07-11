package com.sportygroup.feed.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * MessageTypeEnum represents the different types of messages that can be processed in the sporty group feed service.
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    ODDS_UPDATE(List.of("odds_update", "ODDS")),
    SETTLEMENT(List.of("settlement", "SETTLEMENT")),
    UNDEFINED(List.of("undefined"));

    private final List<String> messageTypeList;

    @JsonCreator
    public static MessageTypeEnum fromStringToMessageTypeEnum(String type) {
        return Arrays.stream(values())
                .filter(typeEnum -> typeEnum.getMessageTypeList().contains(type))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
