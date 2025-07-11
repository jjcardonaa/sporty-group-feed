package com.sportygroup.feed.mapper;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.dto.FeedInternalDto;
import com.sportygroup.feed.enums.ProviderTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between different FeedDto types.
 */
@Mapper
public interface SportyGroupFeedMapper {

    SportyGroupFeedMapper INSTANCE = Mappers.getMapper(SportyGroupFeedMapper.class);

    /**
     * Method used to map {@link FeedInternalDto} internal object.
     * @param providerTypeEnum {@link ProviderTypeEnum} to be used as the source.
     * @param feedDto {@link FeedDto} object to be converted to an internal DTO.
     *
     * @return {@link FeedInternalDto} object.
     */
    @Mapping(target = "source", expression = "java(providerTypeEnum.name())")
    FeedInternalDto fromDtoToInternal(ProviderTypeEnum providerTypeEnum, FeedDto feedDto);
}
