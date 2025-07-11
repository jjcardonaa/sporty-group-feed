package com.sportygroup.feed.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderTypeEnum {

    PROVIDER_BETA("provider-beta"),
    PROVIDER_ALPHA("provider-alpha");

    private final String providerType;

    /**
     * Converts a string to the corresponding ProviderTypeEnum.
     *
     * @param type the string representation of the provider type
     * @return the corresponding ProviderTypeEnum
     * @throws IllegalArgumentException if the string does not match any provider type
     */
    @JsonCreator
    public static ProviderTypeEnum fromString(String type) {
        for (ProviderTypeEnum providerType : values()) {
            if (providerType.getProviderType().equalsIgnoreCase(type)
                    || providerType.name().equalsIgnoreCase(type)) {
                return providerType;
            }
        }
        throw new IllegalArgumentException("Unknown provider type: " + type);
    }

}
