package com.sportygroup.feed.controller.api.controllers;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import com.sportygroup.feed.service.SportyGroupFeedService;
import lombok.extern.log4j.Log4j2;

/**
 * Controller for handling feed updates in the SportyGroup application.
 * This controller processes incoming feed data and updates odds accordingly.
 */
@Log4j2
public class SportyGroupFeedController {

    private final SportyGroupFeedService sportyGroupFeedService;

    public SportyGroupFeedController(SportyGroupFeedService sportyGroupFeedService) {
        this.sportyGroupFeedService = sportyGroupFeedService;
    }

    public void updateOdds(ProviderTypeEnum providerType, FeedDto feedDto) {
        log.info("Processing {} {} ", providerType , feedDto);
        sportyGroupFeedService.processFeed(providerType, feedDto);
    }
}
