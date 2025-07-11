package com.sportygroup.feed.service;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import com.sportygroup.feed.service.impl.SportyGroupFeedServiceImpl;
import com.sportygroup.feed.utils.DtosFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

/**
 * Tests for SportyGroupFeedServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SportyGroupFeedServiceImplTest {

    private SportyGroupFeedService sportyGroupFeedService;
    private MessageQueueService messageQueueService;

    @BeforeAll
    protected void initAll() {
        messageQueueService = mock(MessageQueueService.class);
        sportyGroupFeedService = new SportyGroupFeedServiceImpl(messageQueueService);
    }

    @ParameterizedTest(name = "{index} - Testing processFeed scenarios - {0}")
    @MethodSource("loadFeedDataScenarios")
    <E> void processFeed(
            String scenario,
            ProviderTypeEnum providerTypeEnum,
            FeedDto feedData,
            boolean expectException) {
        sportyGroupFeedService.processFeed(providerTypeEnum, feedData);
    }


    public Stream<Arguments> loadFeedDataScenarios() {
        FeedDto feedDto = DtosFactory.createMockDto(FeedDto.class);
        return Stream.of(
                Arguments.of("Valid Feed Data",
                        ProviderTypeEnum.PROVIDER_ALPHA,
                        feedDto,
                        false),
                Arguments.of("2nd Provider Feed Data",
                        ProviderTypeEnum.PROVIDER_BETA,
                        feedDto,
                        true)
        );
    }
}
