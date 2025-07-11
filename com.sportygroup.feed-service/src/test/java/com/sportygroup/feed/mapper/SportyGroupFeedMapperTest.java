package com.sportygroup.feed.mapper;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.dto.FeedInternalDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import com.sportygroup.feed.utils.DtosFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for SportyGroupFeedMapper.
 */
public class SportyGroupFeedMapperTest {

    @Test
    public void testFromDtoToInternal() {
        FeedDto feedDto = DtosFactory.createMockDto(FeedDto.class);
        FeedInternalDto feedInternalDto = SportyGroupFeedMapper.INSTANCE.fromDtoToInternal(
                ProviderTypeEnum.PROVIDER_ALPHA,feedDto);

        assertEquals(ProviderTypeEnum.PROVIDER_ALPHA.name(), feedInternalDto.getSource());
        assertEquals(feedDto.getEventId(), feedInternalDto.getEventId());
        assertEquals(feedDto.getOutcome(), feedInternalDto.getOutcome());
        assertEquals(feedDto.getMessageType(), feedInternalDto.getMessageType());
        assertEquals(feedDto.getValues(), feedInternalDto.getValues());
    }
}
