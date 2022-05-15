package dev.asgui.address.mapper;

import dev.asgui.address.dto.AddressDto;
import dev.asgui.address.dto.ResponseConsultCepDto;

public class AddressMapper {

    public static AddressDto toDto(ResponseConsultCepDto consultCepDto) {
        var address = new AddressDto();
        address.setCidade(consultCepDto.getLocalidade());
        address.setBairro(consultCepDto.getBairro());
        address.setEstado(consultCepDto.getUf());
        address.setRua(consultCepDto.getLogradouro());
        return address;
    }
}
