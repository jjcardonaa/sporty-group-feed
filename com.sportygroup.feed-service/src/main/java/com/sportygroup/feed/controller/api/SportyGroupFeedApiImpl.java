package com.sportygroup.feed.controller.api;

import com.sportygroup.feed.controller.api.controllers.SportyGroupFeedController;
import com.sportygroup.feed.controller.validations.FeedDtoRequestValidator;
import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import com.sportygroup.feed.service.SportyGroupFeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sportygroup.feed.constants.ControllerConstants.API_INTERNAL_VERSION;
import static com.sportygroup.feed.constants.OpenApiTagsConstants.TAG_FEED;

/**
 * Implementation of the SportyGroupFeedApi endpoints.
 * This class handles the API requests related to the Sporty Group Feed operations.
 */
@RestController
@RequestMapping(API_INTERNAL_VERSION)
@Tag(name = TAG_FEED, description = "Endpoints for Sporty Group Feed operations")
@Log4j2
public class SportyGroupFeedApiImpl extends SportyGroupFeedController implements SportyGroupFeedApi{

    private final FeedDtoRequestValidator feedDtoRequestValidator;

    public SportyGroupFeedApiImpl(FeedDtoRequestValidator feedDtoRequestValidator,
                                  SportyGroupFeedService sportyGroupFeedService){
        super(sportyGroupFeedService);
        this.feedDtoRequestValidator = feedDtoRequestValidator;
    }

    /**
     * Initializes the binder for FeedDto validation.
     */
    @InitBinder
    public void initFeedDtoBinder(WebDataBinder binder) {
        binder.setValidator(feedDtoRequestValidator);
    }

    /**
     * Update odds for a specific event or final results of markets for the event.
     */
    @Operation(
            summary = "Update odds for a specific event or final market results",
            description = "Update odds for a specific event or final results of markets for the event",
            tags = {TAG_FEED}
    )
    @Override
    public void updateOdds(ProviderTypeEnum providerType, @Valid FeedDto feedDto) {
        super.updateOdds(providerType, feedDto);
    }
}
