package br.facens.eds2.host.handler;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String userMessage;
    private String developerMessage;
    private List<String> errors;

}
