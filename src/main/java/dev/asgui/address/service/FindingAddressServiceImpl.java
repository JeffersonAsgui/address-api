package dev.asgui.address.service;

import dev.asgui.address.controller.FindingAddressController;
import dev.asgui.address.exception.DataIntegratyViolationException;
import dev.asgui.address.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindingAddressServiceImpl implements FindingAddressService {

    private static final Logger logger = LoggerFactory.getLogger(FindingAddressServiceImpl.class);

    @Override
    public String updateLastDigit(String cep) {
        return null;
    }

    @Override
    public boolean isNumbering(String cep) {
        return cep.matches("^\\d+$");
    }

    @Override
    public Address findAddressByCep(String cep) {
        if (!isNumbering(cep)){
            logger.error("m=address.findByCep error cep={} ", cep );
            throw new DataIntegratyViolationException("CEP inválido");
        }

        var ad = new Address();
        ad.setCITY("City");
        ad.setDISTRICT("District");
        ad.setSTATE("State");
        ad.setSTREET("Street");
        return ad;
    }
}
