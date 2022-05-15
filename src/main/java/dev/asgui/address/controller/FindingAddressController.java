package dev.asgui.address.controller;

import dev.asgui.address.model.Address;
import dev.asgui.address.service.FindingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/address")
public class FindingAddressController {

    private static final Logger logger = LoggerFactory.getLogger(FindingAddressController.class);

    private final FindingAddressService service;

    public FindingAddressController(FindingAddressService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> findById(@PathVariable("cep") String cep) {
        logger.info("m=address.findByCep cep={}", cep);
        var address = service.findAddressByCep(cep);
        return ResponseEntity.ok().body(address);
    }

}