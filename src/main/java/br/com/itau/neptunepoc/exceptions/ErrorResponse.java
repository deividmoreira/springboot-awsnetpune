package br.com.itau.neptunepoc.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorResponse {

    @JsonProperty("codigo_http")
    private Integer status;

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("descricao_http")
    private String reasonPhrase;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("errors")
    private List<ApiError> errors;

    public ErrorResponse(Integer status, String reasonPhrase, String message){
        this.status = status;
        this.reasonPhrase = reasonPhrase;
        this.message = message;
    }

    public ErrorResponse(Integer status, String code, String reasonPhrase, String message){
        this.status = status;
        this.code = code;
        this.reasonPhrase = reasonPhrase;
        this.message = message;
    }

    public ErrorResponse(Integer status, String reasonPhrase, List<ApiError> errors){
        this.status = status;
        this.reasonPhrase = reasonPhrase;
        this.addErrors(errors);
    }

    private void addErrors(List<ApiError> errors) {
        this.errors = new ArrayList<>();
        errors.forEach(error -> this.errors.add(new ApiError(error.getCode(), error.getMessage())));
    }

}
