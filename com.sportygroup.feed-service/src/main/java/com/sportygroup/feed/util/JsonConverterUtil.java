package com.sportygroup.feed.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.sportygroup.feed.exceptions.InvalidJsonException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

/**
 * Utility class for JSON conversion and validation.
 * It provides methods to convert Java POJOs to JSON strings and validate JSON payloads against predefined schemas.
 */
@Log4j2
public class JsonConverterUtil {

    private static final ObjectMapper objectMapper;
    private static final Map<String, JsonSchema> SCHEMAS_MAP = new TreeMap<>();
    private static final JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private static JsonSchema getJsonSchema(String schemaName) {
        Optional<JsonSchema> jsonSchema = Optional.ofNullable(SCHEMAS_MAP.get(schemaName));

        return jsonSchema.orElseGet(() -> {
            JsonSchema schema = schemaFactory.getSchema(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("jsonSchemas/" + schemaName + ".json"));
            SCHEMAS_MAP.put(schemaName, schema);
            return schema;
        });
    }

    /**
     * Converts a java POJO to the String representation .
     *
     * @param value POJO to serialize
     * @return String representation of the POJO Dto
     * @throws IOException if object cannot be serialized.
     */
    public static String convertDtoToJsonString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            log.error("Cannot serialize dto to string.");
            log.error(e);
            return "";
        }
    }

    /**
     * Perform payload validation against the schema defined for the payload message and add the errors to {@link Error}
     * object if exists.
     *
     * @param msg        {@link String} message payload
     * @param jsonSchema {@link String} json schema name
     * @param errors     object that saves the errors that are found
     * @throws InvalidJsonException if something goes wrong when parsing the payload
     */
    public static void validateMsg(String msg, String jsonSchema, Errors errors) throws InvalidJsonException {
        try {
            JsonNode json = objectMapper.readTree(msg);
            Set<ValidationMessage> validationResult = getJsonSchema(jsonSchema).validate(json);

            if (!validationResult.isEmpty()) {
                validationResult.forEach(vm -> {
                    log.error(vm.getMessage());
                    try {
                        String fieldName = vm.getMessage().split(":")[0].replace("$.", "");
                        errors.rejectValue(fieldName, vm.getCode(), new ValidationMessage[]{vm}, vm.getMessage());
                    } catch (NotReadablePropertyException | NullPointerException exception) {
                        errors.reject(vm.getCode(), vm.getMessage());
                    }
                });
            }
        } catch (Exception ex) {
            throw new InvalidJsonException(ex.getMessage(), ex.getCause());
        }
    }
}
