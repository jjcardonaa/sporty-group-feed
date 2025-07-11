package com.sportygroup.feed.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * MarketOutcomeEnum represents the possible outcomes of a market in a sporting group.
 */
@Getter
@AllArgsConstructor
public enum MarketOutcomeEnum {

    HOME_WIN(List.of("1", "home")),
    DRAW(List.of("X", "draw")),
    AWAY_WIN(List.of("2","away")),
    UNDEFINED(List.of("undefined"));

    private final List<String> messageTypeList;

    @JsonCreator
    public static MarketOutcomeEnum fromStringToMessageTypeEnum(String type) {
        return Arrays.stream(values())
                .filter(typeEnum -> typeEnum.getMessageTypeList().contains(type))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
