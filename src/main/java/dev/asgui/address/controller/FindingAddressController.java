package dev.asgui.address.controller;

import dev.asgui.address.dto.AddressDto;
import dev.asgui.address.dto.RequestDto;
import dev.asgui.address.service.FindingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/address")
public class FindingAddressController {

    private static final Logger logger = LoggerFactory.getLogger(FindingAddressController.class);

    private final FindingAddressService service;

    @Autowired
    public FindingAddressController(FindingAddressService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AddressDto> findByCep(@Valid @RequestBody RequestDto request) {
        logger.info("m=address.findByCep cep={}", request.getCep());
        var address = service.findAddressByCep(request.getCep());
        return ResponseEntity.ok().body(address);
    }

}