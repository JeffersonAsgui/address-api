package dev.asgui.address.service;

import dev.asgui.address.client.ConsultCepClient;
import dev.asgui.address.exception.DataIntegratyViolationException;
import dev.asgui.address.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FindingAddressServiceImpl implements FindingAddressService {

    private final ConsultCepClient consultCepClient;

    private static final Logger logger = LoggerFactory.getLogger(FindingAddressServiceImpl.class);

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
    public boolean isNumbering(String cep) {
        logger.info("m=address.findByCep verify isNumbering ");
        return cep.matches("^\\d+$");
    }

    @Override
    public Address findAddressByCep(String cep) {
        if (!isNumbering(cep)) {
            logger.error("m=address.findByCep error cep={} ", cep);
            throw new DataIntegratyViolationException("CEP inválido");
        }
        var position = cep.length();
        Address ad = null;
        do {
            var response = consultCepClient.getAddress(cep);
            if (Objects.requireNonNull(response.getBody()).getCep() != null) {
                ad = new Address();
                var obj = response.getBody();
                ad.setCITY(obj.getLocalidade());
                ad.setDISTRICT(obj.getBairro());
                ad.setSTATE(obj.getUf());
                ad.setSTREET(obj.getLogradouro());
                position = 0;
            } else {
                position--;
                cep = updateDigitLeftToRight(cep, position);
                logger.info("m=address.findByCep Update Digit Left To Right cep={} ", cep);
            }
        } while (position > 0);
        return ad;
    }
}
