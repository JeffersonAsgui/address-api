package dev.asgui.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    @NotNull(message = "CEP inválido")
    @Size(min = 8, max = 8, message = "CEP inválido")
    private String cep;
}
