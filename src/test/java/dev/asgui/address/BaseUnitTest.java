package dev.asgui.address;

import dev.asgui.address.dto.AddressDto;
import dev.asgui.address.dto.RequestDto;

public class BaseUnitTest {

    protected final String VALID_CEP = "01003000";
    protected final String VALID_CEP_UPDATE_DIGIT = "01003321";
    protected final String INVALID_CEP_UPDATE_DIGIT = "01000321";
    protected final String INVALID_CEP_IS_NUMERIC = "01000321";
    protected final String INVALID_CEP_NOT_NUMERIC = "0100100j";

    protected AddressDto createFakeAddressDto() {
        return AddressDto.builder()
                .Estado("SP")
                .Cidade("São Paulo")
                .Bairro("Sé")
                .Rua("Rua José Bonifácio")
                .build();

    }

    protected RequestDto createFakeRequestDto(String cep) {
        return RequestDto.builder()
                .cep(cep)
                .build();
    }
}
