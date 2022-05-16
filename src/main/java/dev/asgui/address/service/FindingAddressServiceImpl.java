package dev.asgui.address.service;

import dev.asgui.address.client.ConsultCepClient;
import dev.asgui.address.dto.AddressDto;
import dev.asgui.address.exception.DataIntegratyViolationException;
import dev.asgui.address.mapper.AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FindingAddressServiceImpl implements FindingAddressService {

    private final ConsultCepClient consultCepClient;

    private static final Logger logger = LoggerFactory.getLogger(FindingAddressServiceImpl.class);

    @Autowired
    public FindingAddressServiceImpl(ConsultCepClient consultCepClient) {
        this.consultCepClient = consultCepClient;
    }

    @Override
    public String updateDigitLeftToRight(String cep, int position) {
        StringBuilder sb = new StringBuilder(cep);
        sb.setCharAt(position, '0');
        return sb.toString();
    }

    @Override
    public boolean isNumeric(String cep) {
        logger.info("m=address.findByCep verify isNumbering ");
        return cep.matches("^\\d+$");
    }

    @Override
    public AddressDto findAddressByCep(String cep) {
        if (!isNumeric(cep)) {
            logger.error("m=address.findByCep error cep={} ", cep);
            throw new DataIntegratyViolationException("CEP inválido");
        }
        AddressDto address = getAddressDto(cep);
        if (address == null) {
            throw new DataIntegratyViolationException("CEP inválido");
        }
        return address;
    }

    private AddressDto getAddressDto(String cep) {
        var position = cep.length();
        AddressDto address = null;
        do {
            var response = consultCepClient.getAddress(cep);
            if (Objects.requireNonNull(response.getBody()).getCep() != null) {
                address = AddressMapper.toDto(response.getBody());
                position = 0;
            } else {
                position--;
                cep = updateDigitLeftToRight(cep, position);
                logger.info("m=address.findByCep Update Digit Left To Right cep={} ", cep);
            }
        } while (position > 0);
        return address;
    }

}
