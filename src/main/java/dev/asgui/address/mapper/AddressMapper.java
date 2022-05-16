package dev.asgui.address.mapper;

import dev.asgui.address.dto.AddressDto;
import dev.asgui.address.dto.ResponseConsultCepDto;

public class AddressMapper {

    public static AddressDto toDto(ResponseConsultCepDto consultCepDto) {
        return AddressDto.builder()
                .Cidade(consultCepDto.getLocalidade())
                .Bairro(consultCepDto.getBairro())
                .Estado(consultCepDto.getUf())
                .Rua(consultCepDto.getLogradouro())
                .build();
    }
}
