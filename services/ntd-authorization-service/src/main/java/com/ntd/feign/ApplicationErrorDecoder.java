package com.ntd.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntd.exception.ErrorResponseException;
import com.ntd.exception.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.ntd.constants.ApplicationConstants.ERROR;


public class ApplicationErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    @SuppressWarnings("unchecked")
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
             Map<String, String> errors =
                     mapper.readValue(IOUtils.toString(body, StandardCharsets.UTF_8), Map.class);
            if (response.status() == HttpStatus.BAD_REQUEST.value()) {
                return ValidationException.builder()
                        .validationErrors(errors)
                        .build();
            } else {
                return ErrorResponseException.builder()
                        .httpStatus(HttpStatus.valueOf(response.status()))
                        .message(errors.get(ERROR))
                        .build();
            }
        } catch (IOException ex) {
            throw ErrorResponseException.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(ex.getMessage())
                    .build();
        }
    }
}
