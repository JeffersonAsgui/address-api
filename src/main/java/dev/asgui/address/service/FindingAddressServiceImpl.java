package dev.asgui.address.service;

import dev.asgui.address.model.Address;
import org.springframework.stereotype.Service;

@Service
public class FindingAddressServiceImpl implements FindingAddressService {


    @Override
    public String updateLastDigit(String cep) {
        return null;
    }

    @Override
    public Address findAddressByCep(String cep) {
        var ad = new Address();
        ad.setCITY("City");
        ad.setDISTRICT("District");
        ad.setSTATE("State");
        ad.setSTREET("Street");
        return ad;
    }
}
