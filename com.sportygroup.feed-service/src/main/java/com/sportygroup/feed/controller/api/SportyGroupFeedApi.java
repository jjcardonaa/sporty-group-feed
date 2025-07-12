package com.sportygroup.feed.controller.api;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sportygroup.feed.constants.ControllerConstants.PROVIDER_TYPE_FEED;

/**
 * Defines the SportyGroupFeedApi
 */
public interface SportyGroupFeedApi {

    /**
     * Represents an update to the odds for a specific event
     * or final results of markets for the event
     *
     * @param feedDto {@link FeedDto} the payload
     */
    @PostMapping(PROVIDER_TYPE_FEED)
    @ResponseStatus(HttpStatus.OK)
    void updateOdds(@PathVariable("provider-type") ProviderTypeEnum providerType,
                    @RequestBody FeedDto feedDto);
}
