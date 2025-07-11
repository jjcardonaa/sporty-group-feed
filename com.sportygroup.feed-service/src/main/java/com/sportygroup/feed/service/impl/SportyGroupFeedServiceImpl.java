package com.sportygroup.feed.service.impl;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.dto.FeedInternalDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import com.sportygroup.feed.mapper.SportyGroupFeedMapper;
import com.sportygroup.feed.service.MessageQueueService;
import com.sportygroup.feed.service.SportyGroupFeedService;
import com.sportygroup.feed.util.JsonConverterUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Implementation of the SportyGroupFeedService interface.
 * This service processes feed data and sends it to a message queue.
 */
@Service
@AllArgsConstructor
@Log4j2
public class SportyGroupFeedServiceImpl implements SportyGroupFeedService {

    private final MessageQueueService messageQueueService;

    /**
     * <{@inheritDoc}/>
     */
    @Override
    public void processFeed(ProviderTypeEnum providerTypeEnum, FeedDto feedDto) {
        log.info(feedDto);
        FeedInternalDto feedInternalDto = SportyGroupFeedMapper.INSTANCE.fromDtoToInternal(providerTypeEnum, feedDto);
        messageQueueService.sendMessage(JsonConverterUtil.convertDtoToJsonString(feedInternalDto));
    }
}
