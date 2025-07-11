package com.sportygroup.feed.service;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;

/**
 * SportyGroupFeedService interface defines the contract for processing feed data.
 */
public interface SportyGroupFeedService {

    /**
     * Processes the provided feed data.
     *
     * @param feedDto {@link FeedDto} the feed data to be processed
     */
    void processFeed(ProviderTypeEnum providerTypeEnum, FeedDto feedDto);
}
