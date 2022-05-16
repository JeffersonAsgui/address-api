package dev.asgui.address.integration;

import dev.asgui.address.BaseUnitTest;
import dev.asgui.address.client.ConsultCepClient;
import dev.asgui.address.controller.FindingAddressController;
import dev.asgui.address.exception.DataIntegratyViolationException;
import dev.asgui.address.mapper.AddressMapper;
import dev.asgui.address.service.FindingAddressServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class FindingAddressServiceIntegratedTest extends BaseUnitTest {

    @Autowired
    private ConsultCepClient consultCepClient;

    @Mock
    private FindingAddressServiceImpl findingAddressService;

    private FindingAddressController findingAddressController;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        findingAddressService = new FindingAddressServiceImpl(consultCepClient);
        findingAddressController = new FindingAddressController(findingAddressService);
    }

    @Test
    void should_returnAddressDto_when_updateDigitLeftToRightCepIsValid() {
        var addressDtoExpected = createFakeAddressDto();
        var addressSuccess = findingAddressService.findAddressByCep(VALID_CEP_UPDATE_DIGIT);
        assertNotNull(addressSuccess);
        assertEquals(addressSuccess, addressDtoExpected);
    }

    @Test
    void should_returnError_when_updateDigitLeftToRightCepInvalid() {
        var thrownExpected = new DataIntegratyViolationException("CEP inválido");
        var thrown = Assertions.assertThrows(
                DataIntegratyViolationException.class, () -> {
                    findingAddressService.findAddressByCep(INVALID_CEP_UPDATE_DIGIT);
                }, "");
        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());
    }


    @Test
    void should_returnAddressDto_when_findAddressByCep() {
        var addressExpected = createFakeAddressDto();
        var addressSuccess = AddressMapper.toDto(
                Objects.requireNonNull(consultCepClient.getAddress(VALID_CEP).getBody()));
        assertNotNull(addressSuccess);
        assertEquals(addressExpected, addressSuccess);
    }

    @Test
    void should_returnError_when_findAddressByCep() {
        var addressExpected = createFakeAddressDto();
        var addressSuccess = AddressMapper.toDto(
                Objects.requireNonNull(consultCepClient.getAddress(INVALID_CEP_UPDATE_DIGIT).getBody()));
        Assertions.assertNotEquals(addressExpected, addressSuccess);
    }

    @Test
    void should_returnStatusCode200_when_findByCep() {
        var request = createFakeRequestDto(VALID_CEP);
        var response = findingAddressController.findByCep(request);
        org.junit.Assert.assertEquals(200, response.getStatusCodeValue());
    }

}