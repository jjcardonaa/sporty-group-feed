package com.sportygroup.feed.dto;

import com.sportygroup.feed.enums.MarketOutcomeEnum;
import com.sportygroup.feed.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Internal DTO to handle feed messages.
 */
@Getter
@Setter
@ToString
public class FeedInternalDto {
    private String source;
    private MessageTypeEnum messageType;
    private String eventId;
    private Map<MarketOutcomeEnum, Double> values;
    private MarketOutcomeEnum outcome;
}
