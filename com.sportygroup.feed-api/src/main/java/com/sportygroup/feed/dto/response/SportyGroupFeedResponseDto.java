package com.sportygroup.feed.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SportyGroupFeedResponseDto.
 * This class represents the response DTO for the Sporty Group Feed API.
 * It includes fields for error handling and response details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportyGroupFeedResponseDto {

    private String id;
    private String errorMessage;
    private Object rejectedValue;
    private String field;
    private int errorCode;
}
