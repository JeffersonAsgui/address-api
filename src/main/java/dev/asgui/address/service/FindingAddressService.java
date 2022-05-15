package dev.asgui.address.service;

import dev.asgui.address.dto.AddressDto;

public interface FindingAddressService {

    String updateDigitLeftToRight(String cep, int position);

    boolean isNumbering(String cep);

    AddressDto findAddressByCep(String cep);
}
