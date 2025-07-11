package com.sportygroup.feed.utils;

import com.sportygroup.feed.dto.FeedDto;
import com.sportygroup.feed.enums.MarketOutcomeEnum;
import com.sportygroup.feed.enums.MessageTypeEnum;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Log4j2
public class DtosFactory {

    /**
     * Dynamically generates the mock dto based on the POJO class name.
     *
     * @param valueType java Pojo
     * @param <T>       pojo type
     * @return pojo mock dto
     */
    public static <T> T createMockDto(Class<T> valueType) {
        String methodName = "createMock" + valueType.getSimpleName();
        Method staticMethod = null;
        T result = null;

        try {
            staticMethod = DtosFactory.class.getDeclaredMethod(methodName);
            result = (T) staticMethod.invoke(null);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            log.error(e.getCause());
        }

        return result;
    }

    public static FeedDto createMockFeedDto() {
        return FeedDto.builder()
                .eventId("ev123")
                .messageType(MessageTypeEnum.ODDS_UPDATE)
                .values(Map.of(
                        MarketOutcomeEnum.HOME_WIN, 1.5,
                        MarketOutcomeEnum.DRAW, 2.0,
                        MarketOutcomeEnum.AWAY_WIN, 3.0
                ))
                .build();
    }
}
