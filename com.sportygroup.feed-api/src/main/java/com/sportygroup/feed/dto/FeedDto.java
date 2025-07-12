package com.sportygroup.feed.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportygroup.feed.enums.MarketOutcomeEnum;
import com.sportygroup.feed.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "FeedDto", description = "Data Transfer Object for handling feed messages.")
public class FeedDto {
    @Schema(description = "Message Type", allowableValues = { "ODDS", "SETTLEMENT" })
    @JsonAlias({ "msg_type", "type" })
    private MessageTypeEnum messageType;
    @JsonProperty("event_id")
    private String eventId;
    @JsonAlias({ "values", "odds" })
    private Map<MarketOutcomeEnum, Double> values;
    @JsonAlias({ "outcome", "result" })
    @Schema(description = "Outcome", allowableValues = { "home", "draw", "away" })
    private MarketOutcomeEnum outcome;
}
