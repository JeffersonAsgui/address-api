package dev.asgui.address.service;

import dev.asgui.address.dto.AddressDto;

public interface FindingAddressService {

    String updateDigitLeftToRight(String cep, int position);

    boolean isNumeric(String cep);

    AddressDto findAddressByCep(String cep);
}
