package com.sportygroup.feed.exceptions;

import com.sportygroup.feed.dto.response.SportyGroupFeedResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Encapsulates the response for exceptions.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
    private List<SportyGroupFeedResponseDto> validationErrors;
}
