package dev.asgui.address.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


@Builder
@Getter
public class Error implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}
