package com.sportygroup.feed.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportygroup.feed.enums.MarketOutcomeEnum;
import com.sportygroup.feed.enums.MessageTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Dto to handle feed messages requests,
 */
@Getter
@Setter
@Builder
@ToString
public class FeedDto {
    @JsonAlias({ "msg_type", "type" })
    private MessageTypeEnum messageType;
    @JsonProperty("event_id")
    private String eventId;
    @JsonAlias({ "values", "odds" })
    private Map<MarketOutcomeEnum, Double> values;
    @JsonAlias({ "outcome", "result" })
    private MarketOutcomeEnum outcome;
}
