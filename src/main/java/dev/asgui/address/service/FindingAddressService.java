package dev.asgui.address.service;

import dev.asgui.address.model.Address;

public interface FindingAddressService {

    String updateLastDigit(String cep);

    boolean isNumbering(String cep);

    Address findAddressByCep(String cep);
}
