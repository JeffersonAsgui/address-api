package dev.asgui.address.service;

import dev.asgui.address.BaseUnitTest;
import dev.asgui.address.client.ConsultCepClient;
import dev.asgui.address.exception.DataIntegratyViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class FindingAddressServiceImplTest extends BaseUnitTest {

    @Mock
    private FindingAddressServiceImpl findingAddressService;

    @Mock
    private ConsultCepClient consultCepClient;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        consultCepClient = new ConsultCepClient();
        findingAddressService = new FindingAddressServiceImpl(consultCepClient);

    }

    @Test
    void should_returnTrue_when_cepIsNumeric() {
        var resultExpected = findingAddressService.isNumeric(INVALID_CEP_IS_NUMERIC);
        Assertions.assertTrue(resultExpected);
    }

    @Test
    void should_returnFalse_when_cepNotNumeric() {
        var resultExpected = findingAddressService.isNumeric(INVALID_CEP_NOT_NUMERIC);
        Assertions.assertFalse(resultExpected);
    }

    @Test
    void shoud_returnError_when_cepIsInvalid() {
        var thrownExpected = new DataIntegratyViolationException("CEP inválido");
        var thrown = Assertions.assertThrows(
                DataIntegratyViolationException.class, () -> {
                    findingAddressService.findAddressByCep(INVALID_CEP_NOT_NUMERIC);
                }, "");
        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());
    }
}