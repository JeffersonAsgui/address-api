package dev.asgui.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String Rua;
    private String Bairro;
    private String Cidade;
    private String Estado;

}