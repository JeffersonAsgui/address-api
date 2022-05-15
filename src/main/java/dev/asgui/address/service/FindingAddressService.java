package dev.asgui.address.service;

import dev.asgui.address.model.Address;

public interface FindingAddressService {

    String updateDigitLeftToRight(String cep,int position);

    boolean isNumbering(String cep);

    Address findAddressByCep(String cep);
}
